/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.enums.Escenas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Manueh
 */
public class ControladorPrimary implements Initializable{
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot(Escenas.SECONDARY.getUrl());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
