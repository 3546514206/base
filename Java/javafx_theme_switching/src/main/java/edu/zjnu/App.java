package edu.zjnu;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * App
 *
 * @Date 2024-09-30 08:44
 * @Author 杨海波
 **/
public class App extends Application {

    private boolean isDarkTheme = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 300);

        Button switchThemeButton = new Button("切换主题");
        switchThemeButton.setOnAction(e -> switchTheme(scene));

        root.getChildren().add(switchThemeButton);

        applyTheme(scene);

        primaryStage.setTitle("主题切换示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void switchTheme(Scene scene) {
        isDarkTheme = !isDarkTheme; // 切换主题状态
        applyTheme(scene); // 应用新的主题
    }

    private void applyTheme(Scene scene) {
        if (isDarkTheme) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/light-theme.css").toExternalForm());
        }
    }
}