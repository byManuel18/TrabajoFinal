/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author Manueh
 */
public class Ejecutable {
    public static void main(String[] args) throws IOException {
        Logger logger =Logger.getLogger("Basico");
        FileHandler fh=new FileHandler("MyLogFile.log");
        logger.addHandler(fh);
        App.main(args);
        ConexionUtilidades.cerrarConec();
        
    }
}
