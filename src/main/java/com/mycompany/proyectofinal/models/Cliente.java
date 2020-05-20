/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import com.mycompany.proyectofinal.enums.Factor_Correccion;
import com.mycompany.proyectofinal.enums.IMC;
import com.mycompany.proyectofinal.enums.Nivel_Ejercicio;
import com.mycompany.proyectofinal.enums.Objetivo;
import com.mycompany.proyectofinal.enums.Sexo;
import java.time.LocalDate;

/**
 *
 * @author Manueh
 */
public class Cliente {

    private String nombre;
    private String dni;
    private String contrase;
    private LocalDate fecha_nacimiento;
    private byte[] foto;
    private float peso;
    private float altura;
    private IMC indice_masa;
    private Objetivo objetivo;
    private Nivel_Ejercicio nivel_ejer;
    private float calorias_necesarias;
    private Sexo s;

    private Cliente() {
    }

    public Cliente(String nombre, String dni, String contraseña, LocalDate fecha_nacimiento, Sexo s, float peso, float altura, Nivel_Ejercicio nivel_ejer) {
        this.nombre = nombre;
        this.dni = dni;
        this.contrase = contraseña;
        this.fecha_nacimiento = fecha_nacimiento;
        this.peso = peso;
        this.altura = altura;
        this.nivel_ejer = nivel_ejer;
        this.s = s;
        CalculaIMC();
        CalcularObjetivo();
        CalcularCaloriasNecesarias();
    }

    private void CalculaIMC() {
        float nume = 0;
        if (nume < 18.5) {
            this.indice_masa = IMC.BAJO_PESO;
        } else if (nume >= 18.5 && nume <= 24.9) {
            this.indice_masa = IMC.NORMAL;
        } else if (nume >= 25 && nume <= 29.9) {
            this.indice_masa = IMC.OBESO;
        } else {
            this.indice_masa = IMC.SOBREPESO;
        }
    }

    private void CalcularObjetivo() {
        switch (this.indice_masa) {
            case BAJO_PESO:
                this.objetivo = Objetivo.Engordar;
                break;
            case NORMAL:
                this.objetivo = Objetivo.Mantener;
                break;
            case OBESO:
                this.objetivo = Objetivo.Adelgazar;
                break;
            case SOBREPESO:
                this.objetivo = Objetivo.Adelgazar;
                break;
        }
    }

    private void CalcularCaloriasNecesarias() {
        int edad = LocalDate.now().compareTo(fecha_nacimiento);
        float metabolismo_basal = (10 * this.peso) + (6.25f * (this.altura*100)) - (edad * 5);

        switch (s) {
            case M:
                metabolismo_basal+=5;
                break;
            case H:
                metabolismo_basal-=161;
                break;
        }
        switch(nivel_ejer){
            case SEDENTARIO:
                metabolismo_basal*=Factor_Correccion.Mb_Sedentarias.getFactor();
                break;
            case MUY_ACTIVO:
                metabolismo_basal*=Factor_Correccion.Mb_Muy_Activas.getFactor();
                break;
            case LIGERAMENTE_ACTIVO:
                metabolismo_basal*=Factor_Correccion.Mb_Ligeramente_Activas.getFactor();
                break;
            case MODERADAMENTE_ACTIVO:
                metabolismo_basal*=Factor_Correccion.Mb_Moderadamente_Activas.getFactor();
                break;
            case HIPERACTIVO:
                metabolismo_basal*=Factor_Correccion.Mb_Hiperactivas.getFactor();
                break;
        }
        
        switch(objetivo){
            case Adelgazar:
                metabolismo_basal-=250;
                break;
            case Engordar:
                metabolismo_basal+=300;
                break;
            case Mantener:
                metabolismo_basal=metabolismo_basal;
                break;
        }
        
        calorias_necesarias=metabolismo_basal;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContraseña() {
        return contrase;
    }

    public void setContraseña(String contraseña) {
        this.contrase = contraseña;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public IMC getIndice_masa() {
        return indice_masa;
    }

    public void setIndice_masa(IMC indice_masa) {
        this.indice_masa = indice_masa;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Nivel_Ejercicio getNivel_ejer() {
        return nivel_ejer;
    }

    public void setNivel_ejer(Nivel_Ejercicio nivel_ejer) {
        this.nivel_ejer = nivel_ejer;
    }

    public float getCalorias_necesarias() {
        return calorias_necesarias;
    }

    public void setCalorias_necesarias(float calorias_necesarias) {
        this.calorias_necesarias = calorias_necesarias;
    }

    public Sexo getS() {
        return s;
    }

    public void setS(Sexo s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual = false;

        if (obj != null) {
            if (this == obj) {
                igual = true;
            } else {
                if (obj instanceof Cliente) {
                    Cliente n = (Cliente) obj;
                    igual = this.getDni().equals(n.getContraseña());
                }
            }
        }
        return igual;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", dni=" + dni + ", contrasena=" + contrase + ", fecha_nacimiento=" + fecha_nacimiento + ", foto=" + foto + ", peso=" + peso + ", altura=" + altura + ", indice_masa=" + indice_masa + ", objetivo=" + objetivo + ", nivel_ejer=" + nivel_ejer + ", calorias_necesarias=" + calorias_necesarias + ", s=" + s + '}';
    }
    

}
