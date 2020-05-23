/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import com.mycompany.proyectofinal.models.Canal;
import com.mycompany.proyectofinal.models.ConexionWrapper;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *  Clase dedicada aescribir y leer xml
 * @author Manueh
 */
public class XMLUtilidades {
    public static String file="canaldeconexion.xml";
    /**
     * Pinta en un xml el canal indicado (daros para cargar base de datos)
     * @param dato canal a escribir
     */
    public static void EscribeCanal(Canal dato){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(ConexionWrapper.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ConexionWrapper wrapper = new ConexionWrapper();
            wrapper.setCanal(dato);
            m.marshal(wrapper, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            //Dialog.showError("ERROR", "Error reading "+file, ex.toString());
            System.out.println("NO VA :((");
        }
        
    }
    /**
     * Devulve una canal cargado de un xml
     * @return canal a conectarse
     */
    public static Canal CargarCanal(){
        Canal c=null;
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(ConexionWrapper.class);
                Unmarshaller um = context.createUnmarshaller();
                ConexionWrapper wrapper = (ConexionWrapper) um.unmarshal(f);
                c=(wrapper.getCanal());
            }catch(JAXBException ex){
                ex.printStackTrace();
            }
        }
        return c;
    }
}
