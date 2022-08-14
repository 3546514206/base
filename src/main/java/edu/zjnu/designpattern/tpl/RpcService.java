package edu.zjnu.designpattern.tpl;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-14 17:03
 **/
public abstract class RpcService implements IRpcService {

    @Override
    public final RpcResponse execRpc(RpcRequest request) {
        // 1、参数校验
        checkRequest(request);
        // 2、执行网络请求
        RpcResponse response = doExecRpc(request);
        // 3、对响应做处理
        if (response.getStatus() == 1) {
            handleSuccessCase(request, response);
        } else {
            handleFailedCase(request, response);
        }

        return response;
    }

    protected abstract void handleFailedCase(RpcRequest request, RpcResponse response);

    protected abstract void handleSuccessCase(RpcRequest request, RpcResponse response);

    private RpcResponse doExecRpc(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        // todo 执行网络请求
        return response;
    }

    protected abstract void checkRequest(RpcRequest request);


}
