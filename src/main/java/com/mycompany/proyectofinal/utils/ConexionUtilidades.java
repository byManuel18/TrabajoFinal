/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import com.mycompany.proyectofinal.models.Canal;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Manueh
 */
public class ConexionUtilidades {
    public static java.sql.Connection connect(Canal c) throws ClassNotFoundException, SQLException{
        java.sql.Connection conn=null;
        
        if(c==null){
            return null;
        }
        Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://"+c.getDireccion()+":"+c.getPuerto()+"/"+c.getBd()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",c.getUsuario(),c.getContraseña());
        
        return conn;
    }
}
