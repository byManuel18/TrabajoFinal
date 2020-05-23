/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.DAOS;

import com.mycompany.proyectofinal.enums.Escenas;
import com.mycompany.proyectofinal.enums.Sentencias;
import com.mycompany.proyectofinal.models.Producto;
import com.mycompany.proyectofinal.utils.ConexionUtilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Clase que controla la base de datos de productos 
 * @author Manueh
 */
public class ProductoDAO extends Producto {

    private boolean persiste;

    public ProductoDAO() {
        super(0, "", "", 0, 0, 0, 0, 0, 0, 0, 0,new byte[0]);
        persiste = false;
    }

    public ProductoDAO(int id, String nombre, String marca, float calorias, float grasas, float grasas_saturadas, float hidratos, float azucaar, float proteinas, float sodio, float fibra,byte[] foto) {
        super(id, nombre, marca, calorias, grasas, grasas_saturadas, hidratos, azucaar, proteinas, sodio, fibra,foto);
        persiste = false;
    }

    public ProductoDAO(String nombre, String marca, float calorias, float grasas, float grasas_saturadas, float hidratos, float azucaar, float proteinas, float sodio, float fibra,byte[] foto) {
        super(-1, nombre, marca, calorias, grasas, grasas_saturadas, hidratos, azucaar, proteinas, sodio, fibra,foto);
        persiste = false;
    }

    public ProductoDAO(Producto p) {
        id = p.getId();
        nombre = p.getNombre();
        marca = p.getMarca();
        calorias = p.getCalorias();
        grasas = p.getGrasas();
        grasas_saturadas = p.getGrasas_saturadas();
        hidratos = p.getHidratos();
        azucaar = p.getAzucaar();
        proteinas = p.getProteinas();
        sodio = p.getSodio();
        fibra = p.getFibra();
        foto = p.getFoto();
        persiste = false;
    }
    /**
     * Constructor que genera un prodcutodao a partir de un id. Si existe en la base de datos lo cogerá si no hace uno por defecto
     * @param id 
     */
    public ProductoDAO(int id) {
        super();
        persiste=false;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sentencias.SELECTPRODUCTOCONCRETO.getSenten());
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.id = id;
                    this.nombre = rs.getString("nombre");
                    this.marca = rs.getString("marca");
                    this.calorias = rs.getFloat("calorias");
                    this.grasas = rs.getFloat("grasa_total");
                    this.grasas_saturadas = rs.getFloat("grasas_saturadas");
                    this.hidratos = rs.getFloat("hidratos");
                    this.proteinas = rs.getFloat("proteina");
                    this.fibra = rs.getFloat("fibra");
                    this.foto = rs.getBytes("foto");
                    this.azucaar = rs.getFloat("azucar");
                    this.sodio = rs.getFloat("sodio");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(rs!=null)
                rs.close();
                if(ps!=null)
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.persiste = persiste;
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

    public void setMarca(String marca) {
        super.setMarca(marca);
        if (persiste == true) {
            save();
        }
    }

    public void setCalorias(float calorias) {
        super.setCalorias(calorias);
        if (persiste == true) {
            save();
        }
    }

    public void setGrasas(float grasas) {
        super.setGrasas(grasas);
        if (persiste == true) {
            save();
        }
    }

    public void setGrasas_saturadas(float grasas_saturadas) {
        super.setGrasas_saturadas(grasas_saturadas);
        if (persiste == true) {
            save();
        }
    }

    public void setHidratos(float hidratos) {
        super.setHidratos(hidratos);
        if (persiste == true) {
            save();
        }
    }

    public void setAzucaar(float azucaar) {
        super.setAzucaar(azucaar);
        if (persiste == true) {
            save();
        }
    }

    public void setProteinas(float proteinas) {
        super.setProteinas(proteinas);
        if (persiste == true) {
            save();
        }
    }

    public void setSodio(float sodio) {
        super.setSodio(sodio);
        if (persiste == true) {
            save();
        }
    }

