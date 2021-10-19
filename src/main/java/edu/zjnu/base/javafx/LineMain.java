package edu.zjnu.base.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @description: javafx线条
 * @author: 杨海波
 * @date: 2021-10-19
 **/
public class LineMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        final Scene scene = new Scene(box, 300, 250);
        scene.setFill(null);


        Line line = new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(scene.getWidth());
        line.setEndY(scene.getHeight());

        box.getChildren().add(line);

        stage.setScene(scene);
        stage.show();
    }

}
