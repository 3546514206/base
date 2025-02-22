package com.gqz.syn;

public class SynTest02 {

    public static void main(String[] args) {
        Account account = new Account(30000, "旅游费用");
        SafeDrawing you = new SafeDrawing(account, 18000, "you");
        SafeDrawing wife = new SafeDrawing(account, 20000, "wife");

        you.start();
        wife.start();
    }
}

//模拟提款
class SafeDrawing extends Thread {
    Account account; //操作取钱的对象
    int drawingMoney;//取钱数目
    int packetTotal; //口袋里的钱
    boolean flag = false;

    public SafeDrawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    //目标锁定失败，这里不是this 应该是锁account
    public synchronized void test() {
        if (account.money - drawingMoney < 0) {
            return;
        } else {
            flag = true;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        account.money -= drawingMoney;
        packetTotal += drawingMoney;
        System.out.println(this.getName() + "-->本次提款金额： " + drawingMoney);
        System.out.println(flag ? "取款成功！" : "取款失败");
        System.out.println(this.getName() + "-->口袋里面的钱： " + packetTotal);
        System.out.println(this.getName() + "-->账户剩余金额： " + account.money);
    }
}