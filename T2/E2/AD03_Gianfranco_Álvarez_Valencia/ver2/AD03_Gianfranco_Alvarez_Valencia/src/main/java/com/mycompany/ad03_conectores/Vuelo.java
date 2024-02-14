/*
 * Acceso a Datos - Tarea UT3
 */
package com.mycompany.ad03_conectores;

import java.util.Date;

/**
 *
 * @author Alba Tortosa
 */
public class Vuelo {
 
    private String codigo_vuelo;
    private Date fecha_hora;
    private String origen;
    private String destino;

    public String getCodigo_vuelo() {
        return codigo_vuelo;
    }

    public void setCodigo_vuelo(String codigo_vuelo) {
        this.codigo_vuelo = codigo_vuelo;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
    
    
}
