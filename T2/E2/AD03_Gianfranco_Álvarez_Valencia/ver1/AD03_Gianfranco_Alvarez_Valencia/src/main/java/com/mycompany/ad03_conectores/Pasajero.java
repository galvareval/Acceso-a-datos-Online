/*
 * Acceso a Datos - Tarea UT3
 */
package com.mycompany.ad03_conectores;

/**
 *
 * @author Alba Tortosa
 */
public class Pasajero {
    
    public static final String TIPO_TU = "TURISTA";
    public static final String TIPO_PR = "PRIMERA";
    
    private String dni;
    private String tipo_plaza;
    private String plaza;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo_plaza() {
        return tipo_plaza;
    }

    public void setTipo_plaza(String tipo_plaza) {
        this.tipo_plaza = tipo_plaza;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }
    
    
    
}
