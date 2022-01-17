package edu.zjnu.base.http;

import java.io.IOException;

/**
 * @description: Main
 * @author: 杨海波
 * @date: 2022-01-14
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        try {
            HttpServer server = new HttpServer(Config.port, new HttpServlet());
            server.start();
            System.out.println("服务器启动成功, 您现在可以访问 http://localhost:" + server.getPort());
        } catch (ServerException e) {
            System.out.println("服务器启动失败...");
            e.printStackTrace();
        }
        System.in.read();
    }
}
