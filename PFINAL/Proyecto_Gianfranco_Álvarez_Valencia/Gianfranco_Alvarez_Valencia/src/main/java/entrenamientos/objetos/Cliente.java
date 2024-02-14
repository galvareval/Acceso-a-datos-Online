/*
 * Acceso a Datos - Proyecto final
 */

package entrenamientos.objetos;

/**
 *
 * @author Gianfranco Álvarez
 */
public class Cliente {
    
    private String id;
    private String nombre;
    private String apel1;
    private String apel2;
    private String telefono;
    private String correo;
    
    
    public Cliente() {
    }

    public Cliente(String id, String nombre, String apel1, String apel2, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apel1 = apel1;
        this.apel2 = apel2;
        this.telefono = telefono;
        this.correo = correo;
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApel1() {
        return apel1;
    }

    public void setApel1(String apel1) {
        this.apel1 = apel1;
    }

    public String getApel2() {
        return apel2;
    }

    public void setApel2(String apel2) {
        this.apel2 = apel2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   

   
    
    
    
}
