/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.enums;

/**
 * Clase enum que nos sirve para elegir entre las vistas con mayor versatilidad
 * @author Manueh
 */
public enum Escenas {
    ROOT("view/root"),
    PRIMARY("view/primary"),
    SECONDARY("view/secondary"),
    LOBBY("view/lobby"),
    MODIFICARDIETA("view/ADDProductosaDieta"),
    MODIFICARRUTINA("view/AddEjerRutina"),
    VENTANAADMINISTRADOR("view/VentanaAdmin"),
    EDITARPERFIL("view/EditarPerfil"),
    AÑADIREJER("view/AnadirEjer"),
    AÑADIRPRO("view/AnadirProducto"),
    ;

    private String url;

    Escenas(String fxmlFile) {
        this.url = fxmlFile;
    }

    public String getUrl() {
        return url;
    }
}
