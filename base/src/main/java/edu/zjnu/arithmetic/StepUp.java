package edu.zjnu.arithmetic;

import edu.zjnu.base.LogInterFace;

/**
 * @author 杨海波
 * @Description 每次走台阶，可以走两级或者一级，给定N级台阶，总共有多少种走法
 * @create 2021-05-09
 */
public class StepUp implements LogInterFace {

    /**
     * f(n) = f(n-1) + f(n-2)
     *
     * @param n
     * @return
     */
    private static int stepUpByRecurse(int n) {
        if (0 == n) {
            return 1;
        }
        if (1 == n) {
            return 1;
        }

        return stepUpByRecurse(n - 1) + stepUpByRecurse(n - 2);
    }

    public static void main(String[] args) {
        int total = stepUpByRecurse(0);
        log.info(total);

        Object o = new Object();
    }
}
