/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.DAOS.EjerciciosDAO;
import com.mycompany.proyectofinal.DAOS.ProductoDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.models.Ejercicio;
import com.mycompany.proyectofinal.models.Producto;
import com.mycompany.proyectofinal.utils.ApoyoClientes;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Manueh
 */
public class ControladorLobby extends General {

    @FXML
    private ImageView img;
    @FXML
    private Button eliminar_pro;
    @FXML
    private Button eliminar_ejer;
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
    @FXML
    private DatePicker fecha_busqueda;
    @FXML
    private TableView<Ejercicio> tabla_ejercicios;
    @FXML
    private TableColumn<Ejercicio, String> nombre_ejer;
    @FXML
    private TableColumn<Ejercicio, String> descr_ejer;
    @FXML
    private TableColumn<Ejercicio, ImageIcon> foto_ejer;
    @FXML
    private TableColumn<Ejercicio, String> grupomusc_ejer;
    @FXML
    private TableColumn<Ejercicio, Integer> series_ejer;
    @FXML
    private TableColumn<Ejercicio, Integer> repeticiones_ejer;
    @FXML
    private TableView<Producto> tabla_productos;
    @FXML
    private TableColumn<Producto, String> nombre_pro;
    @FXML
    private TableColumn<Producto, String> marca_pro;
    @FXML
    private TableColumn<Producto, ImageIcon> foto_pro;
    @FXML
    private TableColumn<Producto, Float> calorias_pro;
    @FXML
    private TableColumn<Producto, Float> grasas_pro;
    @FXML
    private TableColumn<Producto, Float> grasassatu_pro;
    @FXML
    private TableColumn<Producto, Float> hidratos_pro;
    @FXML
    private TableColumn<Producto, Float> azucar_pro;
    @FXML
    private TableColumn<Producto, Float> sodio_pro;
    @FXML
    private TableColumn<Producto, Float> proteinas_pro;
    @FXML
    private TableColumn<Producto, Float> fibra_pro;

    private ObservableList<Producto> data_pro;
    private ObservableList<Ejercicio> data_ejer;
    private ClientesDAO cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data_ejer = FXCollections.observableArrayList();
        this.data_pro = FXCollections.observableArrayList();
        cliente = ApoyoClientes.getCliente();
        img.setImage(UtilidadesGenerales.pasardebitsaimage(cliente.getFoto()));

        nombre.setText(cliente.getNombre());
        dni.setText(cliente.getDni());
        altura.setText(Float.toString(cliente.getAltura()));
        peso.setText(Float.toString(cliente.getPeso()));
        calorias_nece.setText(Float.toString(cliente.getCalorias_necesarias()));
        sexo.setText(cliente.getS().toString());
        imc.setText(cliente.getIndice_masa().toString());
        objeti.setText(cliente.getObjetivo().toString());
        int edad = LocalDate.now().compareTo(cliente.getFecha_nacimiento());
        años.setText(Integer.toString(edad));
        

    }

    @FXML
    private void EliminarPro(ActionEvent event) {
        Producto aeliminar=tabla_productos.getSelectionModel().getSelectedItem();
        if(aeliminar!=null){
            data_pro.remove(aeliminar);
            ProductoDAO.remuveDieta(cliente.getDni(),fecha_busqueda.getValue().toString(),aeliminar.getId());
        }else{
            
        }
    }

    @FXML
    private void EliminarEjer(ActionEvent event) {
        Ejercicio aeliminar=tabla_ejercicios.getSelectionModel().getSelectedItem();
        if(aeliminar!=null){
            data_ejer.remove(aeliminar);
            System.out.println(cliente.getDni()+fecha_busqueda.getValue().toString()+aeliminar.getId());
            EjerciciosDAO.removeejerderutina(cliente.getDni(),fecha_busqueda.getValue().toString(),aeliminar.getId());
        }else{
            
        }
    }

    @FXML
    private void ActualizarTodo(ActionEvent event) {
        String fecha = fecha_busqueda.getValue().toString();
        actualizarTablaEjer(cliente.getDni(), fecha);
        actualizatTablaPro(cliente.getDni(),fecha);

    }

    private void actualizarTablaEjer(String f, String d) {
        List<Ejercicio> ejer = EjerciciosDAO.listarRutinaEjer(f, d);
        this.data_ejer.addAll(ejer);
        if (!ejer.isEmpty()) {
            this.nombre_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
            /*this.foto_ejer.setCellValueFactory(eachRowData ->{
                      byte[] bit=eachRowData.getValue().getFoto();
                     ImageIcon imagennu=UtilidadesGenerales.pasardebitsaimageImageIcon(bit);
                    return new SimpleObjectProperty<>(imagennu);
                     });*/
            this.descr_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getDescripcion());
            });
            this.grupomusc_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMusculo().toString());
            });
            this.series_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getSeries());
            });
            this.repeticiones_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getRepeticiones());
            });

        } else {
            for (int i = 0; i < tabla_ejercicios.getItems().size(); i++) {
                tabla_ejercicios.getItems().clear();
            }
        }
        this.tabla_ejercicios.setItems(data_ejer);

    }

    private void actualizatTablaPro(String f, String d) {
        List<Producto> prod = ProductoDAO.ListarDietaDia(f, d);
        this.data_pro.addAll(prod);
        if (!prod.isEmpty()) {
            this.nombre_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
            this.marca_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMarca());
            });
            this.calorias_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getCalorias());
            });
            this.grasas_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas());
            });
            this.grasassatu_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas_saturadas());
            });
            this.azucar_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getAzucaar());
            });
            this.sodio_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getSodio());
            });
            this.proteinas_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getProteinas());
            });
            this.fibra_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getFibra());
            });
            this.hidratos_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getHidratos());
            });
            /*this.foto_pro.setCellValueFactory(eachRowData -> {
                byte[] bit = eachRowData.getValue().getFoto();
                ImageIcon imagennu = UtilidadesGenerales.pasardebitsaimageImageIcon(bit);
                return new SimpleObjectProperty<>(imagennu);
            });*/
        } else {
            for (int i = 0; i < tabla_productos.getItems().size(); i++) {
                tabla_productos.getItems().clear();
            }
        }
        this.tabla_productos.setItems(data_pro);
    }
    
    @FXML
    private void ModificarDieta(ActionEvent event){
        try {
            if(fecha_busqueda!=null&&fecha_busqueda.getValue()!=null){
                ApoyoClientes.setFecha_consulta(fecha_busqueda.getValue());
            }
            App.setRoot(Escenas.MODIFICARDIETA.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(ControladorLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
