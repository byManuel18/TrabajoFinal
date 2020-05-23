/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.DAOS;

import com.mycompany.proyectofinal.enums.Grupo_Muscular;
import com.mycompany.proyectofinal.enums.Sentencias;
import com.mycompany.proyectofinal.models.Ejercicio;
import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Clase que controla la base de datos de ejercicios 
 * @author Manueh
 */
public class EjerciciosDAO extends Ejercicio {

    private boolean persiste;

    public EjerciciosDAO() {
        super(-1, "", 0, 0, "", Grupo_Muscular.ABDOMINALES);
        persiste = false;
    }

    public EjerciciosDAO(int id, String nombre, int repeticiones, int series, String descripcion, Grupo_Muscular musculo) {
        super(id, nombre, repeticiones, series, descripcion, musculo);
        persiste = false;
    }

    public EjerciciosDAO(String nombre, int repeticiones, int series, String descripcion, Grupo_Muscular musculo) {
        super(-1, nombre, repeticiones, series, descripcion, musculo);
        persiste = false;
    }

    public EjerciciosDAO(Ejercicio ej) {
        id = ej.getId();
        descripcion = ej.getDescripcion();
        nombre = ej.getNombre();
        foto = ej.getFoto();
        musculo = ej.getMusculo();
        repeticiones = ej.getRepeticiones();
        series = ej.getSeries();
        persiste = false;
    }
    /**
     * CConstructor que genera un EjercicioDao a partir de un id que busca en la base de datos. Si no encuentra lo crea por defecto 
     * @param id id del producto a buscar para crear 
     */
    public EjerciciosDAO(int id) {
        super();
        persiste = false;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(Sentencias.SELECTEJERCICIOCONCRETO.getSenten());
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.id = rs.getInt("id");
                    this.nombre = rs.getString("name");
                    this.descripcion = rs.getString("descipcion");
                    this.foto = rs.getBytes("foto");
                    this.repeticiones = rs.getInt("repeticiones");
                    this.series = rs.getInt("series");
                    this.musculo = Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void persist() {
        persiste = true;
    }

    public void detatch() {
        persiste = false;
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
        if (persiste == true) {
            save();
        }
    }

    public void setRepeticiones(int repeticiones) {
        super.setRepeticiones(repeticiones);
        if (persiste == true) {
            save();
        }
    }

    public void setSeries(int series) {
        super.setSeries(series);
        if (persiste == true) {
            save();
        }
    }

    public void setDescripcion(String descripcion) {
        super.setDescripcion(descripcion);
        if (persiste == true) {
            save();
        }
    }

    public void setFoto(byte[] foto) {
        super.setFoto(foto);
        if (persiste == true) {
            save();
        }
    }

    public void setMusculo(Grupo_Muscular musculo) {
        super.setMusculo(musculo);
        if (persiste == true) {
            save();
        }
    }
    /**
     * Guarda un ejercicio o modificacion en la base de datos 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public int save() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        if (this.id > 0) {
            try {
                ps = conn.prepareStatement(Sentencias.UPDATEEJERCICIO.getSenten());
                ps.setString(1, descripcion);
                if(foto==null){
                     ps.setBytes(2,new byte[1]);
                }else{
                    ps.setBytes(2, foto);
                }
                ps.setString(3, musculo.toString());
                ps.setInt(4, series);
                ps.setInt(5, repeticiones);
                ps.setString(6, nombre);
                ps.setInt(7, id);

                resultado = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                ps = conn.prepareStatement(Sentencias.INSERTEJERCICIO.getSenten());
                ps.setString(1, descripcion);
                if(foto==null){
                     ps.setBytes(2,new byte[1]);
                }else{
                    ps.setBytes(2, foto);
                }
                ps.setString(3, musculo.toString());
                ps.setInt(4, series);
                ps.setInt(5, repeticiones);
                ps.setString(6, nombre);
                resultado = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return resultado;
    }
    /**
     * Borra un cliente de la base de datos 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public int delete() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sentencias.DELETEEJERCICIO.getSenten());
            ps.setInt(1, this.id);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    public static List<Ejercicio> ListAll() {
        return ListAll("");
    }
    /**
     * Lista todos los ejercicios a partir de un nombre introducido 
     * @param patter string nombre introducido 
     * @return una lista de ejercicios 
     */
    public static List<Ejercicio> ListAll(String patter) {
        List<Ejercicio> listapro = new ArrayList<>();
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (patter.length() == 0) {
            try {
                ps = conn.prepareStatement(Sentencias.SELECTALLEJERCICIOS.getSenten());
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("repeticiones"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
                        listapro.add(nuevo);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                ps = conn.prepareStatement(Sentencias.SELECTALLEJERCICIOSPORNOMBRE.getSenten());
                ps.setString(1, patter+"%");
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("repeticiones"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
                        listapro.add(nuevo);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listapro;
    }
    /**
     * Metodo que devulve los ejercicios de una rutina concreta a partir del dni la fecha 
     * @param cdni string dni del cliente 
     * @param da string fecha de creación de la rutina 
     * @return una lista de ejercicios 
     */
    public static List<Ejercicio> listarRutinaEjer(String cdni, String da) {
        List<Ejercicio> listapro = new ArrayList<>();
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sentencias.SENTENCIARUTIAEJER.getSenten());
            ps.setString(1, cdni);
            ps.setString(2, da);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("repeticiones"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
                    listapro.add(nuevo);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listapro;
    }
    /**
     * Borra una rutina a paartir de la fecha un cliente y el id de un ejercicio
     * @param dnic String dni cliente 
     * @param fe string fecha rutina 
     * @param id id del ejercicio 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public static int removeejerderutina(String dnic, String fe, int id) {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sentencias.ELIMINAREJERCICIORUTINA.getSenten());
            ps.setString(1, dnic);
            ps.setString(2, fe);
            ps.setInt(3, id);
            resultado=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    /**
     * Añade un ejercicio a una rutina 
     * @param dnic string dni cliente 
     * @param fechar string fecha cliente 
     * @param idej id del ejercicio a introducir en la rutina
     * @return int que nos dice la cantidad de filas modificadas
     */
    public static int añadiralaruttina(String dnic,String fechar,int idej){
        int resultado=-1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps=conn.prepareStatement(Sentencias.AÑADIRALARUTINA.getSenten());
            ps.setString(1, dnic);
            ps.setString(2, fechar);
            ps.setInt(3, idej);
            resultado=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EjerciciosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

}
