package edu.zjnu.designpattern.zhaihongwei.mediator;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by zhaihongwei on 2018/4/4
 * 具体中介者对象
 */
public class ConcreteMediator implements Mediator {

    public Set<Colleague> colleagues = new HashSet<>();

    /**
     * 添加具体的ConcreteColleague对象
     *
     * @param colleague
     */
    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    /**
     * 删除指定的ConcreteColleague对象
     *
     * @param colleague
     */
    public void removeColleague(Colleague colleague) {
        colleagues.remove(colleague);
    }

    /**
     * 一个Colleague的改变，影响其他的Colleague
     *
     * @param message
     * @param colleague
     */
    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleagues.contains(colleague)) {
            colleagues.remove(colleague);
            // 改变其他的所有Colleague对象
            for (Colleague coll : colleagues) {
                coll.getMessage(message);
            }
            colleagues.add(colleague);
        }
    }


    /**
     * 中介者对象需要知道所有的Colleague对象
     *//*
    public ColleagueA colleagueA;
    public ColleagueB colleagueB;

    public void setColleagueA(ColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }
    public void setColleagueB(ColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    *//**
     * 一个Colleague的改变，影响其他的Colleague
     * @param message
     * @param colleague
     *//*
    @Override
    public void sendMessage(String message, Colleague colleague) {

        if (colleague == colleagueA) {
            colleagueB.getMessage(message);
        } else if (colleague == colleagueB) {
            colleagueA.getMessage(message);
        }
    }
*/


}
