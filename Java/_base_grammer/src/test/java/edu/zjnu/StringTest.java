package edu.zjnu;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testString() {
        String str = "347021a417c78d95d06dd4c4c6158368057296b55d29caff4f741385ea63922c7bb4a62bb1be8240a4e59d779a14d77c678d3ff07e5088c963f1f848c002b9d3";
        System.out.println(str.length());
        String key1 = str.substring(0, str.length() / 2);
        String key2 = str.substring(str.length() / 2);
        System.out.println(key1.length());
        System.out.println(key2.length());
        System.out.println(key1);
        System.out.println(key2);
    }

    @Test
    public void test001() {
        List<String> testList = new ArrayList<>();
        System.out.println("".equals(testList));
    }

    @Test
    public void test002() {

        String inputStr = null;
        int value = Optional.ofNullable(inputStr)
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .orElse(0);

        System.out.println(value);
    }
}
