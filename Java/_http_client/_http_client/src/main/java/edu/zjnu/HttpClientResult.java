package edu.zjnu;

import java.io.Serializable;

/**
 * @author 杨海波
 * @date 2024-03-21 16:06
 * @description http 返回结果
 */
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = 1;
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}