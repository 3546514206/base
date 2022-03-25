package edu.zjnu.base.net.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 带有参数的Post请求
 * NameValuePair
 */
public class DoPOSTParam {
    public static void main(String[] args) throws Exception {

        // 创建http POST请求
        String url = "https://sfapi.sf-express.com/std/service";
        Map<String, String> param = new HashMap<>();
        param.put("trackingType", "2");
        param.put("trackingNum", "SF1142419693047");
        param.put("phone", "13874568254");
        Map<String, Object> query = new HashMap<>();
        query.put("QuerySFWaybill", param);

        String result = doPostJson(url, JSON.toJSONString(query));
        System.out.println(result);
    }

    // 接口测试-处理json格式的post请求
    public static String doPostJson(String url, String json) {
        // 创建连接池
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        // 声明一个字符串用来存储response
        String result;
        // 创建httppost对象
        HttpPost httpPost = new HttpPost(url);
        // 给httppost对象设置json格式的参数
        StringEntity httpEntity = new StringEntity(json, "utf-8");
        // 设置请求格式
        httpPost.setHeader("Content-type", "application/json");
        // 传参
        httpPost.setEntity(httpEntity);

        // 发送请求，并获取返回值
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            // 将response转成String并存储在result中
            result = response.toString();
            return result;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "error";
    }
}