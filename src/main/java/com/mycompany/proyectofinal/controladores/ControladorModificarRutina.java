/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.EjerciciosDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.models.Ejercicio;
import com.mycompany.proyectofinal.utils.ApoyoClientes;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.ImageIcon;

/**
 *  Clasee que controla la vista de modificar rutina 
 * @author Manueh
 */
public class ControladorModificarRutina extends General {

    @FXML
    private TextField textobusqueda;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<Ejercicio> tabla_ejercicios_dis;
    @FXML
    private TableColumn<Ejercicio, String> nombre_ejer_dis;
    @FXML
    private TableColumn<Ejercicio, String> descr_ejer_dis;
    @FXML
    private TableColumn<Ejercicio, String> grupomusc_ejer_dis;
    @FXML
    private TableColumn<Ejercicio, Integer> series_ejer_dis;
    @FXML
    private TableColumn<Ejercicio, Integer> repeticiones_ejer_dis;
    @FXML
    private TableColumn<Ejercicio, String> nombre_ejer_tene;
    @FXML
    private TableColumn<Ejercicio, String> descr_ejer_tene;
    @FXML
    private TableColumn<Ejercicio, String> grupomusc_ejer_tene;
    @FXML
    private TableColumn<Ejercicio, Integer> series_ejer_tene;
    @FXML
    private TableColumn<Ejercicio, Integer> repeticiones_ejer_tene;
    @FXML
    private TableView<Ejercicio> tabla_ejercicios_tene;

    private ObservableList<Ejercicio> data_ejer_dis;
    private ObservableList<Ejercicio> data_ejer_tener;
    private LocalDate fecha_busqueda_ini;
    private Cliente cli;
    /**
     * Inicializa la tablas de los ejercicios disponible y la tabla de los ejercicios que tenemos en un dñia si previamente hemos puesto una 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data_ejer_dis = FXCollections.observableArrayList();
        this.data_ejer_tener = FXCollections.observableArrayList();
        cli = ApoyoClientes.getCliente();
        fecha_busqueda_ini = ApoyoClientes.getFecha_consulta();
        if (fecha_busqueda_ini != null) {
            fecha.setValue(fecha_busqueda_ini);
            MostrarRutina();
        }
        List<Ejercicio> p = EjerciciosDAO.ListAll();
        data_ejer_dis.addAll(p);

        if (!p.isEmpty()) {

            this.nombre_ejer_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });

            this.descr_ejer_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getDescripcion());
            });
            this.grupomusc_ejer_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMusculo().toString());
            });
            this.series_ejer_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getSeries());
            });
            this.repeticiones_ejer_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getRepeticiones());
            });
        } else {
            for (int i = 0; i < tabla_ejercicios_dis.getItems().size(); i++) {
                tabla_ejercicios_dis.getItems().clear();
            }
        }

        tabla_ejercicios_dis.setItems(data_ejer_dis);
    }
    /**
     * Muestra nuestar rutina en la tabla
     */
    @FXML
    public void MostrarRutina() {
        if (fecha != null && fecha.getValue() != null) {
            List<Ejercicio> p = EjerciciosDAO.listarRutinaEjer(cli.getDni(), fecha.getValue().toString());
            data_ejer_tener.addAll(p);
            if (!p.isEmpty()) {
                this.nombre_ejer_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
                });
                this.descr_ejer_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getDescripcion());
                });
                this.grupomusc_ejer_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getMusculo().toString());
                });
                this.series_ejer_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getSeries());
                });
                this.repeticiones_ejer_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getRepeticiones());
                });
            } else {
                for (int i = 0; i < tabla_ejercicios_tene.getItems().size(); i++) {
                    tabla_ejercicios_tene.getItems().clear();
                }
            }
        }
        tabla_ejercicios_tene.setItems(data_ejer_tener);
    }
    /**
     * Vuelve al lobby
     * @param event 
     */
    @FXML
    private void Volver(ActionEvent event) {
        try {
            App.setRoot(Escenas.LOBBY.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(ControladorModificarRutina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Añade un ejercicio a la rutina especificada 
     * @param event 
     */
    @FXML
    private void AñadirEjer(ActionEvent event) {
        Ejercicio n=tabla_ejercicios_dis.getSelectionModel().getSelectedItem();
        if(n!=null&&fecha!=null&&fecha.getValue()!=null){
            data_ejer_tener.add(n);
            EjerciciosDAO.añadiralaruttina(cli.getDni(), fecha.getValue().toString(), n.getId());
        }
    }
    /**
     * Elimina un ejercicio de la dieta 
     * @param event 
     */
    @FXML
    private void EliminarEjer(ActionEvent event) {
        Ejercicio n=tabla_ejercicios_tene.getSelectionModel().getSelectedItem();
        if(n!=null&&fecha!=null&&fecha.getValue()!=null){
            data_ejer_tener.remove(n);
            EjerciciosDAO.removeejerderutina(cli.getDni(), fecha.getValue().toString(), n.getId());
        }
    }
    /**
     * Busca un ejercico de la lista 
     * @param event 
     */
    @FXML
    private void Busqueda(ActionEvent event) {
        List<Ejercicio> p =EjerciciosDAO.ListAll(textobusqueda.getText());
        data_ejer_dis.clear();
        data_ejer_dis.addAll(p);
    }
}
