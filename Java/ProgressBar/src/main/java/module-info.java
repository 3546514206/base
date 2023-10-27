module edu.zjnu.progressbar {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.zjnu.progressbar to javafx.fxml;
    exports edu.zjnu.progressbar;
}