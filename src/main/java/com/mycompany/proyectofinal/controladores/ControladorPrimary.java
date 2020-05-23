/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.utils.ApoyoClientes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Manueh
 */
public class ControladorPrimary extends General {

    @FXML
    private TextField dni;
    @FXML
    private PasswordField contrase;
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot(Escenas.SECONDARY.getUrl());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    /**
     * Inicia el programa con el cliente de los datos introducidos. Si coincide con 44170333Y nos lleva a la vista en modo administrador 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void iniciarlobby(ActionEvent event) throws IOException {
        ClientesDAO clientes = new ClientesDAO(dni.getText().toUpperCase());

        if (contrase.getText().length() > 0 || dni.getText().length() > 0) {
            if (clientes.getDni().length() < 9) {
                super.muestraerror("Usuario inexistente", "Compruebe el campo DNI", "Si eres usuario nuevo, puedes registrarte");
            } else {
                if (clientes.getContraseña().equals(contrase.getText())) {
                    ApoyoClientes.setCliente(clientes);
                    if(dni.getText().toUpperCase().equals("44170333Y")){
                        App.setRoot(Escenas.VENTANAADMINISTRADOR.getUrl());
                    }else{
                        App.setRoot(Escenas.LOBBY.getUrl());
                    }
                    
                } else {
                    super.muestraerror("Error validación", "La contaseña no coincide", "Compruebe que la contraseña es correcta para el usuario introducido");
                }
            }
        }else{
            super.muestrawarnig("Campos vacíos", "Cumpruebe que los campos tengan contenido", "");
        }

    }

}
