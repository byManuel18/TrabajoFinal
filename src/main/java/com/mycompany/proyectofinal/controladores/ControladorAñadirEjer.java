/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.EjerciciosDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.enums.Grupo_Muscular;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * Clase que controla la vista de añadir ejercicios a la base de datos 
 * @author Manueh
 */
public class ControladorAñadirEjer extends General {

    @FXML
    private ImageView img;
    @FXML
    private ChoiceBox<Grupo_Muscular> musculo;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField nombre;
    @FXML
    private TextField series;
    @FXML
    private TextField repeticiones;

    private Image ide;
    /**
     * Inicializa a una imagen por defecto y le da valores al choicebox
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ide = img.getImage();
        for (Grupo_Muscular s : Grupo_Muscular.values()) {
            musculo.getItems().add(s);
        }
    }
    /**
     * Vuelve a la vemtana de administrador 
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
     * carga una imagena partir de un filechooser
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
     * Limpia todos los campos en pantalla
     * @param event 
     */
    @FXML
    private void Limpiar(ActionEvent event) {
        img.setImage(ide);
        descripcion.clear();;
        nombre.clear();
        series.clear();
        repeticiones.clear();
        musculo.setValue(null);

    }
    /**
     * Guarda el producto en la base de datos 
     * @param event 
     */
    @FXML
    private void Guardar(ActionEvent event) {

        EjerciciosDAO nuevo;
        if (img != null && descripcion != null && nombre != null && series != null && repeticiones != null && musculo != null) {
            if (img.getImage() != null && descripcion.getText().length() > 0 && nombre.getText().length() > 0 && series.getText().length() > 0 && repeticiones.getText().length() > 0 && musculo.getValue() != null) {
                try{
                    nuevo=new EjerciciosDAO(nombre.getText(),Integer.parseInt(repeticiones.getText()), Integer.parseInt(series.getText()), descripcion.getText(), musculo.getValue());
                    if(nuevo.save()>0){
                        super.muestrinformacion("Creación ejercicios", "Ejercicio añadido correctamente", "");
                        Limpiar(event);
                    }
                }catch(Exception ex){
                    super.muestraerror("Error", "Compos con información no válida", "");
                }
            } else {
                super.muestraerror("Error al crear ejercicio", "Compruebe que no haya campos en blanco ", "");
            }

        } else {
            super.muestraerror("Error al crear ejercicio", "COmpruebe que no haya campos en blanco ", "");
        }

    }

}
