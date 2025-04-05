package edu.zjnu;

import java.nio.charset.Charset;
import java.util.Random;

/*
 * @Author 杨海波
 *
 * @Date 2024-07-23 下午7:22
 * @Description ChineseCharacterGenerator
 **/
public class ChineseCharacterGenerator {

    public static String getRandomChineseCharacter() {
        Random random = new Random();
        // 汉字的Unicode范围：0x4E00到0x9FA5
        int highPos = (176 + Math.abs(random.nextInt(39))); // 高字节范围
        int lowPos = (161 + Math.abs(random.nextInt(94)));  // 低字节范围

        byte[] bytes = new byte[2];
        bytes[0] = (byte) highPos;
        bytes[1] = (byte) lowPos;

        try {
            // 使用GB2312编码生成汉字
            Charset gb2312 = Charset.forName("GB2312");
            return new String(bytes, gb2312);
        } catch (Exception e) {
//            e.printStackTrace();
            return ""; // 返回空字符串
        }
    }
}
