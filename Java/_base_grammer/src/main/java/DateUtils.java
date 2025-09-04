import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * todo
 *
 * @Date 2025-09-03 20:01
 * @Author 杨海波
 **/
public class DateUtils {
    private static final String PATTERN = "yyyy-MM-dd";

    // String -> Date（null 安全）
    public static Date parse(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null; // 输入为空直接返回 null
        }
        try {
            return new SimpleDateFormat(PATTERN).parse(str);
        } catch (ParseException e) {
            throw new RuntimeException("日期解析失败: " + str, e);
        }
    }

    // Date -> String（null 安全）
    public static String format(Date date) {
        if (date == null) {
            return null; // 输入为空直接返回 null
        }
        return new SimpleDateFormat(PATTERN).format(date);
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.parse(null));
    }
}