module edu.zjnu.progressbar001 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.zjnu.progressbar001 to javafx.fxml;
    exports edu.zjnu.progressbar001;
}