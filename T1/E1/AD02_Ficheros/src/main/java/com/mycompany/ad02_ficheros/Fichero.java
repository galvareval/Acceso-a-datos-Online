/*
 * Acceso a Datos - Tarea UT2 
 */
package com.mycompany.ad02_ficheros;

import java.util.Date;

/**
 *
 * @author Alba Tortosa
 */
public class Fichero {
    
    public static final String TIPO_FICHERO = "Fichero";
    public static final String TIPO_CARPETA = "Carpeta";
    
    private String nombre;
    private String tipo;
    private long kilobytes;
    private Date ultimaModificacion;

    public Fichero(String nombre, String tipo, long kilobytes, Date ultimaModificacion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.kilobytes = kilobytes;
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public long getKilobytes() {
        return kilobytes;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setKilobytes(long kilobytes) {
        this.kilobytes = kilobytes;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
    
    
    
}
