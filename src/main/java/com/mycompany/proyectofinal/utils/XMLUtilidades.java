/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.utils;

import com.mycompany.proyectofinal.models.Canal;
import com.mycompany.proyectofinal.models.ConexionWrapper;
import java.io.File;
import javafx.scene.control.Dialog;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Manueh
 */
public class XMLUtilidades {
    public static String file="canaldeconexion.xml";
    
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
}
