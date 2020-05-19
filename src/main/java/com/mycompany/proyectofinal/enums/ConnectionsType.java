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
public enum ConnectionsType {

    MYSQL("mySQL"),
    H2("H2");
    
    private String type;

    private ConnectionsType(String type) {
        this.type=type;
    }
    public String getType(){
        return this.type;
    } 
}
