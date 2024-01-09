package com.gqz.state;


/**
 * 房间对象
 *
 * @author Administrator
 */
public class HomeContext {
    //如果是银行系统，这个Context类就是账号。根据金额不同，切换不同的状态！

    private State state;


    public void setState(State s) {
        System.out.println("修改状态！");
        state = s;
        state.handle();
    }

}
