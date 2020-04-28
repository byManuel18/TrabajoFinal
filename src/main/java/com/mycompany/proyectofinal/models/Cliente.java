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
    
    
}
