package edu.zjnu.base.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description: NIO Server
 * @author: 杨海波
 * @date: 2021-08-23
 **/
public class Server {

    public static void main(String[] args) {

        try {
            // 创建ServerSocketChannel，监听8082端口
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(8082));

            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            //为ServerSocketChannel注册选择器
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //创建处理器
            Handler


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Handler {

        private int buffSize = 1024;

        private String localCharset = "UTF-8";

        public Handler() {

        }

        public Handler(String localCharset) {
            this(-1, localCharset);
        }


        public Handler(int buffSize, String localCharset) {
            if (buffSize > 0) {
                this.buffSize = buffSize;
            }

            if (localCharset != null) {
                this.localCharset = localCharset;
            }
        }

        public void handleAccept(SelectionKey key) throws IOException {

            SocketChannel socketChannel = ((ServerSocketChannel) (key.channel())).accept();

            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(buffSize));
        }

        public void handleRead(SelectionKey key){

            // 获取Channel
            SocketChannel socketChannel

        }
    }

}
