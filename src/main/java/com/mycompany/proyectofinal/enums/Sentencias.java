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
    VERTODOSLOSCLIENTES("SELECT * FROM clientes");
    
    private String senten;

    private Sentencias(String senten) {
        this.senten = senten;
    }

    public String getSenten() {
        return senten;
    }
}
