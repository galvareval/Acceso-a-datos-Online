/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entrenamientos.objetos;

/**
 *
 * @author gianf
 */
public class Rutinas {
    private String codRutina;
    private String idCliente;
    private String frecuenciaSemana;

    public Rutinas(String codRutina, String idCliente, String frecuenciaSemana) {
        this.codRutina = codRutina;
        this.idCliente = idCliente;
        this.frecuenciaSemana = frecuenciaSemana;
    }

    public String getCodRutina() {
        return codRutina;
    }

    public void setCodRutina(String codRutina) {
        this.codRutina = codRutina;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFrecuenciaSemana() {
        return frecuenciaSemana;
    }

    public void setFrecuenciaSemana(String frecuenciaSemana) {
        this.frecuenciaSemana = frecuenciaSemana;
    }
    
}
