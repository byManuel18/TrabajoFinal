/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal;


import com.mycompany.proyectofinal.DAOS.ClientesDAO;
import com.mycompany.proyectofinal.DAOS.EjerciciosDAO;
import com.mycompany.proyectofinal.DAOS.ProductoDAO;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Sexo;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.models.Ejercicio;
import com.mycompany.proyectofinal.models.Producto;
import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javafx.scene.image.Image;


/**
 *
 * @author Manueh
 */
public class Prueba {

    public static void main(String[] args) {
        ClientesDAO n=new ClientesDAO("20225097R");
        byte[] c=n.getFoto();
        System.out.println(c);
        Image i = new Image(new ByteArrayInputStream(c));
        System.out.println(i.getProgress());
        ConexionUtilidades.cerrarConec();
                
    }
}
