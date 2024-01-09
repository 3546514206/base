package com.gqz.syn;

public class SynBlockTest01 {

    public static void main(String[] args) {
        Account account = new Account(20000, "旅游费用");
        SynDrawing you = new SynDrawing(account, 18000, "you");
        SynDrawing wife = new SynDrawing(account, 40000, "wife");
        SynDrawing son = new SynDrawing(account, 10000, "son");
        SynDrawing re = new SynDrawing(account, 10900, "re");

        you.start();
        wife.start();
        son.start();
        re.start();
    }
}

//模拟提款
class SynDrawing extends Thread {
    Account account; //操作取钱的对象
    int drawingMoney;//取钱数目
    int packetTotal; //口袋里的钱
    boolean flag = false;

    public SynDrawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    //目标锁定account
    public void test() {
        // 提高性能
        if (account.money <= 0) {
            return;
        }
        synchronized (account) {
            System.out.println("=========account账户总金额=========： " + account.money);
            if (account.money - drawingMoney < 0) {
                flag = false;
//				return ;
            } else {
                flag = true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (flag) {
                account.money -= drawingMoney;
                packetTotal += drawingMoney;
            }
            System.out.println(this.getName() + "-->本次提款金额： " + drawingMoney);
            System.out.println(flag ? "  取款成功！" : "  取款失败,余额不足！");
//			System.out.println(this.getName()+"-->口袋里面的钱： "+packetTotal);
            System.out.println(this.getName() + "-->账户剩余金额： " + account.money);
        }
    }
}