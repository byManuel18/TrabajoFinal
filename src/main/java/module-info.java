module com.mycompany.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.prefs;
    requires java.xml.bind;
    requires java.desktop;
   
    opens com.mycompany.proyectofinal to javafx.fxml;
    opens com.mycompany.proyectofinal.controladores to javafx.fxml;
    opens com.mycompany.proyectofinal.models to java.xml.bind;
    opens com.mycompany.proyectofinal.utils to java.xml.bind;
    exports com.mycompany.proyectofinal;
}