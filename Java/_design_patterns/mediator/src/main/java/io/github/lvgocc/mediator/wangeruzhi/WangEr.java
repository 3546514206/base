package io.github.lvgocc.mediator.wangeruzhi;

/**
 * WangEr
 * <p>
 * 欢迎跟我一起学习，微信（lvgocc）公众号搜索：星尘的一个朋友
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @blog @see http://lvgo.org
 * @CSDN @see https://blog.csdn.net/sinat_34344123
 * @date 2020/12/1
 */
public class WangEr extends Colleague {
    public WangEr(Mediator mediator) {
        super(mediator);
    }

    @Override
    protected void receive(String str) {
        System.out.println("王二收到消息：" + str);
    }
}
