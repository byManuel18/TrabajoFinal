/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.controladores;

import com.mycompany.proyectofinal.App;
import com.mycompany.proyectofinal.DAOS.EjerciciosDAO;
import com.mycompany.proyectofinal.DAOS.ProductoDAO;
import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.enums.Grupo_Muscular;
import com.mycompany.proyectofinal.models.Ejercicio;
import com.mycompany.proyectofinal.models.Producto;
import com.mycompany.proyectofinal.utils.UtilidadesGenerales;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;

/**
 *
 * @author Manueh
 */
public class ControladorAdmin extends General {

    @FXML
    private TextField busqueda_ejer;
    @FXML
    private TextField busqueda_pro;
    @FXML
    private TableView<Ejercicio> tabla_ejer;
    @FXML
    private TableView<Producto> tabla_pro;
    @FXML
    private TableColumn<Producto, String> nombre_pro;
    @FXML
    private TableColumn<Producto, String> marca_pro;
    @FXML
    private TableColumn<Producto, ImageView> foto_pro;
    @FXML
    private TableColumn<Producto, String> calorias_pro;
    @FXML
    private TableColumn<Producto, String> grasas_pro;
    @FXML
    private TableColumn<Producto, String> grasassatu_pro;
    @FXML
    private TableColumn<Producto, String> hidratos_pro;
    @FXML
    private TableColumn<Producto, String> azucar_pro;
    @FXML
    private TableColumn<Producto, String> sodio_pro;
    @FXML
    private TableColumn<Producto, String> proteinas_pro;
    @FXML
    private TableColumn<Producto, String> fibra_pro;
    @FXML
    private TableColumn<Ejercicio, String> nombre_ejer;
    @FXML
    private TableColumn<Ejercicio, String> descr_ejer;
    @FXML
    private TableColumn<Ejercicio, ImageView> foto_ejer;
    @FXML
    private TableColumn<Ejercicio, String> grupomusc_ejer;
    @FXML
    private TableColumn<Ejercicio, String> series_ejer;
    @FXML
    private TableColumn<Ejercicio, String> repeticiones_ejer;

