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
 *
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

    public int save() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        if (this.id > 0) {
            try {
                ps = conn.prepareStatement(Sentencias.UPDATEEJERCICIO.getSenten());
                ps.setString(1, descripcion);
                ps.setBytes(2, foto);
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
                ps = conn.prepareStatement(Sentencias.INSERTPRODUCTO.getSenten());
                ps.setString(1, descripcion);
                ps.setBytes(2, foto);
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
                        Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("series"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
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
                ps.setString(1, patter);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("series"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
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
                    Ejercicio nuevo = new Ejercicio(rs.getInt("id"), rs.getString("name"), rs.getInt("series"), rs.getInt("series"), rs.getString("descripcion"), Grupo_Muscular.valueOf(rs.getString("Grupo_Muscular")));
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

}
