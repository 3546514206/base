package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-03-02 10:39:23
 * @description: todo
 */
public class PrintBinMain {

    public static void main(String[] args) {

    }

    public String printBin(double num) {
        double [] a= {0.5, 0.25, 0.125, 0.0625, 0.03125, 0.015625, 0.0078125};
        String ans = "0.";
        int idx = 0;
        for(int i = 0; i < 7; i++) {
            if(num >= a[i]) {
                num -= a[i];
                ans += '1';
            }
            else if(num != .0) {
                ans += '0';
            } else {
                break;
            }
            // cout << num << endl;
        }
        if(num != 0.0) {
            return "ERROR";
        }
        return ans;
    }
}
