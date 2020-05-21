/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.utils.ApoyoClientes;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Manueh
 */
public class ControladorLobby extends General{
    @FXML
    private ImageView img;
    @FXML
    private Label nombre;
    @FXML
    private Label dni;
    @FXML
    private Label años;
    @FXML
    private Label altura;
    @FXML
    private Label peso;
    @FXML
    private Label sexo;
    @FXML
    private Label imc;
    @FXML
    private Label objeti;
    @FXML
    private Label calorias_nece;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientesDAO clien=ApoyoClientes.getCliente();
        System.out.println(clien);
        img.setImage(UtilidadesGenerales.pasardebitsaimage(clien.getFoto()));
        
        nombre.setText(clien.getNombre());
        dni.setText(clien.getDni());
        altura.setText(Float.toString(clien.getAltura()));
        peso.setText(Float.toString(clien.getPeso()));
        calorias_nece.setText(Float.toString(clien.getCalorias_necesarias()));
        sexo.setText(clien.getS().toString());
        imc.setText(clien.getIndice_masa().toString());
        objeti.setText(clien.getObjetivo().toString());
        int edad = LocalDate.now().compareTo(clien.getFecha_nacimiento());
        años.setText(Integer.toString(edad));
        System.out.println("eda: "+edad);
        
    }
    
}
