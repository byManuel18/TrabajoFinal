/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import com.mycompany.proyectofinal.models.Canal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manueh
 */
public class ConexionUtilidades {
    private static Connection _conec=null;
    private static String zona_horaria="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static java.sql.Connection connect(Canal c) throws ClassNotFoundException, SQLException{
        java.sql.Connection conn=null;
        
        if(c==null){
            return null;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://"+c.getDireccion()+":"+c.getPuerto()+"/"+c.getBd()+zona_horaria+" & characterEncoding = UTF-8 & maxAllowedPacket = 16777216",c.getUsuario(),c.getContraseña());
        
        return conn;
    }
    
    public static Connection getConntion(){
        if(_conec==null){
            Canal c=new Canal();
            c=XMLUtilidades.CargarCanal();
            try {
                _conec=connect(c);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionUtilidades.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConexionUtilidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _conec;
    }
    
    public static void cerrarConec(){
        try {
            if(_conec!=null){
                _conec.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionUtilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
