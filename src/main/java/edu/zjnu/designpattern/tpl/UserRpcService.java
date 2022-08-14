package edu.zjnu.designpattern.tpl;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-14 17:09
 **/
public class UserRpcService extends RpcService {
    @Override
    protected void handleFailedCase(RpcRequest request, RpcResponse response) {
        // log
    }

    @Override
    protected void handleSuccessCase(RpcRequest request, RpcResponse response) {
        // todo 业务代码

    }

    @Override
    protected void checkRequest(RpcRequest request) {
        //
    }
}
