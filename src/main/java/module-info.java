module com.example.licenta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires spring.web;
    requires spring.core;


    opens com.example.licenta to javafx.fxml;
    exports com.example.licenta;
    exports com.example.licenta.DataBase;
    opens com.example.licenta.DataBase to javafx.fxml;
    exports com.example.licenta.Auxiliary;
    opens com.example.licenta.Auxiliary to javafx.fxml;
    exports com.example.licenta.Entities;



}