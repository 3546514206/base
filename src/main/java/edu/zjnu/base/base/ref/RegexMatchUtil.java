package edu.zjnu.base.base.ref;

/**
 * @author: 杨海波
 * @date: 2023-05-29 18:34:09
 * @description: todo
 */
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatchUtil {

    public static String replaceVariables(String str, Map<String, Object> map) {
        String regex = "\\$\\{([^{}]+)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String varName = matcher.group(1);
            Object varValue = map.get(varName);
            if (varValue != null) {
                String replacement = Matcher.quoteReplacement(varValue.toString());
                str = matcher.replaceFirst(replacement);
                matcher.reset(str);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String str = "Hello, ${name}! Welcome to ${place}.";
        Map<String, Object> map = new HashMap<>();
        map.put("name","Alice");
        map.put("place","New York");
        String result = replaceVariables(str, map);
        System.out.println(result);
        // 输出：Hello, Alice! Welcome to New York.
    }

}

