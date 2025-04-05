import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatExample {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date now = new Date();
        String formattedDate = sdf.format(now);
        System.out.println("Current Date and Time: " + formattedDate);

        String test = "2024-04-30 11:03:54:719";

    }
}
