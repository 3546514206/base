package edu.zjnu;


import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨海波
 * @date 2024-03-21 16:07
 * @description 多线程 http 通讯工具类
 */
public class HttpConnectionPoolUtil {

    /**
     * 编码格式。发送编码格式统一用UTF-8
     */
    private static final String ENCODING = Charsets.UTF_8.name();

    /**
     * 设置连接超时时间，单位毫秒。
     */
    private static final int CONNECT_TIMEOUT = 20 * 1000;

    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒。
     */
    private static final int SOCKET_TIMEOUT = 120 * 1000;
    /**
     * 相当于线程锁,用于线程安全
     */
    private final static Object syncLock = new Object();
    /**
     * 发送请求的客户端单例
     */
    private static volatile CloseableHttpClient httpClient;
    /**
     * 连接池管理类
     */
    private static PoolingHttpClientConnectionManager manager;
    /**
     * 监控线程
     */
    private static ScheduledExecutorService monitorExecutor;

    /**
     * 发送get请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static HttpClientResult doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * 发送get请求；带请求参数
     *
     * @param url    请求地址
     * @param params 请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResult doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, null, params);
    }

    /**
     * 发送get请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResult doGet(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        // 创建httpClient对象
        CloseableHttpClient httpClient = getHttpClient(url);

        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);

        if (params != null && !params.isEmpty()) {
            params.forEach(uriBuilder::setParameter);
        }

        // 创建http对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        setRequestConfig(httpGet);

        // 设置请求头
        packageHeader(headers, httpGet);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpResponse);
        } finally {
            // 释放资源
            release(httpResponse);
        }
    }

    private static CloseableHttpClient getHttpClient(String url) {
        if (Objects.isNull(httpClient)) {
            synchronized (syncLock) {
                if (Objects.isNull(httpClient)) {
                    httpClient = createHttpClient(url);

                    //开启监控线程,对异常和空闲线程进行关闭
                    monitorExecutor = Executors.newScheduledThreadPool(1);
                    monitorExecutor.scheduleAtFixedRate(() -> {

                        //关闭异常连接
                        manager.closeExpiredConnections();

                        //关闭20s空闲的连接
                        manager.closeIdleConnections(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                        // log.info("close expired and idle for over 20s connection");

                    }, 300, 300, TimeUnit.SECONDS);
                }
            }
        }

        return httpClient;
    }

    private static CloseableHttpClient createHttpClient(String url) {
        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainSocketFactory).register("https", sslSocketFactory).build();

        manager = new PoolingHttpClientConnectionManager(registry);
        //设置连接参数
        manager.setMaxTotal(200);
        manager.setDefaultMaxPerRoute(20);

        HttpHost httpHost = new HttpHost(url);
        manager.setMaxPerRoute(new HttpRoute(httpHost), 20);

        //请求失败时,进行请求重试
        HttpRequestRetryHandler handler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
                if (i > 1) {
                    //重试超过1次,放弃请求
                    System.out.println("retry has more than 1 time, give up request");
                    return false;
                }
                if (e instanceof NoHttpResponseException) {
                    //服务器没有响应,可能是服务器断开了连接,应该重试
                    System.out.println("receive no response from server, retry");
                    return true;
                }
                if (e instanceof SSLHandshakeException) {
                    // SSL握手异常
                    System.out.println("SSL hand shake exception");
                    return false;
                }
                if (e instanceof InterruptedIOException) {
                    //超时
                    System.out.println("InterruptedIOException");
                    return false;
                }
                if (e instanceof UnknownHostException) {
                    // 服务器不可达
                    System.out.println("server host unknown");
                    return false;
                }
                if (e instanceof SSLException) {
                    System.out.println("SSLException");
                    return false;
                }

                HttpClientContext context = HttpClientContext.adapt(httpContext);
                HttpRequest request = context.getRequest();
                //如果请求不是关闭连接的请求
                return !(request instanceof HttpEntityEnclosingRequest);
            }
        };

        return HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler).build();
    }

    /**
     * 发送post请求；带请求参数
     *
     * @param url       请求地址
     * @param jsonParam 参数集合
     * @param desc      接口描述
     * @return
     * @throws Exception
     */
    public static HttpClientResult doPost(String url, String jsonParam, String desc) throws Exception {
        return doPost(url, null, jsonParam, desc);
    }

    /**
     * 发送post json请求；带请求头和请求参数
     *
     * @param url       请求地址
     * @param headers   请求头集合
     * @param jsonParam 请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResult doPost(String url, Map<String, String> headers, String jsonParam, String desc) throws Exception {
        // log.info("{},请求url:{}, 请求参数:{}", desc, url, jsonParam);
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 创建httpClient对象
        CloseableHttpClient httpClient = getHttpClient(url);

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);

        setRequestConfig(httpPost);

        // 设置请求头
        packageHeader(headers, httpPost);

        // 封装请求参数
        httpPost.setEntity(new StringEntity(jsonParam, ContentType.APPLICATION_JSON));

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost, HttpClientContext.create());

        try {
            // 执行请求并获得响应结果
            HttpClientResult result = getHttpClientResult(httpResponse);

            stopwatch.stop();
            // log.info("请求url:{},响应:{},耗时{}", url, result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
            return result;
        } finally {
            // 释放资源
            release(httpResponse);
        }
    }

    /**
     * 发送普通post请求；带请求头和请求参数
     *
     * @param url     请求地址
     * @param headers 请求头集合
     * @param params  请求参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResult doPost(String url, Map<String, String> headers, Map<String, String> params, String desc) throws Exception {
        // log.info("{},请求url:{}, 请求参数:{}", desc, url, params);
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 创建httpClient对象
        CloseableHttpClient httpClient = getHttpClient(url);

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);

        setRequestConfig(httpPost);

        // 设置请求头
        packageHeader(headers, httpPost);

        // 封装请求参数
        packageParam(params, httpPost);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost, HttpClientContext.create());

        try {
            // 执行请求并获得响应结果
            HttpClientResult result = getHttpClientResult(httpResponse);

            stopwatch.stop();
            // log.info("请求url:{},响应:{},耗时{}", url, result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
            return result;
        } finally {
            // 释放资源
            release(httpResponse);
        }

    }

    /**
     * 对http请求进行基本设置
     *
     * @param httpRequestBase http请求
     */
    private static void setRequestConfig(HttpRequestBase httpRequestBase) {
        /*
         * setConnectTimeout：设置连接超时时间，单位毫秒。
         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
         */
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * Description: 封装请求头
     *
     * @param params
     * @param httpMethod
     */
    private static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        if (params == null || params.isEmpty()) {
            return;
        }

        // 设置到请求头到HttpRequestBase对象中
        params.forEach(httpMethod::setHeader);

    }

    /**
     * Description: 封装请求参数
     *
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    private static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod) throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nvps = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }

    /**
     * Description: 获得响应结果
     *
     * @param httpResponse
     * @return
     * @throws Exception
     */
    private static HttpClientResult getHttpClientResult(CloseableHttpResponse httpResponse) throws Exception {
        // 获取返回结果
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = StringUtils.EMPTY;
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
        }
        return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    /**
     * Description: 释放资源
     *
     * @param httpResponse
     * @throws IOException
     */
    private static void release(CloseableHttpResponse httpResponse) {
        // 释放资源
        if (null != httpResponse) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
                httpResponse.close();
            } catch (IOException e) {
                // log.error("释放链接错误");
            }
        }
    }

    /**
     * 关闭连接池
     */
    public static void closeConnectionPool() {
        try {
            httpClient.close();
            manager.close();
            monitorExecutor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}