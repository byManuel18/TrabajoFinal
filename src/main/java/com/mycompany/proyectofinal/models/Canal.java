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
public class Canal {
    private String usuario;
    private String contraseña;
    private String bd;
    private String zona_horaria;
    private String direccion;
    private String puerto;

    public Canal(String usuario, String contraseña, String bd, String zona_horaria, String direccion, String puerto) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.bd = bd;
        this.zona_horaria = zona_horaria;
        this.direccion = direccion;
        this.puerto = puerto;
    }

    public Canal() {
        this("","","","","","");
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getZona_horaria() {
        return zona_horaria;
    }

    public void setZona_horaria(String zona_horaria) {
        this.zona_horaria = zona_horaria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    @Override
    public String toString() {
        return "Canal{" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", bd=" + bd + ", zona_horaria=" + zona_horaria + ", direccion=" + direccion + ", puerto=" + puerto + '}';
    }
    
    
}
