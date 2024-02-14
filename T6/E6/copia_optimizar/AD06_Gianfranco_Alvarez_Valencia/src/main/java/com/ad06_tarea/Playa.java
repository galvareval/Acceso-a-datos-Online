/*
 * Acceso a Datos - Tarea UT6
 */

package com.ad06_tarea;

/**
 *
 * @author Alba Tortosa
 */
public class Playa {
    
    private String nombre;
    private String municipio;
    private String provincia;
    private String comunidad;
    private String longitud;
    
    public Playa() {
    }

    public Playa(String nombre, String municipio, String provincia, String comunidad, String longitud) {
        this.nombre = nombre;
        this.municipio = municipio;
        this.provincia = provincia;
        this.comunidad = comunidad;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    
    
}
