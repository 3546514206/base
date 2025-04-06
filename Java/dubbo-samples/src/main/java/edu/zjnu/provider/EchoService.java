package edu.zjnu.provider;


import edu.zjnu.facade.IEchoService;

/**
 * EchoService
 *
 * @Date 2025-03-06 14:56
 * @Author 杨海波
 **/
public class EchoService implements IEchoService {

    private static Integer INDEX = 0;

    @Override
    public String sayHello(String name) {
        return (++INDEX) + " : " + name;
    }
}
