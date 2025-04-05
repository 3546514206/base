package edu.zjnu;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main
 *
 * @Date 2024-09-29 22:45
 * @Author 杨海波
 **/
public class Main extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/book_management";
    private static final String DB_USER = "root"; // 根据需要修改
    private static final String DB_PASSWORD = "Zic200716"; // 根据需要修改

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("连接数据库成功！");
            // 可在此处初始化数据库或执行其他操作
            conn.close();
        } catch (SQLException e) {
            System.err.println("数据库连接失败: " + e.getMessage());
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        initDB();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        loadPage(webEngine);

        Button refreshButton = new Button("刷新书籍列表");
        refreshButton.setOnAction(event -> loadPage(webEngine));

        VBox vbox = new VBox(refreshButton, webView);
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("图书管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDB() {
        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver"); // 注意：MySQL 8 的驱动类是 "com.mysql.cj.jdbc.Driver"

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("连接数据库成功！");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("找不到 MySQL 驱动类：" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("数据库连接失败: " + e.getMessage());
        }
    }

    private void loadPage(WebEngine webEngine) {
        webEngine.load(getClass().getResource("/index.html").toExternalForm());
    }
}