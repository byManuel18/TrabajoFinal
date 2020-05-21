/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.enums;

/**
 *
 * @author Manueh
 */
public enum Sentencias {
    VERTODOSLOSCLIENTES("SELECT * FROM cliente"),
    EXISTECLIENTE("SELECT * FROM cliente WHERE Dni=?"),
    SELECLIENTECONCRETO("SELECT * FROM cliente WHERE dni= ? and contaseña"),
    ISEERTARCLIENTES("INSERT INTO cliente (nombre,dni,Fecha_Nacimiento,Contraseña,Foto,Altura,Peso,IMC,OBJETIVO,Nivel_Ejercicio,Calorias_Necesarias,Sexo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
    ;
    private String senten;

    private Sentencias(String senten) {
        this.senten = senten;
    }

    public String getSenten() {
        return senten;
    }
}
