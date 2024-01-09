package com.gqz.udp;

/**
 * 加入多线程，实现双向交流。模拟在线咨询
 *
 * @author ganquanzhong
 * @ClassName: TalkStudent
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月15日 下午3:37:33
 */
public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkRecevice(9999, "学生")).start();

        new Thread(new TalkSend(8989, "127.0.0.1", 9998)).start();
    }
}
