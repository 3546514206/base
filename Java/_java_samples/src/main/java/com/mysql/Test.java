package com.mysql;

import java.sql.*;
import java.util.Random;

/**
 * @author: landy
 * @date: 2020/5/17 13:03
 * @description:
 *
 * 测试项目：
 * 创建100万个随机数，并生成插入这些随机数的SQL语句。
 * 连接本地数据库，如不成功，尝试创建数据库。
 * 删除并创建数据库表，引擎类型为InnoDB，主键为自动递增的整数，此外有个浮点型的字段（无索引）。
 * 分成100组，每次插入1万个随机数。（因为每组的执行量都很大，因此启用自动提交事务。）
 * 用SELECT COUNT(*)统计小于0.1的随机数个数。（约10万个）
 * 用SELECT *取出再统计大于0.9的随机数个数。（约10万个）
 * 将所有0.4～0.5之间的随机数加1。（约10万个）
 * 将所有0.5～0.6之间的行删除。（约20万个）
 * 断开数据库连接。
 * 再次连接数据库。
 *
 */
public final class Test {

    public static void main(String[] args) {
        final int SIZE1 = 10000;
        final int SIZE2 = 100;
        final String DB_ENGINE = "InnoDB"; // InnoDB Memory MyISAM
        final double NANO_TIME_PER_SEC = 1000000000.0;
        System.out.printf("测试数据量：%d\n", SIZE1 * SIZE2);
        System.out.printf("测试引擎：%s\n", DB_ENGINE);

        long t1 = System.nanoTime(), t2, t3 = 0, t4, t5, t6, t7, t8, t9, t10, t11;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        Random r = new Random();
        String[] sqls = new String[SIZE2];
        for (int i = 0; i < SIZE2; ++i){
            StringBuilder buffer = new StringBuilder("INSERT INTO test (value) VALUES (");
            for (int j = 0; j < SIZE1; ++j){
                buffer.append(r.nextDouble()).append("),(");
            }
            sqls[i] = buffer.substring(0, buffer.length() -2);
        }
        t2 = System.nanoTime();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=landy8530");
            t3 = System.nanoTime();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=landy8530");
                t3 = System.nanoTime();
                stmt = conn.createStatement();
                stmt.execute("CREATE DATABASE test");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }

        try {
            stmt.execute("DROP TABLE test");
        } catch (SQLException e) {
        }
        try {
            stmt.execute("CREATE TABLE test (`id` INT AUTO_INCREMENT PRIMARY KEY, `value` REAL) ENGINE = " + DB_ENGINE);
        } catch (SQLException e) {
        }
        t4 = System.nanoTime();

        try {
            for (String sql: sqls){
                stmt.execute(sql);
            }
            t5 = System.nanoTime();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM test WHERE value < 0.1");
            if (rs.next())
                System.out.printf("共有%d个小于0.1的随机数\n", rs.getInt(1));
            t6 = System.nanoTime();

            rs = stmt.executeQuery("SELECT * FROM test WHERE value > 0.9");
            if (rs.next())
                System.out.printf("共有%d个大于0.9的随机数\n", rs.getRow());
            t7 = System.nanoTime();

            stmt.executeUpdate("UPDATE test SET value = value + 0.1 WHERE value > 0.4 AND value < 0.5");
            t8 = System.nanoTime();

            stmt.execute("DELETE FROM test WHERE value > 0.5 AND value < 0.6");
            t9 = System.nanoTime();

            stmt.close();
            conn.close();
            t10 = System.nanoTime();

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=landy8530");
            t11 = System.nanoTime();
            conn.close();

            System.out.printf("创建随机数：%f\n", (t2 - t1) / NANO_TIME_PER_SEC);
            System.out.printf("初次连接数据库：%f\n", (t3 - t2) / NANO_TIME_PER_SEC);
            System.out.printf("再次连接数据库：%f\n", (t11 - t10) / NANO_TIME_PER_SEC);
            System.out.printf("初始化数据库和表：%f\n", (t4 - t3) / NANO_TIME_PER_SEC);
            System.out.printf("插入：%f\n", (t5 - t4) / NANO_TIME_PER_SEC);
            System.out.printf("选择（COUNT）：%f\n", (t6 - t5) / NANO_TIME_PER_SEC);
            System.out.printf("选择：%f\n", (t7 - t6) / NANO_TIME_PER_SEC);
            System.out.printf("更新：%f\n", (t8 - t7) / NANO_TIME_PER_SEC);
            System.out.printf("删除：%f\n", (t9 - t8) / NANO_TIME_PER_SEC);
            System.out.printf("关闭连接：%f\n", (t10 - t9) / NANO_TIME_PER_SEC);
            System.out.printf("总时间：%f\n", (t10 - t1) / NANO_TIME_PER_SEC);

        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            ex.printStackTrace();
        }

    }

}