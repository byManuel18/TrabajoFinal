module com.mycompany.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectofinal to javafx.fxml;
    opens com.mycompany.proyectofinal.controladores to javafx.fxml;
    exports com.mycompany.proyectofinal;
}