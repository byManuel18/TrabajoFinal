/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 *
 * @author Manueh
 */
public abstract class General implements Initializable{
    
    @FXML
    public void cerrarConex(){
        ConexionUtilidades.cerrarConec();
    }
    protected void muestraerror(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    protected void muestrawarnig(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    protected void muestrconfirmacion(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    protected void muestrinformacion(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    
}
