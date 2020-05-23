/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Sexo;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Manueh
 */
public class ControladorSecondary extends General{

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
    private void Volver(ActionEvent event){
        try {
            App.setRoot(Escenas.PRIMARY.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(ControladorSecondary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        byte[] imagenenbits = null;
        Image nue = img.getImage();

        if (nue != null) {
            imagenenbits = UtilidadesGenerales.pasarimagenabites(nue);
        }
        try {
            float altura = Float.parseFloat(this.altura.getText());
            float peso = Float.parseFloat(this.peso.getText());
            Sexo s = this.sexo.getValue();
            Nivel_Ejercicio n = this.ni_ejer.getValue();
            if (!UtilidadesGenerales.validarDNI(dni.getText())) {
                super.muestraerror("Error al validar dni", "", "Introduzca un dni válido");
            } else {
                Cliente nu = new Cliente(name.getText(), dni.getText().toUpperCase(), contrase.getText(), fecha_nacimiento.getValue(), s, peso, altura, n);
                if (imagenenbits != null) {
                    nu.setFoto(imagenenbits);
                }
                ClientesDAO nuevo = new ClientesDAO(nu);
                nuevo.save();
                super.muestrinformacion("Usuario creado correctamente!!", "Disfrute de la aplicación", "");
                App.setRoot(Escenas.PRIMARY.getUrl());
            }

        } catch (Exception ex) {
            super.muestraerror("Error al introducir datos", "Compruebe que no hay campos en blanco y sean válidos", "");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (sexo != null) {
            for (Sexo s : Sexo.values()) {
                sexo.getItems().add(s);
            }
        }
        if (ni_ejer != null) {
            for (Nivel_Ejercicio n : Nivel_Ejercicio.values()) {
                ni_ejer.getItems().add(n);
            }
        }
    }
    
    
    

}
