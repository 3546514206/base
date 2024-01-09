package com.gqz.facade;

public interface 银行 {
    void openAccount();  //开户
}


class 中国工商银行 implements 银行 {

    @Override
    public void openAccount() {
        System.out.println("在中国工商银行开户！");
    }

}