    public void setFibra(float fibra) {
        super.setFibra(fibra);
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
    /**
     * Método que inserta un nuevo poducto o lo upgradea dependiendo de si la id es positiva o negativa 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public int save() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        if (this.id > 0) {
            try {
                ps = conn.prepareStatement(Sentencias.UPDATEPRODUCTO.getSenten());
                ps.setString(1, nombre);
                ps.setString(2, marca);
                ps.setFloat(3, calorias);
                ps.setFloat(4, grasas);
                ps.setFloat(5, grasas_saturadas);
                ps.setFloat(6, hidratos);
                ps.setFloat(7, azucaar);
                ps.setFloat(8, proteinas);
                ps.setFloat(9, fibra);
                ps.setFloat(10, sodio);
                if(foto!=null){
                    ps.setBytes(11, foto);
                }else{
                    ps.setBytes(11, new byte[1]);
                }
                ps.setInt(12, id);
                resultado=ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(ps!=null)
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            try {
                ps = conn.prepareStatement(Sentencias.INSERTPRODUCTO.getSenten());
                ps.setString(1, nombre);
                ps.setString(2, marca);
                ps.setFloat(3, calorias);
                ps.setFloat(4, grasas);
                ps.setFloat(5, grasas_saturadas);
                ps.setFloat(6, hidratos);
                ps.setFloat(7, azucaar);
                ps.setFloat(8, proteinas);
                ps.setFloat(9, fibra);
                ps.setFloat(10, sodio);
                if(foto!=null){
                    ps.setBytes(11, foto);
                }else{
                    ps.setBytes(11, new byte[1]);
                }
                
                resultado=ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    if(ps!=null)
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resultado;
    }
    /**
     * Método que elimina un producto 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public int remove() {
        int resultado = -1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sentencias.DELETEPRODUCTO.getSenten());
            ps.setInt(1, id);
            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ps!=null)
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }
    /**
     * Lista todos los ejercicios 
     * @return devulve una lista con todos los productos
     */
    public static List<Producto> ListarAll() {
        return ListarAll(-1);
    }
    /**
     * Metodo que muestra todos los productos, si el introducido el menor a cero nos imprimirá todos si es mayor, nos imprimirá uno en concreto 
     * @param patter id específico 
     * @return lista de todos los ejercicios que coincidan con el patter 
     */
    public static List<Producto> ListarAll(int patter) {
        List<Producto> listapro = new ArrayList<>();
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (patter > 0) {
            try {
                ps = conn.prepareStatement(Sentencias.SELECTPRODUCTOCONCRETO.getSenten());
                ps.setInt(1, patter);
                rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Producto nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("marca"), rs.getFloat("calorias"), rs.getFloat("Grasa_Total"), rs.getFloat("Grasas_Saturadas"), rs.getFloat("hidratos"), rs.getFloat("azucar"), rs.getFloat("proteina"), rs.getFloat("sodio"), rs.getFloat("fibra"),rs.getBytes("foto"));
                        listapro.add(nuevo);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(ps!=null)
                    ps.close();
                    if(rs!=null)
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                ps = conn.prepareStatement(Sentencias.SELECTALLPRODUCTOS.getSenten());
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Producto nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("marca"), rs.getFloat("calorias"), rs.getFloat("Grasa_Total"), rs.getFloat("Grasas_Saturadas"), rs.getFloat("hidratos"), rs.getFloat("azucar"), rs.getFloat("proteina"), rs.getFloat("sodio"), rs.getFloat("fibra"),rs.getBytes("foto"));
                        listapro.add(nuevo);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(ps!=null)
                    ps.close();
                    if(rs!=null)
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listapro;
    }
    /**
     * Devulve una lista de productos cuyo nombre coincida con el dato introducido 
     * @param patter string nombre para buscar
     * @return una lista de productos que coincide con el patter
     */
    public static List<Producto> buscar(String patter) {
        List<Producto> listapro = new ArrayList<>();
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(Sentencias.BUSCARPRODUCTOMEDDIANTENOMBRE.getSenten());
            ps.setString(1, patter+"%");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Producto nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("marca"), rs.getFloat("calorias"), rs.getFloat("Grasa_Total"), rs.getFloat("Grasas_Saturadas"), rs.getFloat("hidratos"), rs.getFloat("azucar"), rs.getFloat("proteina"), rs.getFloat("sodio"), rs.getFloat("fibra"),rs.getBytes("foto"));
                    listapro.add(nuevo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ps!=null)
                ps.close();
                if(rs!=null)
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listapro;
    }
    
    /**
     * Método que devulve una lista de productos de una rutina 
     * @param dnicli string del cliente 
     * @param da fecha de la rutina 
     * @return lista de todos los productos de una dieta 
     */
    public static List<Producto> ListarDietaDia(String dnicli, String da){
        List<Producto> listapro = new ArrayList<>();
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps=conn.prepareStatement(Sentencias.SENTENCIADIETAPRODUC.getSenten());
            ps.setString(1, dnicli);
            ps.setString(2, da);
            rs=ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Producto nuevo = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("marca"), rs.getFloat("calorias"), rs.getFloat("Grasa_Total"), rs.getFloat("Grasas_Saturadas"), rs.getFloat("hidratos"), rs.getFloat("azucar"), rs.getFloat("proteina"), rs.getFloat("sodio"), rs.getFloat("fibra"),rs.getBytes("foto"));
                    listapro.add(nuevo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if(ps!=null)
                    ps.close();
                    if(rs!=null)
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return listapro;
    }
    /**
     * metodo que borra un producto de una rutina 
     * @param dniclie string dni cliente 
     * @param datee string fecha rutina 
     * @param id id del producto a eliminar de la rutina 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public static int remuveDieta(String dniclie,String datee,int idp){
        int resultado=-1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        
        try {
            ps=conn.prepareStatement(Sentencias.ELIMINARUNPRODUCTODELADIETA.getSenten());
            ps.setString(1, dniclie);
            ps.setString(2, datee);
            ps.setInt(3, idp);
            resultado=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if(ps!=null)
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return resultado;
    }
    /**
     * Añade un producto a una dieta 
     * @param dnicli string dni cliente 
     * @param date string fecha rutina 
     * @param idpro id producto a introducir en la rutina 
     * @return int que nos dice la cantidad de filas modificadas
     */
    public static int AddalaDieta(String dnicli,String date,int idpro){
        int resultado=-1;
        Connection conn = ConexionUtilidades.getConntion();
        PreparedStatement ps = null;
        try {
            ps=conn.prepareStatement(Sentencias.AÑADIRDIETA.getSenten());
            ps.setString(1, dnicli);
            ps.setString(2, date);
            ps.setInt(3, idpro);
            resultado=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if(ps!=null)
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return resultado;
    }
    
}
