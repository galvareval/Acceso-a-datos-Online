/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entrenamientos.objetos;

/**
 *
 * @author gianf
 */
public class Ejercicios {
    private String nombre;
    private String codRutina;
    private String grupoMuscular;
    private String series;
    private String repeticiones;
    private String peso;

    public Ejercicios(String nombre, String codRutina, String grupoMuscular, String series, String repeticiones, String peso) {
        this.nombre = nombre;
        this.codRutina = codRutina;
        this.grupoMuscular = grupoMuscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public String getCodRutina() {
        return codRutina;
    }

    public void setCodRutina(String codRutina) {
        this.codRutina = codRutina;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    
}
