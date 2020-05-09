/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author Manueh
 */
public class Rutina {
    private Cliente clie;
    private ArrayList<Ejercicio> ejercicios;
    private ArrayList<Producto> productos;
    private LocalDate fecha;

    private Rutina() {
    }

    public Rutina(Cliente clie, LocalDate fecha) {
        this.clie = clie;
        this.fecha = fecha;
        ejercicios=new ArrayList<>();
        productos=new ArrayList<>();
    }
    
    public boolean AñadirEjercicio(Ejercicio ej){
        return ejercicios.add(ej);
    }
    public boolean AñadirProducto(Producto p){
        return productos.add(p);
    }

    public Cliente getClie() {
        return clie;
    }

    public void setClie(Cliente clie) {
        this.clie = clie;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    
}
