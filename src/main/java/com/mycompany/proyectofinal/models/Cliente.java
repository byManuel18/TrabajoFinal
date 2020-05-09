/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import com.mycompany.proyectofinal.enums.IMC;
import com.mycompany.proyectofinal.enums.Objetivo;
import java.time.LocalDate;

/**
 *
 * @author Manueh
 */
public class Cliente {
    private String nombre;
    private String dni;
    private String contraseña;
    private LocalDate fecha_nacimiento;
    private byte[] foto;
    private float peso;
    private float altura;
    private IMC indice_masa;
    private Objetivo objetivo;

    private Cliente() {
    }

    public Cliente(String nombre, String dni, String contraseña, LocalDate fecha_nacimiento,float peso, float altura) {
        this.nombre = nombre;
        this.dni = dni;
        this.contraseña = contraseña;
        this.fecha_nacimiento = fecha_nacimiento;
        this.peso = peso;
        this.altura = altura;
        CalculaIMC();
        CalcularObjetivo();
    }
    
    

    private void CalculaIMC(){
        float nume=0;
        if(nume<18.5){
            this.indice_masa=IMC.BAJO_PESO;
        }else if(nume>=18.5&&nume<=24.9){
            this.indice_masa=IMC.NORMAL;
        }else if(nume>=25&&nume<=29.9){
            this.indice_masa=IMC.OBESO;
        }else{
            this.indice_masa=IMC.SOBREPESO;
        }
    }
    
    private void CalcularObjetivo(){
        switch(this.indice_masa){
            case BAJO_PESO:
                this.objetivo=Objetivo.Engordar;
                break;
            case NORMAL:
                this.objetivo=Objetivo.Mantener;
                break;
            case OBESO:
                this.objetivo=Objetivo.Adelgazar;
                break;
            case SOBREPESO:
                this.objetivo=Objetivo.Adelgazar;
                break;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public IMC getIndice_masa() {
        return indice_masa;
    }

    public void setIndice_masa(IMC indice_masa) {
        this.indice_masa = indice_masa;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual=false;
        
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Cliente){
                    Cliente n= (Cliente) obj;
                    igual=this.getDni().equals(n.getContraseña());
                }
            }
        }
        return igual;
    }

}
