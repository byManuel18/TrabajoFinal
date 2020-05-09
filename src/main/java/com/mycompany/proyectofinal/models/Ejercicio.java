/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import com.mycompany.proyectofinal.enums.Grupo_Muscular;

/**
 *
 * @author Manueh
 */
public class Ejercicio {

    private String nombre;
    private int repeticiones;
    private int series;
    private String descripcion;
    private byte[] foto;
    private Grupo_Muscular musculo;    
   
    public Ejercicio() {
    }

    public Ejercicio(String nombre, int repeticiones, int series, String descripcion, Grupo_Muscular musculo) {
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.series = series;
        this.descripcion = descripcion;
        this.musculo = musculo;
    }
    
    
    
    public Grupo_Muscular getMusculo() {
        return musculo;
    }

    public void setMusculo(Grupo_Muscular musculo) {
        this.musculo = musculo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getRepeticiones() {
        return repeticiones;
    }
    
    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
    
    public int getSeries() {
        return series;
    }
    
    public void setSeries(int series) {
        this.series = series;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public byte[] getFoto() {
        return foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean igual = false;
        if (obj != null) {
            if (this == obj) {
                igual = true;
            } else {
                if (obj instanceof Ejercicio) {
                    Ejercicio n = (Ejercicio) obj;
                    igual = this.getNombre().equals(n.getNombre());
                }
            }
        }
        return igual;
    }
}
