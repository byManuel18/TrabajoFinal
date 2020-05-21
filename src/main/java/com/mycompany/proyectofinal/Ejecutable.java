/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.utils.ConexionUtilidades;

/**
 *
 * @author Manueh
 */
public class Ejecutable {
    public static void main(String[] args) {
        App.main(args);
        ConexionUtilidades.cerrarConec();
        
    }
}
