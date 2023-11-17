package io.github.lvgocc.visitor;

/**
 * 我比较博学多识
 * <p>
 * 欢迎跟我一起学习，微信（lvgocc）公众号搜索：星尘的一个朋友
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @blog @see http://lvgo.org
 * @CSDN @see https://blog.csdn.net/sinat_34344123
 * @date 2020/12/1
 */
public class I implements Visitor {
    @Override
    public void visit(Versailles versailles) {
        System.out.println("I 访问凡尔赛");
        System.out.println(versailles.getInterpretation1());
        System.out.println(versailles.getInterpretation2());
        System.out.println(versailles.getInterpretation3());
    }

    @Override
    public void visit(FiveTiaoRen fiveTiaoRen) {
        System.out.println("I 访问五条人");
        System.out.println(fiveTiaoRen.getInterpretation2());
    }
}
