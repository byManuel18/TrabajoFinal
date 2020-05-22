/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import java.time.LocalDate;

/**
 *
 * @author Manueh
 */
public class ApoyoClientes {
    private static ClientesDAO client=null;
    private static LocalDate fecha_consulta=null;
    
    public static void setCliente(ClientesDAO c){
        client=c;
    }
    
    public static ClientesDAO getCliente(){
        return client;
    }

    public static LocalDate getFecha_consulta() {
        return fecha_consulta;
    }

    public static void setFecha_consulta(LocalDate d) {
        fecha_consulta=d;
    }

    
}
