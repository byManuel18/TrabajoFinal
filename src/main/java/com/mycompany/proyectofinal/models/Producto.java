/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

/**
 *
 * @author Manueh
 */
public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private float calorias;
    private float grasas;
    private float grasas_saturadas;
    private float hidratos;
    private float azucaar;
    private float proteinas;
    private float sodio;
    private float fibra;
    private byte[] foto;
    
    private Producto() {
    }

    public Producto(int id, String nombre, String marca, float calorias, float grasas, float grasas_saturadas, float hidratos, float azucaar, float proteinas, float sodio, float fibra) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.calorias = calorias;
        this.grasas = grasas;
        this.grasas_saturadas = grasas_saturadas;
        this.hidratos = hidratos;
        this.azucaar = azucaar;
        this.proteinas = proteinas;
        this.sodio = sodio;
        this.fibra = fibra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
    }

    public float getGrasas_saturadas() {
        return grasas_saturadas;
    }

    public void setGrasas_saturadas(float grasas_saturadas) {
        this.grasas_saturadas = grasas_saturadas;
    }

    public float getHidratos() {
        return hidratos;
    }

    public void setHidratos(float hidratos) {
        this.hidratos = hidratos;
    }

    public float getAzucaar() {
        return azucaar;
    }

    public void setAzucaar(float azucaar) {
        this.azucaar = azucaar;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getSodio() {
        return sodio;
    }

    public void setSodio(float sodio) {
        this.sodio = sodio;
    }

    public float getFibra() {
        return fibra;
    }

    public void setFibra(float fibra) {
        this.fibra = fibra;
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
    public boolean equals(Object obj) {
        boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Producto){
                    Producto n= (Producto) obj;
                    if(this.getId()-n.getId()==0){
                        igual=true;
                    }
                }
            }
        }
        return igual;
    } 
}
