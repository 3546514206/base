package edu.zjnu.datastructure.io.aio;

/**
 * AIO时间服务器客户端  TimeClient
 *
 * @author weijielu
 */
public class TimeClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }

        int i = 0;
        while (++i < 1000) {
            new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-" + i).start();
        }

    }

}
