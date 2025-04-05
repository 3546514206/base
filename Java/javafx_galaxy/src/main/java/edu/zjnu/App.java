package edu.zjnu;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

/**
 * App
 *
 * @Date 2024-09-29 23:20
 * @Author 杨海波
 **/
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        URL url = getClass().getResource("/index.html");

        if (null == url) {
            throw new RuntimeException("index.html file not found!");
        }

        webEngine.load(url.toExternalForm());

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setTitle("JavaFX Galaxy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}