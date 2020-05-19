/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import com.mycompany.proyectofinal.models.Canal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manueh
 */
@XmlRootElement(name="conns")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConexionWrapper {
    
    @XmlElement(name="conn")
    private Canal canal;

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    @Override
    public String toString() {
        return "ConexionWrapper{" + "canal=" + canal + '}';
    }

}
