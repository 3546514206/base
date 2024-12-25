module edu.zjnu.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    // requires eu.hansolo.tilesfx;

    opens edu.zjnu.demo to javafx.fxml;
    exports edu.zjnu.demo;
}