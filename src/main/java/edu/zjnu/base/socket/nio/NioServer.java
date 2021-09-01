package edu.zjnu.base.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: Nio服务端
 * @author: 杨海波
 * @date: 2021-08-25
 **/
public class NioServer {

    public static void main(String[] args) {
        Selector selector = null;
        try {
            // 1. 创建 Selector 实例
            selector = Selector.open();
            // 2. 创建 ServerSocketChannel 实例，配置为非阻塞模式，绑定本地端口。
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(8899));
            // 3. 把 ServerSocketChannel实例 注册到 Selector 实例中
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 4. 这里设置了3秒超时时间，也就是阻塞3秒
                if (selector.select(3000) == 0) {
                    continue;
                }
                // 5. 获取选中的 SelectionKey 的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 6. 处理 SelectionKey 的感兴趣的操作。注册到 selector 中的 serverSocketChannel 只能是
                // isAcceptable() ，因此通过它的 accept() 方法，
                // 我们可以获取到客户端的请求 SocketChannel 实例，然后再把这个 socketChannel 注册到 selector
                // 中，设置为可读的操作。那么下次遍历 selectionKeys 的时候，就可以处理那么可读的操作
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // 准备好连接
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(key.selector(), SelectionKey.OP_READ);
                    }

                    // 准备好读的操作
                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(10);
                        int readBytes = clientChannel.read(readBuffer);
                        if (readBytes == -1) {
                            System.out.println("closed.......");
                            clientChannel.close();
                        } else if (readBytes > 0) {
                            String s = new String(readBuffer.array());
                            System.out.println("Client said: " + s);
                            if (s.trim().equalsIgnoreCase("Hello")) {
                                // attachment is content used to write
                                key.interestOps(SelectionKey.OP_WRITE);
                                key.attach("Welcome maizi !!!");
                            }
                        }
                    }

                    if (key.isValid() && key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        String content = (String) key.attachment();
                        // write content to socket channel
                        clientChannel.write(ByteBuffer.wrap(content.getBytes()));
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    // remove handled key from selected keys
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close selector
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
