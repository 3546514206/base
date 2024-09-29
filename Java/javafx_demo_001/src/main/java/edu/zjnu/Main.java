package edu.zjnu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean darkTheme = true; // 默认使用暗黑主题

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        loadPage(webEngine);

        Button toggleThemeButton = new Button("切换主题");
        toggleThemeButton.setOnAction(event -> toggleTheme(webEngine));

        VBox vbox = new VBox(toggleThemeButton, webView);
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("JavaFX WebView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadPage(WebEngine webEngine) {
        String theme = darkTheme ? "dark" : "light";
        webEngine.load(getClass().getResource("/index.html").toExternalForm() + "?theme=" + theme);
    }

    private void toggleTheme(WebEngine webEngine) {
        darkTheme = !darkTheme; // 切换主题状态
        loadPage(webEngine); // 重新加载页面
    }
}
