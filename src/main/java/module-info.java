module br.com.project.javafxmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.com.project.javafxmvc.model.domain to javafx.base;

    opens br.com.project.javafxmvc to javafx.fxml;
    exports br.com.project.javafxmvc;
    exports br.com.project.javafxmvc.controller;
    opens br.com.project.javafxmvc.controller to javafx.fxml;
}