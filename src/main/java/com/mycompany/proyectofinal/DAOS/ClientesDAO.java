/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.DAOS;

import com.mycompany.proyectofinal.enums.IMC;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Objetivo;
import com.mycompany.proyectofinal.enums.Sentencias;
import com.mycompany.proyectofinal.enums.Sexo;
import com.mycompany.proyectofinal.models.Cliente;
import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manueh
 */
public class ClientesDAO extends Cliente {

    private boolean persiste;

    public ClientesDAO() {
        super("", "", "", LocalDate.now(), Sexo.H, 0, 0, Nivel_Ejercicio.SEDENTARIO);
        this.persiste = false;
    }

    public ClientesDAO(String nombre, String dni, String contraseña, LocalDate fecha_nacimiento, Sexo s, float peso, float altura, Nivel_Ejercicio nivel_ejer) {
        super(nombre, dni, contraseña, fecha_nacimiento, s, peso, altura, nivel_ejer);
        this.persiste = false;
    }

    public ClientesDAO(Cliente c) {
        nombre = c.getNombre();
        dni = c.getDni();
        contrase = c.getContraseña();
        fecha_nacimiento = c.getFecha_nacimiento();
        s = c.getS();
        peso = c.getPeso();
        altura = c.getAltura();
        nivel_ejer = c.getNivel_ejer();
        foto = c.getFoto();
        indice_masa = c.getIndice_masa();
        objetivo = c.getObjetivo();
        calorias_necesarias = c.getCalorias_necesarias();
        this.persiste = false;
    }

    public ClientesDAO(String cdni) {
        super();

        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sentencias.EXISTECLIENTE.getSenten());
            ps.setString(1, cdni);
            rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.nombre = rs.getString("nombre");
                    this.dni = rs.getString("Dni");
                    this.altura = rs.getFloat("altura");
                    this.peso=rs.getFloat("peso");
                    this.contrase = rs.getString("Contraseña");
                    this.foto = rs.getBytes("foto");
                    Date d = rs.getDate("fecha_nacimiento");
                    this.fecha_nacimiento = d.toLocalDate();
                    this.calorias_necesarias = rs.getFloat("calorias_necesarias");
                    this.indice_masa = IMC.valueOf(rs.getString("IMC"));
                    this.objetivo = Objetivo.valueOf(rs.getString("OBJETIVO"));
                    this.s = Sexo.valueOf(rs.getString("Sexo"));
                    this.nivel_ejer = Nivel_Ejercicio.valueOf(rs.getString("Nivel_Ejercicio"));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public int save() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sentencias.ISEERTARCLIENTES.getSenten());
            ps.setString(1, nombre);
            ps.setString(2, dni);
            Date sqldate = Date.valueOf(fecha_nacimiento);
            ps.setDate(3, sqldate);
            ps.setString(4, contrase);
            ps.setBytes(5, foto);
            ps.setFloat(6, altura);
            ps.setFloat(7, peso);
            ps.setString(8, indice_masa.toString());
            ps.setString(9, objetivo.toString());
            ps.setString(10, nivel_ejer.toString());
            ps.setFloat(11, calorias_necesarias);
            ps.setString(12, s.toString());
            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

}
