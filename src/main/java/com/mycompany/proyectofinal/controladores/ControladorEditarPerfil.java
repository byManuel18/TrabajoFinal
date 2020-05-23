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
import com.mycompany.proyectofinal.utils.ApoyoClientes;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * Clase que controla la vista de editar perfil 
 * @author Manueh
 */
public class ControladorEditarPerfil extends General {

    @FXML
    private ImageView img;
    @FXML
    private ChoiceBox<Sexo> sexo;
    @FXML
    private ChoiceBox<Nivel_Ejercicio> ejercicio;
    @FXML
    private DatePicker fechanacimi;
    @FXML
    private PasswordField contasena;
    @FXML
    private TextField nombre;
    @FXML
    private TextField peso;
    @FXML
    private TextField altura;
    
    private Cliente clie;
    
    /**
     * ada valor a todos los campos con los datos del cliente introducido y le da valores a los choicebox
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clie=ApoyoClientes.getCliente();
        img.setImage(UtilidadesGenerales.pasardebitsaimage(clie.getFoto()));
        fechanacimi.setValue(clie.getFecha_nacimiento());
        contasena.setText(clie.getContraseña());
        nombre.setText(clie.getNombre());
        peso.setText(Float.toString(clie.getPeso()));
        altura.setText(Float.toString(clie.getAltura()));
        if (sexo != null) {
            for (Sexo s : Sexo.values()) {
                sexo.getItems().add(s);
            }
        }
        sexo.setValue(clie.getS());
        if ( ejercicio!= null) {
            for (Nivel_Ejercicio n : Nivel_Ejercicio.values()) {
                ejercicio.getItems().add(n);
            }
        }
        ejercicio.setValue(clie.getNivel_ejer());
        
    }
    /**
     * Velve a la vista del lobby
     * @param event 
     */
    @FXML
    private void Volver(ActionEvent event){
        try {
            App.setRoot(Escenas.LOBBY.getUrl());
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
     * Guarda los nuevos datos introducidos 
     */
    @FXML
    private void Guardar(){
        ClientesDAO guar= new ClientesDAO(clie);
        if(img!=null&&img.getImage()!=null){
            guar.setFoto(UtilidadesGenerales.pasarimagenabites(img.getImage()));
        }
        if(nombre!=null&&nombre.getText().length()>0){
            guar.setNombre(nombre.getText());
        }
        if(contasena!=null&&contasena.getText().length()>0){
            guar.setContraseña(contasena.getText());
        }
        if(fechanacimi!=null){
            guar.setFecha_nacimiento(fechanacimi.getValue());
        }
        if(altura!=null&&altura.getText().length()>0){
            try{
                guar.setAltura(Float.parseFloat(altura.getText()));
            }catch(Exception ex){
                
            }
            
        }
        if(peso!=null&&altura.getText().length()>0){
            try{
                guar.setPeso(Float.parseFloat(peso.getText()));
            }catch(Exception ex){
                
            } 
        }
        if(sexo!=null){
            guar.setS(sexo.getValue());
        }
        if(ejercicio!=null){
            guar.setNivel_ejer(ejercicio.getValue());
        }
        if(guar.update()>0){
            ApoyoClientes.setCliente(guar);
            super.muestrinformacion("Editar Ciente", "Cliente editado correctamenet", "");
            try {
                App.setRoot(Escenas.LOBBY.getUrl());
            } catch (IOException ex) {
                Logger.getLogger(ControladorEditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    /**
     * Elimina el cliente de la base de datos y vuelve al lobby
     */
    @FXML
    private void Eliminar(){
        ClientesDAO eli=new ClientesDAO(clie.getDni());
        if(super.confirm(eli.getDni())){
            eli.remove();
            try {
                App.setRoot(Escenas.PRIMARY.getUrl());
            } catch (IOException ex) {
                Logger.getLogger(ControladorEditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

}
