/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.ProductoDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * Classe que controla la vista de añadir producto 
 * @author Manueh
 */
public class ControladorAñadirPro extends General {

    private Image ide;
    @FXML
    private ImageView img;
    @FXML
    private TextField nombre;
    @FXML
    private TextField calorias;
    @FXML
    private TextField gratast;
    @FXML
    private TextField grasassatu;
    @FXML
    private TextField hidratos;
    @FXML
    private TextField azucar;
    @FXML
    private TextField proteinas;
    @FXML
    private TextField sodio;
    @FXML
    private TextField fibra;
    @FXML
    private TextField marca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ide = img.getImage();
    }
    /**
     * Limpia los campos en ventana 
     */
    @FXML
    private void Limpiar() {
        img.setImage(ide);
        nombre.clear();
        marca.clear();
        hidratos.clear();
        grasassatu.clear();
        gratast.clear();
        azucar.clear();
        sodio.clear();
        fibra.clear();
        proteinas.clear();
        calorias.clear();

    }
    /**
     * Vuelve a la ventana de administrador
     * @param event 
     */
    @FXML
    private void Volver(ActionEvent event) {
        try {
            App.setRoot(Escenas.VENTANAADMINISTRADOR.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(ControladorSecondary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Carga una imagen a partir de un filechooser
     * @param event 
     */
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
    /**
     * Guarda un producto en la base de datos 
     */
    @FXML
    private void Guardar() {
        ProductoDAO nuev = null;
        if (marca != null && nombre != null && gratast != null && grasassatu != null && hidratos != null && azucar != null && sodio != null && proteinas != null && fibra != null && img != null && calorias != null) {
            if (nombre.getText().length() > 0 && marca.getText().length() > 0 && gratast.getText().length() > 0 && grasassatu.getText().length() > 0 && calorias.getText().length() > 0 && hidratos.getText().length() > 0 && proteinas.getText().length() > 0 && sodio.getText().length() > 0 && fibra.getText().length() > 0 && img.getImage() != null && azucar.getText().length() > 0) {
                try {
                    nuev=new ProductoDAO(nombre.getText(),marca.getText(),Float.parseFloat(calorias.getText()), Float.parseFloat(gratast.getText()), Float.parseFloat(grasassatu.getText()), Float.parseFloat(hidratos.getText()), Float.parseFloat(azucar.getText()), Float.parseFloat(proteinas.getText()), Float.parseFloat(fibra.getText()), Float.parseFloat(sodio.getText()), UtilidadesGenerales.pasarimagenabites(img.getImage()));
                    if (nuev.save() > 0) {
                        super.muestrinformacion("Creación producto", "Producto añadido correctamente", "");
                        Limpiar();
                    }
                } catch (Exception es) {
                    super.muestraerror("Error", "Compos con información no válida", "");
                }

            } else {
                super.muestraerror("Error al crear producto", "Compruebe que no haya campos en blanco ", "");
            }
        } else {
            super.muestraerror("Error al crear producto", "COmpruebe que no haya campos en blanco ", "");
        }
    }

}
