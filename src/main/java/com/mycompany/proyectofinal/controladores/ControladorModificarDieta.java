/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.ProductoDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.models.Producto;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.ImageIcon;

/**
 * Clase que controla la vista de modificar una dieta e un cliente 
 * @author Manueh
 */
public class ControladorModificarDieta extends General {
    
    @FXML
    private TextField textobusqueda;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<Producto> tabla_productos_dis;
    @FXML
    private TableColumn<Producto, String> nombre_pro_dis;
    @FXML
    private TableColumn<Producto, String> marca_pro_dis;
    @FXML
    private TableColumn<Producto, Float> calorias_pro_dis;
    @FXML
    private TableColumn<Producto, Float> grasas_pro_dis;
    @FXML
    private TableColumn<Producto, Float> grasassatu_pro_dis;
    @FXML
    private TableColumn<Producto, Float> hidratos_pro_dis;
    @FXML
    private TableColumn<Producto, Float> azucar_pro_dis;
    @FXML
    private TableColumn<Producto, Float> sodio_pro_dis;
    @FXML
    private TableColumn<Producto, Float> proteinas_pro_dis;
    @FXML
    private TableColumn<Producto, Float> fibra_pro_dis;
    @FXML
    private TableView<Producto> tabla_productos_tene;
    @FXML
    private TableColumn<Producto, String> nombre_pro_tene;
    @FXML
    private TableColumn<Producto, String> marca_pro_tene;
    @FXML
    private TableColumn<Producto, Float> calorias_pro_tene;
    @FXML
    private TableColumn<Producto, Float> grasas_pro_tene;
    @FXML
    private TableColumn<Producto, Float> grasassatu_pro_tene;
    @FXML
    private TableColumn<Producto, Float> hidratos_pro_tene;
    @FXML
    private TableColumn<Producto, Float> azucar_pro_tene;
    @FXML
    private TableColumn<Producto, Float> sodio_pro_tene;
    @FXML
    private TableColumn<Producto, Float> proteinas_pro_tene;
    @FXML
    private TableColumn<Producto, Float> fibra_pro_tene;

    private LocalDate fecha_busqueda_ini;
    private Cliente cli;
    private ObservableList<Producto> data_pro_dis;
    private ObservableList<Producto> data_pro_tene;
    
    /**
     * Inicializa la tablas de los productos disponible y la tabla de los productos que tenemos en un dñia si previamente hemos puesto una 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data_pro_dis = FXCollections.observableArrayList();
        this.data_pro_tene = FXCollections.observableArrayList();
        cli=ApoyoClientes.getCliente();
        fecha_busqueda_ini = ApoyoClientes.getFecha_consulta();
        if (fecha_busqueda_ini != null) {
            fecha.setValue(fecha_busqueda_ini);
            MostarDieta();
        }
        List<Producto> p = ProductoDAO.ListarAll();
        data_pro_dis.addAll(p);

        if (!p.isEmpty()) {
            this.nombre_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
            this.marca_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMarca());
            });
            this.calorias_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getCalorias());
            });
            this.grasas_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas());
            });
            this.grasassatu_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas_saturadas());
            });
            this.azucar_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getAzucaar());
            });
            this.sodio_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getSodio());
            });
            this.proteinas_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getProteinas());
            });
            this.fibra_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getFibra());
            });
            this.hidratos_pro_dis.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getHidratos());
            });

        } else {
            for (int i = 0; i < tabla_productos_dis.getItems().size(); i++) {
                tabla_productos_dis.getItems().clear();
            }
        }

        tabla_productos_dis.setItems(data_pro_dis);

    }
    /**
     * Muestra los productos de una dieta concreata a partir de la fecha y el dni 
     */
    @FXML
    private void MostarDieta() {
        if (fecha!=null&&fecha.getValue() != null) {
            List<Producto> p = ProductoDAO.ListarDietaDia(ApoyoClientes.getCliente().getDni(), fecha.getValue().toString());
            data_pro_tene.addAll(p);

            if (!p.isEmpty()) {
                this.nombre_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
                });
                this.marca_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getMarca());
                });
                this.calorias_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getCalorias());
                });
                this.grasas_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas());
                });
                this.grasassatu_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getGrasas_saturadas());
                });
                this.azucar_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getAzucaar());
                });
                this.sodio_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getSodio());
                });
                this.proteinas_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getProteinas());
                });
                this.fibra_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getFibra());
                });
                this.hidratos_pro_tene.setCellValueFactory(eachRowData -> {
                    return new SimpleObjectProperty<>(eachRowData.getValue().getHidratos());
                });
            } else {
                for (int i = 0; i < tabla_productos_tene.getItems().size(); i++) {
                    tabla_productos_tene.getItems().clear();
                }
            }

            tabla_productos_tene.setItems(data_pro_tene);
        }

    }
    /**
     * Nos devuelve a la vista del lobby
     * @param event 
     */
    @FXML
    private void Volver(ActionEvent event) {
        try {
            App.setRoot(Escenas.LOBBY.getUrl());
        } catch (IOException ex) {
            Logger.getLogger(ControladorModificarDieta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Elimina un producto de la lista si está seleccionado 
     * @param event 
     */
    @FXML
    private void EliminarPro(ActionEvent event) {
        Producto aeliminar=tabla_productos_tene.getSelectionModel().getSelectedItem();
        if(aeliminar!=null&&fecha!=null&&fecha.getValue()!=null){
            data_pro_tene.remove(aeliminar);
            ProductoDAO.remuveDieta(cli.getDni(),fecha.getValue().toString(),aeliminar.getId());
        }else{
            
        }
    }
    /**
     * Añade un producto a la lista seleccionado uno de la lista disponibles
     * @param event 
     */
    @FXML
    private void AñadirPro(ActionEvent event) {
        Producto aañadir=tabla_productos_dis.getSelectionModel().getSelectedItem();
        if(aañadir!=null&&fecha!=null&&fecha.getValue()!=null){
            data_pro_tene.add(aañadir);
            ProductoDAO.AddalaDieta(cli.getDni(),fecha.getValue().toString(),aañadir.getId());
        }else{
            
        }
    }
    /**
     * Busca un producto mediante un string 
     * @param event 
     */
    @FXML
    private void  Busqueda(ActionEvent event){
        
            List<Producto> p = ProductoDAO.buscar(textobusqueda.getText());
            data_pro_dis.clear();
            data_pro_dis.addAll(p);
            
        
    }

}
