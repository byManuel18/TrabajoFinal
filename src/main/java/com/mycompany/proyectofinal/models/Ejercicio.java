/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import com.mycompany.proyectofinal.enums.Grupo_Muscular;

/**
 * Clase de l objeto ejercicio 
 * @author Manueh
 */
public class Ejercicio {
    protected int id;
    protected String nombre;
    protected int repeticiones;
    protected int series;
    protected String descripcion;
    protected byte[] foto;
    protected Grupo_Muscular musculo;    
   
    public Ejercicio() {
        this(-1,"",0,0,"",Grupo_Muscular.PECHO);
    }

    public Ejercicio(int id,String nombre, int repeticiones, int series, String descripcion, Grupo_Muscular musculo) {
        this.id=id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ejercicio other = (Ejercicio) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejercicio{" + "id=" + id + ", nombre=" + nombre + ", repeticiones=" + repeticiones + ", series=" + series + ", descripcion=" + descripcion + ", foto=" + foto + ", musculo=" + musculo + '}';
    }
    
    
    
}
