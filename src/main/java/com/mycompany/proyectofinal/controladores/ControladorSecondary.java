/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Sexo;
import com.mycompany.proyectofinal.models.Cliente;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author Manueh
 */
public class ControladorSecondary implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextField name;
    @FXML
    private TextField dni;
    @FXML
    private TextField peso;
    @FXML
    private TextField altura;
    @FXML
    private PasswordField contrase;
    @FXML
    private DatePicker fecha_nacimiento;
    @FXML
    private ChoiceBox<Sexo> sexo;
    @FXML
    private ChoiceBox<Nivel_Ejercicio> ni_ejer;

    @FXML
    private void CargarImagen(ActionEvent event) {
        FileChooser flich = new FileChooser();
        flich.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        flich.setTitle("ELIGE UNA FOTO");

        File ventana = flich.showOpenDialog(null);
        if (ventana != null) {
            Image im = new Image("file:" + ventana.getAbsolutePath());
            img.setImage(im);
        }
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot(Escenas.PRIMARY.getUrl());
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        byte[] imagenenbits = null;
        Image nue = img.getImage();

        if (nue != null) {
            String ruta = nue.getUrl().substring(5);
            BufferedImage bfi = ImageIO.read(new File(ruta));
            WritableRaster raster = bfi.getRaster();
            DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
            imagenenbits = data.getData();
        }
        try {
            float altura = Float.parseFloat(this.altura.getText());
            float peso = Float.parseFloat(this.peso.getText());
            Sexo s=this.sexo.getValue();
            Nivel_Ejercicio n=this.ni_ejer.getValue();
            Cliente nuevo=new Cliente(name.getText(),dni.getText(),contrase.getText(),fecha_nacimiento.getValue(),s,peso,altura,n);
            if(imagenenbits!=null){
                nuevo.setFoto(imagenenbits);
            }
            System.out.println(nuevo);
        } catch (Exception ex) {
            System.out.println("No dejes campos vacíos");
        }

       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (sexo != null) {
            for (Sexo s : Sexo.values()) {
                sexo.getItems().add(s);
            }
        }
        if(ni_ejer!=null){
            for (Nivel_Ejercicio n : Nivel_Ejercicio.values()) {
                ni_ejer.getItems().add(n);
            }
        }
    }

}
