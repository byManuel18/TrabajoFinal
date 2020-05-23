/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.enums;

/**
 *
 * @author Manueh
 */
public enum Sentencias {
    VERTODOSLOSCLIENTES("SELECT * FROM cliente"),
    EXISTECLIENTE("SELECT * FROM cliente WHERE Dni=?"),
    SELECLIENTECONCRETO("SELECT * FROM cliente WHERE dni= ? and contaseña"),
    ISEERTARCLIENTES("INSERT INTO cliente (nombre,dni,Fecha_Nacimiento,Contraseña,Foto,Altura,Peso,IMC,OBJETIVO,Nivel_Ejercicio,Calorias_Necesarias,Sexo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"),
    UPDATECLIENTE("UPDATE cliente SET nombre= ?,Fecha_Nacimiento= ?,Contraseña= ?, Foto= ?, Altura= ?, Peso= ?, IMC= ?, OBJETIVO= ?, Nivel_Ejercicio= ?, Calorias_Necesarias= ?, Sexo= ?  WHERE dni= ?"),
    DELETECLIENTE("DELETE FROM cliente WHERE dni=?"),
    SELECTPRODUCTOCONCRETO("SELECT * FROM product WHERE id= ?"),
    SELECTALLPRODUCTOS("SELECT * FROM product"),
    DELETEPRODUCTO("DELETE FROM product WHERE id= ?"),
    BUSCARPRODUCTOMEDDIANTENOMBRE("SELECT * FROM product WHERE nombre LIKE ?"),
    UPDATEPRODUCTO("UPDATE product SET nombre= ?, marca= ?,calorias= ?,Grasa_Total= ?,Grasas_Saturadas= ?, hidratos= ?,azucar= ?,proteina= ?,fibra= ?,sodio= ?,foto=? WHERE id= ?"),
    INSERTPRODUCTO("INSERT INTO product (nombre,marca,calorias,Grasa_Total,Grasas_Saturadas,hidratos,azucar,proteina,fibra,sodio,id,foto) VALUES(?,?,?,?,?,?,?,?,?,?,NULL,?)"),
    SELECTEJERCICIOCONCRETO("SELECT * FROM ejercicio WHERE id=?"),
    INSERTEJERCICIO("INSERT INTO ejercicio (Descripcion,Foto_Ejer,Grupo_Muscular,Series,Repeticiones,Id,name) VALUES(?,?,?,?,?,NULL,?"),
    UPDATEEJERCICIO("UPDATE ejercicio SET Descripcion= ?, Foto_Ejer= ?,Grupo_Muscular= ?,Series= ?,Repeticiones= ?,name= ? WHERE id= ?"),
    DELETEEJERCICIO("DELETE FROM ejercicio WHERE id=?"),
    SELECTALLEJERCICIOS("SELECT * FROM ejercicio"),
    SELECTALLEJERCICIOSPORNOMBRE("SELECT * FROM ejercicio WHERE name like ?"),
    SENTENCIARUTIAEJER("SELECT e.* FROM ejercicio e, rutinaejer rt WHERE rt.dni_cliente=? and rt.fecha_rutina=? "),
    SENTENCIADIETAPRODUC("SELECT e.* FROM product e, dieta di WHERE di.dni_cliente=? and di.fecha=? "),
    ELIMINARUNPRODUCTODELADIETA("DELETE FROM dieta WHERE dni_cliente=? and fecha=? and id_producto=? LIMIT 1"),
    ELIMINAREJERCICIORUTINA("DELETE FROM rutinaejer WHERE dni_cliente=? and fecha_rutina=? and id_ejer=? LIMIT 1"),
    AÑADIRDIETA("INSERT INTO dieta (dni_cliente,fecha,id_producto) VALUES (?,?,?)" ),
    AÑADIRALARUTINA("INSERT INTO rutinaejer (dni_cliente,fecha_rutina,id_ejer) VALUES(?,?,?)"),
    ;
    private String senten;

    private Sentencias(String senten) {
        this.senten = senten;
    }

    public String getSenten() {
        return senten;
    }
}
