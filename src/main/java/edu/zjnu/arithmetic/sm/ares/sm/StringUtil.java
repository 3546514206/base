/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 字符串工具类
 *
 * @author zwb
 */
public class StringUtil {

    private static final char[] ALL_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'};

    /**
     * The constant CODE_16_SEQUENCES.
     */
    public static final char[] CODE_16_SEQUENCES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F'};

    /**
     * Generate random string.
     *
     * @param length the length
     * @return the string
     */
    public static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR[random.nextInt(ALL_CHAR.length)]);
        }
        return sb.toString();
    }

    /**
     * Random int string.
     *
     * @param length the length
     * @param min    the min
     * @param max    the max
     * @return the string
     */
    public static String randomInt(int length, int min, int max) {
        Random random = new Random();
        String strRand = String.format("%0" + length + "X", random.nextInt(max - min) + min);
        if (strRand.length() > length) {
            strRand = strRand.substring(0, length);
        }
        return strRand;
    }

    /**
     * Random string 16 string.
     *
     * @param length the length
     * @return the string
     */
    public static String randomString16(int length) {
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String strRand = String.valueOf(CODE_16_SEQUENCES[random.nextInt(16)]);
            randomCode.append(strRand);
        }
        return randomCode.toString();
    }

}
