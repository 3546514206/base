package edu.zjnu.arithmetic.practice;

import java.math.BigDecimal;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-29
 **/
public class pai {

    public static void main(String[] args) {
        final int PRECISION = 100;//计算精度
        final int THENUMBEROFCIRCLES = 1000;//循环次数
        BigDecimal PI = new BigDecimal(0);
        System.out.println("正在计算中...请稍后...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THENUMBEROFCIRCLES; i++) {
            //PI=PI+1/16^i(4/(8i+1)-2/(8i+4)-1/(8n+5)-1/(8n+6))
            PI = PI.add((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(16).pow(i))).multiply((BigDecimal.valueOf(4)
                    .divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(1)), PRECISION, BigDecimal.ROUND_DOWN))//ROUND_DOWN接近零的舍入模式（截取）
                    .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(4)), PRECISION, BigDecimal.ROUND_DOWN))
                    .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(5)), PRECISION, BigDecimal.ROUND_DOWN)))
                    .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(6)), PRECISION, BigDecimal.ROUND_DOWN)))));
        }
        System.out.println("PI=" + PI.setScale(PRECISION, BigDecimal.ROUND_DOWN) + "\n共用时："//输出精度设置成30
                + (System.currentTimeMillis() - startTime) / 1000.0 + "秒");
    }
}
