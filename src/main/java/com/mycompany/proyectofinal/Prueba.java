/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal;


import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Sexo;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;


/**
 *
 * @author Manueh
 */
public class Prueba {

    public static void main(String[] args) {
       /* Cliente c=new Cliente("sfsfd", "sfds", "fs", LocalDate.now(), Sexo.M,58f, 1.62f, Nivel_Ejercicio.MODERADAMENTE_ACTIVO);
        System.out.println(c.toString());
        c.setFoto(new byte[2]);
        
        ClientesDAO n=new ClientesDAO(c);
        n.save();
        ConexionUtilidades.cerrarConec();*/
       
        ClientesDAO n=new ClientesDAO("20225097R");
        System.out.println(n);
        
    }
}
