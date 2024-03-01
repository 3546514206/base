package com.date;

/**
 * @author landyl
 * @create 4:31 PM 03/29/2018
 */
public class SqlDate2Util2Date {
    public static void main(String[] args) {
        // util.date转换成sql.date
        java.util.Date utilDate = new java.util.Date(); // 获取当前时间
        System.out.println(utilDate);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);

        // sql.date转换成util.date
        java.sql.Date sqlDate1 = new java.sql.Date(new java.util.Date().getTime());
        System.out.println(sqlDate1);
        java.util.Date utilDate1 = new java.util.Date(sqlDate1.getTime());
        System.out.println(utilDate1);
    }
}
