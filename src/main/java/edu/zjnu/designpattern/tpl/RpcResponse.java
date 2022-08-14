package edu.zjnu.designpattern.tpl;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-14 17:05
 **/
public class RpcResponse {

    // 0-失败 1-成功
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
