/*
 * Acceso a Datos - Tarea UT5
 */
package com.mycompany.ad05_object;

import javax.persistence.Entity;
import javax.persistence.Id;

/******************************************************************************
 * clase que implementa la identidad Director
 * 
 * @author Alba Tortosa
 */
@Entity  //Anotación que indica que esta es una clase que debe almacenarse en la base de datos
public class Director {
  @Id //Anotación que indica que este atributo es la clave primaria 
  private String nombre;
  private String nacionalidad;

  /****************************************************************************
   * constructor
   */
  public Director() {
    //
    nombre = "";
    nacionalidad = "";
  }

  /****************************************************************************
   * constructor
   *
   * @param nombre
   * @param nacionalidad
   */
  public Director(String nombre, String nacionalidad) {
    //
    this.nombre = nombre;
    this.nacionalidad = nacionalidad;
  }

  /****************************************************************************
   * método que proporciona el nombre
   *
   * @return
   */
  public String getNombre() {
    //
    return nombre;
  }

  /****************************************************************************
   * método que establece el nombre
   *
   * @param nombre
   */
  public void setNombre(String nombre) {
    //
    this.nombre = nombre;
  }
  
  /****************************************************************************
   * método que proporciona la nacionalidad
   *
   * @return
   */
  public String getNacionalidad() {
    //
    return nacionalidad;
  }

  /****************************************************************************
   * método que establece la nacionalidad
   *
   * @param nacionalidad
   */
  public void setNacionalidad(String nacionalidad) {
    //
    this.nacionalidad = nacionalidad;
  }

  /****************************************************************************
   * método heredado de Object que proporciona una cadena cuando se le requiere
   *
   * @return
   */
  @Override
  public String toString() {

    return nombre + " (" + nacionalidad + ")";
  }
}
