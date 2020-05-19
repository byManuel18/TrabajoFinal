/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.models.Canal;
import com.mycompany.proyectofinal.utils.XMLUtilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Manueh
 */
public class ControladorSecondary implements Initializable{
    @FXML
    private ImageView img;
    
    @FXML
    private void CargarImagen(ActionEvent event){
        FileChooser flich=new FileChooser();
        flich.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images","*.*"),
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png"));
        flich.setTitle("ELIGE UNA FOTO");
        
        File ventana=flich.showOpenDialog(null);
        if(ventana!=null){
            Image im = new Image("file:"+ventana.getAbsolutePath());
            img.setImage(im);
        }
    }
    
    @FXML
    private void Volver(ActionEvent event) throws IOException{
        App.setRoot(Escenas.PRIMARY.getUrl());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
