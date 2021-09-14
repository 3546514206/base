package edu.zjnu.arithmetic.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 杨海波
 * @Description 老婆的作业
 * @create 2021-05-30
 */
public class InstallBox {


    public static void main(String[] args) {
        List<E> list = new ArrayList<>();

        // 12
        E e12_1 = new E(12, 1, 1);
        E e12_2 = new E(3, 2, 2);
        E e12_3 = new E(3, 1, 4);
        E e12_4 = new E(6, 2, 1);
        E e12_5 = new E(4, 1, 3);
        E e12_6 = new E(2, 1, 6);
        E e12_7 = new E(4, 3, 1);
        E e12_8 = new E(2, 2, 3);
        E e12_9 = new E(1, 1, 12);
        E e12_10 = new E(6, 1, 2);
        list.add(e12_1);
        list.add(e12_2);
        list.add(e12_3);
        list.add(e12_4);
        list.add(e12_5);
        list.add(e12_6);
        list.add(e12_7);
        list.add(e12_8);
        list.add(e12_9);
        list.add(e12_10);

        // 14
        E e14_1 = new E(14, 1, 1);
        E e14_2 = new E(7, 1, 2);
        E e14_3 = new E(1, 1, 14);
        E e14_4 = new E(7, 2, 1);
        E e14_5 = new E(2, 1, 7);
        list.add(e14_1);
        list.add(e14_2);
        list.add(e14_3);
        list.add(e14_4);
        list.add(e14_5);

        //16
        E e16_1 = new E(16, 1, 1);
        E e16_2 = new E(8, 1, 2);
        E e16_3 = new E(2, 2, 4);
        E e16_4 = new E(8, 2, 1);
        E e16_5 = new E(4, 2, 2);
        E e16_6 = new E(2, 1, 8);
        E e16_7 = new E(4, 4, 1);
        E e16_8 = new E(4, 1, 4);
        E e16_9 = new E(1, 1, 16);
        list.add(e16_1);
        list.add(e16_2);
        list.add(e16_3);
        list.add(e16_4);
        list.add(e16_5);
        list.add(e16_6);
        list.add(e16_7);
        list.add(e16_8);
        list.add(e16_9);

        // 18
        E e18_1 = new E(18, 1, 1);
        E e18_2 = new E(3, 3, 2);
        E e18_3 = new E(3, 1, 6);
        E e18_4 = new E(9, 2, 1);
        E e18_5 = new E(6, 1, 3);
        E e18_6 = new E(2, 1, 9);
        E e18_7 = new E(6, 3, 1);
        E e18_8 = new E(3, 2, 3);
        E e18_9 = new E(1, 1, 18);
        E e18_10 = new E(9, 1, 2);
        list.add(e18_1);
        list.add(e18_2);
        list.add(e18_3);
        list.add(e18_4);
        list.add(e18_5);
        list.add(e18_6);
        list.add(e18_7);
        list.add(e18_8);
        list.add(e18_9);
        list.add(e18_10);

        // 20
        E e20_1 = new E(20, 1, 1);
        E e20_2 = new E(5, 2, 2);
        E e20_3 = new E(2, 2, 5);
        E e20_4 = new E(10, 2, 1);
        E e20_5 = new E(5, 1, 4);
        E e20_6 = new E(2, 1, 10);
        E e20_7 = new E(5, 4, 1);
        E e20_8 = new E(4, 1, 5);
        E e20_9 = new E(1, 1, 20);
        E e20_10 = new E(10, 1, 2);
        list.add(e20_1);
        list.add(e20_2);
        list.add(e20_3);
        list.add(e20_4);
        list.add(e20_5);
        list.add(e20_6);
        list.add(e20_7);
        list.add(e20_8);
        list.add(e20_9);
        list.add(e20_10);

        // 22
        E e22_1 = new E(22, 1, 1);
        E e22_2 = new E(11, 1, 2);
        E e22_3 = new E(1, 1, 22);
        E e22_4 = new E(11, 2, 1);
        E e22_5 = new E(2, 1, 11);
        list.add(e22_1);
        list.add(e22_2);
        list.add(e22_3);
        list.add(e22_4);
        list.add(e22_5);

        // 24
        E e24_1 = new E(24, 1, 1);
        E e24_2 = new E(4, 3, 2);
        E e24_3 = new E(4, 1, 6);
        E e24_4 = new E(12, 2, 1);
        E e24_5 = new E(8, 1, 3);
        E e24_6 = new E(2, 2, 6);
        E e24_7 = new E(8, 3, 1);
        E e24_8 = new E(4, 2, 3);
        E e24_9 = new E(3, 1, 8);
        E e24_10 = new E(6, 4, 1);
        E e24_11 = new E(6, 1, 4);
        E e24_12 = new E(2, 1, 12);
        E e24_13 = new E(12, 1, 2);
        E e24_14 = new E(3, 2, 4);
        E e24_15 = new E(1, 1, 24);
        E e24_16 = new E(6, 2, 2);
        list.add(e24_1);
        list.add(e24_2);
        list.add(e24_3);
        list.add(e24_4);
        list.add(e24_5);
        list.add(e24_6);
        list.add(e24_7);
        list.add(e24_8);
        list.add(e24_9);
        list.add(e24_10);
        list.add(e24_11);
        list.add(e24_12);
        list.add(e24_13);
        list.add(e24_14);
        list.add(e24_15);
        list.add(e24_16);

        //目标函数：判断l:b:n 是否接近 1.5：1：1 的方法
        System.out.println("定义的目标函数为：min[(l:b - 1.5)^2  + (b:n - 1)^2 + (l:n - 1.5)^2 ]");

        // 目标函数值为必大于0的数
        double[] mins = new double[list.size()];

        for (E e : list) {
//            System.out.println("长l宽b高n分别为：" + e.getL() + ", " + e.getN() + "," + e.getB() + "时");
            // 目标函数
            double tmpMin = (e.getL() / e.getB() - 1.5) * (e.getL() / e.getB() - 1.5)
                    + (e.getB() / e.getN() - 1) * (e.getB() / e.getN() - 1)
                    + (e.getL() / e.getN() - 1.5) * (e.getL() / e.getN() - 1.5);

            mins[list.indexOf(e)] = tmpMin;
        }

        Arrays.sort(mins);

        double m1 = mins[0];
        double m2 = mins[1];
        double m3 = mins[2];
        double m4 = mins[3];
        double m5 = mins[4];
        double m6 = mins[5];


        List<E> allResult = new ArrayList<>();
        for (E e : list) {
            // 目标函数
            double tmpMin = (e.getL() / e.getB() - 1.5) * (e.getL() / e.getB() - 1.5)
                    + (e.getB() / e.getN() - 1) * (e.getB() / e.getN() - 1)
                    + (e.getL() / e.getN() - 1.5) * (e.getL() / e.getN() - 1.5);

            if (tmpMin == m1 || tmpMin == m2 || tmpMin == m3 || tmpMin == m4 || tmpMin == m5 || tmpMin == m6) {
                allResult.add(e);
            }

        }

        for (E e : allResult) {

            System.out.printf("第" + (allResult.indexOf(e) + 1) + "组较优解的目标函数值为%21s  长（l）宽（b）高（n）分别是：" + e.getL() + " ," + e.getB() + " ," + e.getN() + ";\n", mins[allResult.indexOf(e)] + ";");
        }

    }
}

class E {
    private double l;
    private double b;
    private double n;

    E(double l, double b, double n) {
        this.l = l;
        this.b = b;
        this.n = n;
    }

    public double getL() {
        return l;
    }

    public double getB() {
        return b;
    }

    public double getN() {
        return n;
    }
}

