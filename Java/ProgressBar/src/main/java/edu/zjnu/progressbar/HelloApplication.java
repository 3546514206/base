package edu.zjnu.progressbar;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 260, 80);
        stage.setScene(scene);

        Group g = new Group();

        ProgressBar p2 = new ProgressBar();
        p2.setProgress(0.25F);

        g.getChildren().add(p2);

        scene.setRoot(g);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}