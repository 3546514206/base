package com.date;

import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author landyl
 * @create 12:16 PM 04/09/2018
 */
public class DiffDate {
    public static void dateDiff(String startTime, String endTime, String format) throws Exception {
//        在java 编程中，不可避免用到计算时间差。前面我写过几篇文章，关于java 时间计算的，还有timezone 转换的文章，但没有这么具体到相差到天数，小时，分钟，秒数都列出来的情况，所以这里再总结下。
//        1.用JDK 自带API 实现。
//        2.利用 joda time library 来实现.
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;

        Date startDate = sd.parse(startTime);
        Date endDate = sd.parse(endTime);
        //获得两个时间的毫秒时间差异
        long different = endDate.getTime() - startDate.getTime();
//        System.out.println(diff);
//        long day = diff / nd;//计算差多少天
//        long hour = diff % nd / nh;//计算差多少小时
//        long min = diff % nd % nh / nm;//计算差多少分钟
//        long sec = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
//        System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

        //看看这里的输出，并没有年，月的差别。手工计算是件麻烦事，所以一般采用了 joda time library 来实现，
        Interval interval =
                new Interval(startDate.getTime(), endDate.getTime());
        Period period = interval.toPeriod();

        System.out.printf(
                "%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n",
                period.getYears(), period.getMonths(), period.getDays(),
                period.getHours(), period.getMinutes(), period.getSeconds());

    }

    public static void main(String[] args) throws Exception {
        dateDiff("2018-03-02 12:22:30","2018-03-02 13:30:30","yyyy-MM-dd HH:mm:ss");
    }
}
