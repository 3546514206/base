import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

/**
 * App
 *
 * @Date 2024-09-30 08:37
 * @Author 杨海波
 **/
public class App extends Application {

    // 星星数量
    private static final int NUM_STARS = 1000;
    // 银河半径
    private static final double GALAXY_RADIUS = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        // 创建星星
        for (int i = 0; i < NUM_STARS; i++) {
            Sphere star = createStar();
            root.getChildren().add(star);
        }

        // 设置相机
        PerspectiveCamera camera = new PerspectiveCamera(true);
        // 设置相机距离
        camera.setTranslateZ(-1000);
        camera.setNearClip(1);
        camera.setFarClip(10000);

        // 创建场景
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        // 添加旋转动画
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            // 绕Y轴旋转
            root.setRotationAxis(javafx.geometry.Point3D.ZERO.add(0, 1, 0));
            // 每帧旋转一定角度
            root.setRotate(root.getRotate() + 0.5);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.setTitle("3D Galaxy Effect with Rotation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 创建星星
    private Sphere createStar() {
        Random rand = new Random();
        double x = rand.nextDouble() * GALAXY_RADIUS * 2 - GALAXY_RADIUS;
        double y = rand.nextDouble() * GALAXY_RADIUS * 2 - GALAXY_RADIUS;
        double z = rand.nextDouble() * GALAXY_RADIUS * 2 - GALAXY_RADIUS;

        // 星星的大小
        Sphere star = new Sphere(2);
        // 星星的颜色
        star.setMaterial(new PhongMaterial(Color.WHITE));
        star.setTranslateX(x);
        star.setTranslateY(y);
        star.setTranslateZ(z);

        return star;
    }
}