package edu.zjnu.spring.aop;

import org.apache.log4j.Logger;

/**
 * @author 杨海波
 * @description Service接口
 * @date 2021-02-16 12:16
 */
public interface IService {

    Logger log = Logger.getLogger(IService.class);

    void doSomeThing();
}
