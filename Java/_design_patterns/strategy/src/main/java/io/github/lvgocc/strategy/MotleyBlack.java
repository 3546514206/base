package io.github.lvgocc.strategy;

/**
 * MotleyBlack
 * <p>
 * 欢迎跟我一起学习，微信（lvgocc）公众号：星尘的一个朋友
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @blog @see http://lvgo.org
 * @CSDN @see https://blog.csdn.net/sinat_34344123
 * @date 2020/11/21
 */
public class MotleyBlack implements Theme {
    @Override
    public void show() {
        System.out.println("- 背景色：backgroundColor 黑灰色\n" +
                "- 字体颜色：fontColor 白色\n");
    }
}
