package edu.zjnu.designpattern.tpl;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-14 17:03
 **/
public interface IRpcService {

    /**
     *
     * @param request
     * @return
     */
    RpcResponse execRpc(RpcRequest request);
}
