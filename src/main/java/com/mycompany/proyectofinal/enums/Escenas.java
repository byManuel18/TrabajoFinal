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
public enum Escenas {
    ROOT("view/root"),
    PRIMARY("view/primary"),
    SECONDARY("view/secondary"),
    LOBBY("view/lobby");

    private String url;

    Escenas(String fxmlFile) {
        this.url = fxmlFile;
    }

    public String getUrl() {
        return url;
    }
}
