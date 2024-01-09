package com.gqz.bridge;

/**
 * @author ganquanzhong
 * @ClassName: Client
 * @Description: 桥接模式
 * 处理多层继承结构 使用组合的方法
 * 使各个维度可以独立的扩展在抽象层建立关联
 * 应用场景：
 * JDBC驱动程序
 * AWT中的Peer架构
 * 银行日志
 * 人力资源系统中的奖金计算模块
 * OA系统中的消息处理*
 * @date 2019年7月22日 下午4:39:28
 */
public class Client {
    public static void main(String[] args) {
        //销售联想的笔记本电脑
        Computer2 c = new Laptop2(new Lenovo());
        c.sale();

        //销售神舟的台式机
        Computer2 c2 = new Desktop2(new Shenzhou());
        c2.sale();
    }
}
