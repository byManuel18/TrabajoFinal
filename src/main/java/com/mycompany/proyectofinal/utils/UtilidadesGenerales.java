/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Manueh
 */
public class UtilidadesGenerales {

    private final static String EXPRESION_DNI = "^[0-9]{8,8}[A-Za-z]$";

    public static byte[] pasarimagenabites(Image img) {
        byte[] bits = null;
        if (img != null) {
            String ruta = img.getUrl().substring(5);
            BufferedImage bfi = null;
            try {
                bfi = ImageIO.read(new File(ruta));
            } catch (IOException ex) {
                Logger.getLogger(UtilidadesGenerales.class.getName()).log(Level.SEVERE, null, ex);
            }
            WritableRaster raster = bfi.getRaster();
            DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
            bits = data.getData();
        }
        return bits;
    }

    public static Image pasardebitsaimage(byte[] bi) {
        return new Image(new ByteArrayInputStream(bi));
    }

    /**
     * Comprueba si el formato del dni pasado (String) es valido
     *
     * @param dni String
     * @return devuelve un boolean, True si es valido, y false si no
     */
    public static boolean validarDNI(String dni) {
        boolean resultado = false;
        Pattern pat = Pattern.compile(EXPRESION_DNI);
        Matcher mat = pat.matcher(dni);
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if (mat.matches()) {
            int numerosdelacadena = Integer.parseInt(dni.substring(0, 8));
            char letra = dni.charAt(8);
            if (letras[numerosdelacadena % 23] == letra) {
                resultado = true;
            }
        }
        return resultado;
    }
}
