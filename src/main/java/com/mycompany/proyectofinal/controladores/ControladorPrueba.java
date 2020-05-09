/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Manueh
 */
public class ControladorPrueba implements Initializable{
    
    @FXML
    private Button pr;
    @FXML
    private ImageView img;
    
    @FXML
    private void CargarImagen(ActionEvent event){
        FileChooser flich=new FileChooser();
        flich.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images","."),
                new FileChooser.ExtensionFilter("JPG",".jpg"),
                new FileChooser.ExtensionFilter("PNG",".png"));
        flich.setTitle("ELIGE UNA FOTO");
        
        File ventana=flich.showOpenDialog(null);
        if(ventana!=null){
            Image im = new Image("file:"+ventana.getAbsolutePath());
            img.setImage(im);
        }
    }
    
    @FXML 
    private void Guarda(ActionEvent event){
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
