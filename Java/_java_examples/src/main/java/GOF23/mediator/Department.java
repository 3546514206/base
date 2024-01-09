package com.gqz.mediator;

//同事类的接口
public interface Department {
    void selfAction(); //做本部门的事情

    void outAction();  //向总经理发出申请
}
