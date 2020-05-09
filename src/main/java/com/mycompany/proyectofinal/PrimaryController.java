package com.mycompany.proyectofinal;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PrimaryController {
    @FXML
    private Button pr;
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
    private void Guarda(ActionEvent event){
        
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
