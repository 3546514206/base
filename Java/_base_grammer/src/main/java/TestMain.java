import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * todo
 *
 * @Date 2024-11-29 11:33
 * @Author 杨海波
 **/
public class TestMain {


    private static final AtomicLong counter = new AtomicLong(1);
    private static final long START_EPOCH = System.currentTimeMillis();

    /**
     * 生成一个指定长度的唯一序列
     * @param length 序列长度（最小10位）
     * @return 唯一字符串
     */
    public static String generate(int length) {
        if (length < 10) {
            throw new IllegalArgumentException("Length must be at least 10 to ensure uniqueness");
        }

        long timestamp = System.currentTimeMillis() - START_EPOCH;
        long count = counter.getAndIncrement();

        String raw = timestamp + String.format("%05d", count); // 至少15位

        if (raw.length() > length) {
            throw new RuntimeException("Cannot generate unique ID of length " + length);
        }

        // 补齐前缀（可根据需要改成随机字符）
        return String.format("%" + length + "s", raw).replace(' ', '0');
    }

    public static String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    public static void main(String[] args) {
        LocalDateTime lst = LocalDateTime.now();
        System.out.println(lst.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        System.out.println(isPositiveInteger("12"));
        System.out.println(isPositiveInteger("0"));
        System.out.println(isPositiveInteger("9999"));
        System.out.println(isPositiveInteger("-1"));
        System.out.println(isPositiveInteger("1.23"));


        Map<String,String> pa = new HashMap<>();



        String nul = pa.get("a");
        System.out.println(nul);

        System.out.println("xxxx".contains(""));

        System.out.println(Boolean.TRUE.toString());

    }

    public static boolean isPositiveInteger(String input) {
        return input != null && input.matches("^[1-9]\\d*$");
    }



}
