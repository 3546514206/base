package edu.zjnu;


import org.junit.Test;

/**
 * @author 杨海波
 * @date 2024/3/4 16:44
 * @description Ascii29Test
 */
public class StringTest {

    @Test
    public void testAscii29() {
        System.out.println(String.valueOf((char) 29));
    }

    @Test
    public void testSubStr() {
        String originalString = "aaaaasssss";

        // 截断字符串从索引2开始到索引9（不包含索引9）
        String startString = originalString.substring(0, originalString.length() / 2);
        String endString = originalString.substring(originalString.length() / 2, originalString.length());


        System.out.println("Original String: " + originalString);
        System.out.println("startString String: " + startString);
        System.out.println("endString String: " + endString);
    }

    @Test
    public void testSecretStr() {
        String tagStr = "#40";
        String secret = "#40AAAASSSSSSS";
        System.out.println(secret.substring(tagStr.length()));
    }
}
