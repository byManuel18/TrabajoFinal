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
public enum Factor_Correccion {
    Mb_Sedentarias(1.2f),
    Mb_Ligeramente_Activas(1.375f),
    Mb_Moderadamente_Activas(1.55f),
    Mb_Muy_Activas(1.725f),
    Mb_Hiperactivas(1.9f);
    
    
    private float factor;


    Factor_Correccion(float factor) {
        this.factor = factor;
    }

    public float getFactor() {
        return factor;
    }
    
    
}
