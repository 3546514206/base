module edu.zjnu.demo {

    // requires 关键字后跟模块名，表示当前模块依赖哪些其他模块
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    // 关键字声明了哪些包是对反射开放的，这对于某些框架（如Spring或Hibernate）是必须的，因为它们需要在运行时访问类的私有成员。
    opens edu.zjnu.demo to javafx.fxml;

    // 关键字用来声明哪些包是可被其他模块访问的。
    exports edu.zjnu.demo;
}