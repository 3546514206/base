package io.github.lvgocc.visitor;

/**
 * 访问者
 * <p>
 * 欢迎跟我一起学习，微信（lvgocc）公众号搜索：星尘的一个朋友
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @blog @see http://lvgo.org
 * @CSDN @see https://blog.csdn.net/sinat_34344123
 * @date 2020/12/1
 */
public interface Visitor {
    /**
     * 访问网络语凡尔赛
     *
     * @param versailles 凡尔赛
     */
    void visit(Versailles versailles);

    void visit(FiveTiaoRen fiveTiaoRen);
}
