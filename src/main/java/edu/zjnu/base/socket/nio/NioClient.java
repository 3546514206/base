package edu.zjnu.base.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: NioClient
 * @author: 杨海波
 * @date: 2021-08-25
 **/
public class NioClient {
    private String ENCODING_UTF8 = "utf-8";
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private CharsetEncoder encoder = Charset.forName(ENCODING_UTF8).newEncoder();
    private String server = "localhost";
    private Integer port = 8888;
    private SocketChannel channel;
    private Selector selector;

    public static void main(String[] args) throws Exception {
        NioClient del = new NioClient();
        del.init();
        del.connect();
    }

    private void init() throws IOException {
        selector = Selector.open();
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    public void connect() throws IOException {
        channel.connect(new InetSocketAddress(server, port));
        while (selector.select() > 0) {
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if (key.isConnectable()) {
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    onSend(key);
                } else if (key.isReadable()) {
                    onReceive(key);
                } else if (key.isWritable()) {
                    onSend(key);
                }
                it.remove();
            }
        }
    }

    private void onReceive(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.read(buffer);
        buffer.flip();
        System.out.println("[client]" + new String(buffer.array(), 0, buffer.limit()));
    }

    private void onSend(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        sc.write(encoder.encode(CharBuffer.wrap("send to server.")));
    }
}

