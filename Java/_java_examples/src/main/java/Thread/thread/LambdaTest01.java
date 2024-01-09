package com.gqz.thread;

interface ILike {
    void lambda();
}

public class LambdaTest01 {

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();


        like = new Like2();
        like.lambda();

        //局部内部类
        class Like3 implements ILike {
            public void lambda() {
                System.out.println("i am lambda3");
            }
        }
        like = new Like3();
        like.lambda();

        //匿名内部类
        like = new ILike() {
            public void lambda() {
                System.out.println("i am lambda4");
            }
        };
        like.lambda();

        //lambda 简化
        like = () -> {
            //自动识别lambda方法
            System.out.println("i am lambda5");
        };
        like.lambda();
    }

    //静态内部类
    static class Like2 implements ILike {
        public void lambda() {
            System.out.println("i am lambda2");
        }
    }
}

class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("i am lambda1");
    }
}