    private ObservableList<Producto> data_pro;
    private ObservableList<Ejercicio> data_ejer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data_ejer = FXCollections.observableArrayList();
        this.data_pro = FXCollections.observableArrayList();
        List<Ejercicio> ejer = EjerciciosDAO.ListAll();
        data_ejer.addAll(ejer);
        if (!ejer.isEmpty()) {
            this.nombre_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
            this.foto_ejer.setCellValueFactory(eachRowData -> {
                byte[] bit = eachRowData.getValue().getFoto();
                ImageView fotonu = new ImageView(UtilidadesGenerales.pasardebitsaimage(bit));
                return new SimpleObjectProperty<>(fotonu);
            });
            this.descr_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getDescripcion());
            });
            this.grupomusc_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMusculo().toString());
            });
            this.series_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Integer.toString(eachRowData.getValue().getSeries()));
            });
            this.repeticiones_ejer.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Integer.toString(eachRowData.getValue().getRepeticiones()));
            });
        }
        nombre_ejer.setCellFactory(TextFieldTableCell.forTableColumn());
        nombre_ejer.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ejercicio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ejercicio, String> t) {

                Ejercicio selected = (Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                EjerciciosDAO dao = new EjerciciosDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        descr_ejer.setCellFactory(TextFieldTableCell.forTableColumn());
        descr_ejer.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ejercicio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ejercicio, String> t) {

                Ejercicio selected = (Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setDescripcion(t.getNewValue());  //<<- update lista en vista

                EjerciciosDAO dao = new EjerciciosDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        repeticiones_ejer.setCellFactory(TextFieldTableCell.forTableColumn());
        repeticiones_ejer.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ejercicio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ejercicio, String> t) {

                Ejercicio selected = (Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setRepeticiones(Integer.parseInt(t.getNewValue()));  //<<- update lista en vista

                    EjerciciosDAO dao = new EjerciciosDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        grupomusc_ejer.setCellFactory(TextFieldTableCell.forTableColumn());
        grupomusc_ejer.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ejercicio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ejercicio, String> t) {

                Ejercicio selected = (Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setMusculo(Grupo_Muscular.valueOf(t.getNewValue()));//<<- update lista en vista
                    EjerciciosDAO dao = new EjerciciosDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        series_ejer.setCellFactory(TextFieldTableCell.forTableColumn());
        series_ejer.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ejercicio, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ejercicio, String> t) {

                Ejercicio selected = (Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setSeries(Integer.parseInt(t.getNewValue()));  //<<- update lista en vista

                    EjerciciosDAO dao = new EjerciciosDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );

        tabla_ejer.setEditable(true);
        tabla_ejer.setItems(data_ejer);

        List<Producto> prod = ProductoDAO.ListarAll();
        data_pro.addAll(prod);
        if (!prod.isEmpty()) {
            this.nombre_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
            });
            this.marca_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(eachRowData.getValue().getMarca());
            });
            this.calorias_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getCalorias()));
            });
            this.grasas_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getGrasas()));
            });
            this.grasassatu_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getGrasas_saturadas()));
            });
            this.azucar_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getAzucaar()));
            });
            this.sodio_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getAzucaar()));
            });
            this.proteinas_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getProteinas()));
            });
            this.fibra_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getFibra()));
            });
            this.hidratos_pro.setCellValueFactory(eachRowData -> {
                return new SimpleObjectProperty<>(Float.toString(eachRowData.getValue().getHidratos()));
            });
            this.foto_pro.setCellValueFactory(eachRowData -> {
                byte[] bit = eachRowData.getValue().getFoto();
                ImageView nuw = new ImageView(UtilidadesGenerales.pasardebitsaimage(bit));
                return new SimpleObjectProperty<>(nuw);
            });
        }

        nombre_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        nombre_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setNombre(t.getNewValue());  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        marca_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        marca_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setMarca(t.getNewValue());  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        calorias_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        calorias_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setCalorias(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        grasas_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        grasas_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setGrasas(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        grasassatu_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        grasassatu_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setGrasas_saturadas(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );

        hidratos_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        hidratos_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setHidratos(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        azucar_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        azucar_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setAzucaar(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );

        proteinas_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        proteinas_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setProteinas(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );
        sodio_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        sodio_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setSodio(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );

        fibra_pro.setCellFactory(TextFieldTableCell.forTableColumn());
        fibra_pro.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Producto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Producto, String> t) {

                Producto selected = (Producto) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());
                try {
                    selected.setFibra(Float.parseFloat(t.getNewValue()));  //<<- update lista en vista

                    ProductoDAO dao = new ProductoDAO(selected); //update en mysql
                    dao.save();
                } catch (Exception ex) {

                }

            }
        }
        );

        tabla_pro.setEditable(true);
        tabla_pro.setItems(data_pro);
    }

    @FXML
    private void buscarEjer() {
        List<Ejercicio> ejer = EjerciciosDAO.ListAll(busqueda_ejer.getText());
        data_ejer.clear();
        data_ejer.addAll(ejer);
    }

    @FXML
    private void buscarPro() {
        List<Producto> prod = ProductoDAO.buscar(busqueda_pro.getText());
        data_pro.clear();
        data_pro.addAll(prod);
    }

    @FXML
    private void EliminarPro(ActionEvent event) {
        Producto aeliminar = tabla_pro.getSelectionModel().getSelectedItem();
        if (aeliminar != null) {
            if (super.confirm(aeliminar.getNombre())) {
                data_pro.remove(aeliminar);
                ProductoDAO eli = new ProductoDAO(aeliminar);
                eli.remove();
            }
        } else {

        }
    }

    @FXML
    private void EliminarEjer(ActionEvent event) {
        Ejercicio aeliminar = tabla_ejer.getSelectionModel().getSelectedItem();
        if (aeliminar != null) {
            if (super.confirm(aeliminar.getNombre())) {
                data_ejer.remove(aeliminar);
                EjerciciosDAO eli = new EjerciciosDAO(aeliminar);
                eli.delete();
            }

        } else {

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

}
