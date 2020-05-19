module com.mycompany.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.prefs;
    requires java.xml.bind;

    opens com.mycompany.proyectofinal to javafx.fxml;
    opens com.mycompany.proyectofinal.controladores to javafx.fxml;
    exports com.mycompany.proyectofinal;
}