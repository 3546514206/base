package edu.zjnu.designpattern.zhaihongwei.mediator;

/**
 * Create by zhaihongwei on 2018/4/4
 */
public class MediatorTest {

    public static void main(String[] args) {

        // 创建具体中介者对象
        ConcreteMediator mediator = new ConcreteMediator();

        // 创建ColleagueA和ColleagueB对象
        ColleagueA colleagueA = new ColleagueA(mediator);
        ColleagueB colleagueB = new ColleagueB(mediator);

        /*mediator.setColleagueA(colleagueA);
        mediator.setColleagueB(colleagueB);*/

        mediator.colleagues.add(colleagueA);
        mediator.colleagues.add(colleagueB);

        String messageA = "中介者模式就是这样的么?";
        colleagueA.sendMessage(messageA);
        String messageB = "中介者模式就是这样的";
        colleagueB.sendMessage(messageB);
    }
}
