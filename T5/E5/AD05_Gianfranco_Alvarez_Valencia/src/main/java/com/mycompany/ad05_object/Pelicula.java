/*
 * Acceso a Datos - Tarea UT5
 */

package com.mycompany.ad05_object;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/******************************************************************************
 * clase que implementa un Pelicula. Cada Pelicula tiene un título y está
 * asignada a un Director.
 *
 * CLASE ESTRUCTURADA: contiene un objeto Director
 *
 * @author Alba Tortosa
 */
@Entity  //Anotación que indica que esta es una clase que debe almacenarse en la base de datos
public class Pelicula {

  
  @Id //Anotación que indica que este atributo es la clave primaria
  private String titulo;
  private int duracion; //en minutos
  
 
  @ManyToOne (cascade=CascadeType.PERSIST)//Anotacion que permite que el objeto director se guarde en la base de datos
  private Director director;
  /****************************************************************************
   * constructor
   */
  public Pelicula() {
    this.director = null;
  }

  /****************************************************************************
   * método que proporciona el titulo
   *
   * @return
   */
  public String getTitulo() {
    //
    return titulo;
  }

  /****************************************************************************
   * método que establece el titulo
   *
   * @param titulo
   */
  public void setTitulo(String titulo) {
    //
    this.titulo = titulo;
  }
  
  /****************************************************************************
   * método que proporciona el Director
   *
   * @return
   */
  public Director getDirector() {
    //
    return director;
  }

  /****************************************************************************
   * método que establece el Director
   *
   * @param director
   */
  public void setDirector(Director director) {
    //
    this.director = director;
  }

  /****************************************************************************
   * método que proporciona la Duracion
   *
   * @return
   */
  public int getDuracion() {
    //
    return duracion;
  }

  /****************************************************************************
   * método que establece la Duracion
   *
   * @param duracion
   */
  public void setDuracion(int duracion) {
    //
    this.duracion = duracion;
  }


  /****************************************************************************
   * método heredado de Object que proporciona una cadena cuando se le requiere
   *
   * @return
   */
  @Override
  public String toString() {
    return "'" + titulo + "'. Película de " + duracion + " min. de duración, "
            + "dirigida por " + director;
  }
}
