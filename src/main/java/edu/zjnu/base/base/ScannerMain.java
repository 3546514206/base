package edu.zjnu.base.base;

import java.io.IOException;
import java.util.Scanner;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-03-31 09:51
 **/
public class ScannerMain {

    public static void main(String[] args) {
        int n = -1;
        try {
            System.out.println("input n: ");
            n = System.in.read();
            System.out.println(n);
        } catch (IOException e) {

        }
        System.out.println("input : ");
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.next());
        System.out.println(a);
    }

}

