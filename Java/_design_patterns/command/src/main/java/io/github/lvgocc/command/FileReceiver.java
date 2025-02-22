package io.github.lvgocc.command;

/**
 * FileReceiver
 * <p>
 * 欢迎跟我一起学习，微信（lvgocc）公众号：星尘的一个朋友
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @blog @see http://lvgo.org
 * @CSDN @see https://blog.csdn.net/sinat_34344123
 * @date 2020/11/28
 */
public class FileReceiver implements Receiver {

    @Override
    public void action() {
        System.out.println("新增文件");
    }
}
