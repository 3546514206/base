package com.gqz.chat05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 服务端，消息转发
 * 多个客户可以正常收发消息
 *
 * @author ganquanzhong
 * @ClassName: Chat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午3:01:34
 */
public class Chat {
    static int i = 0;
    static private CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Chat.Channel>();

    public static void main(String[] args) throws IOException {
        System.out.println("=====Server=====");
        //1、指定端口号 使用ServerSocket创建服务器   监听端口9999
        ServerSocket server = new ServerSocket(9999);

        //2、阻塞式等待连接accept
        while (true) {
            Socket client = server.accept();
            System.out.println("建立" + (++i) + "连接");
            Channel c = new Channel(client);
            all.add(c);//管理所有的成员
            new Thread(c).start();
        }
    }


    /**
     * @author ganquanzhong
     * @ClassName: Channel
     * @Description: 一个client代表一个channel
     * @date 2019年7月16日 下午5:49:02
     */
    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private boolean isRunning;
        private String name;


        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
                //获取名称
                this.name = receive();
                this.send("欢迎来到ForFuture群聊系统！");
                sendOthers(this.name + "上线了", true);
            } catch (IOException e) {
                System.out.println("---初始化错误----");
                release();
                e.printStackTrace();
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("---接收消息错误----");
                release();
                e.printStackTrace();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                System.out.println("---发送消息错误----");
                release();
                e.printStackTrace();
            }
        }


        /**
         * 群聊：获取自己的消息，发送给别人
         * 私聊：约定数据格式 @：名称：msg
         *
         * @param msg
         * @param isSys
         * @Title: sendOthers
         * @Description: TODO(这里用一句话描述这个方法的作用)
         * @author ganquanzhong
         * @date 2019年7月17日 上午9:16:44
         */
        private void sendOthers(String msg, boolean isSys) {
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                //私聊
                int idx1 = msg.indexOf(":");
                int idx2 = msg.indexOf("：");
                int idx = idx1 != -1 ? idx1 : idx2;
                String targetName = msg.substring(1, idx);
                msg = msg.substring(idx + 1);
                for (Channel other : all) {
                    if (other.name.equals(targetName)) {
                        other.send("\n\t" + new SimpleDateFormat("YYYY-MM-dd HH:MM:ss SSS").format(new Date()) + "\n"
                                + this.name + ": " + msg);
                        break;
                    }
                }
            } else {
                //群聊
                for (Channel other : all) {
                    if (other == this) {//本身
                        continue;
                    }
                    if (!isSys) {
                        other.send("\n\t" + new SimpleDateFormat("YYYY-MM-dd HH:MM:ss SSS").format(new Date()) + "\n"
                                + this.name + ": " + msg);
                    } else {
                        other.send("\n\t" + new SimpleDateFormat("YYYY-MM-dd HH:MM:ss SSS").format(new Date()) + "\n"
                                + "系统消息" + ": " + msg);
                    }
                }
            }
        }

        //释放资源
        private void release() {
            this.isRunning = false;
            Utils.close(dis, dos, client);
            //退出
            all.remove(this);
            sendOthers(this.name + "离开了！", true);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    //send(msg);
                    sendOthers(msg, false);
                }
            }
        }
    }
}
