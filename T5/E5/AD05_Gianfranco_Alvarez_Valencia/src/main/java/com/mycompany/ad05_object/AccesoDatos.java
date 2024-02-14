/*
 * Acceso a Datos - Tarea UT5
 */
package com.mycompany.ad05_object;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gianfranco Álvarez
 */
public class AccesoDatos {
    
    public static EntityManagerFactory emf;
    public static EntityManager db;
    public static CriteriaBuilder cb;
    
    public static void crearConexion() {
        emf = Persistence.createEntityManagerFactory("objectdb:db/peliculas.tmp;drop");
        db = emf.createEntityManager();
        cb = emf.getCriteriaBuilder();
    }
       
    public static ArrayList<Pelicula> buscarPeliculas(String titulo, String director) {

        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "peliculas" con la lista de las peliculas que coincidan con los parámetros de entrada.
        //Utiliza la libreria Criteria (javax.persistence.criteria) para realizar la búsqueda
        //Se pueden combinar las dos opciones de búsqueda. Si los dos paramétros se dejan vacíos, se mostrará la lista completa de películas.
        //En el caso del título, la coincidencia NO debe tener en cuenta las mayúsculas y minúsculas.
        //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
        
        // Var aux para almacenar el director a partir del nombre
        Director dirAux = null;
        if (!titulo .isEmpty())//Titulo no vacio
        {
            if (director.isEmpty())//Director vacio
            {
                //Caaso busqueda por título
                //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
                CriteriaQuery<Pelicula> obtenerPeliculasTitulo = cb.createQuery(Pelicula.class);
                //Especifica la clase sobre la que se va a buscar (FROM)
                Root<Pelicula> pelTit = obtenerPeliculasTitulo.from(Pelicula.class);
                //Especifica las condiciones de filtrado (WHERE);Peliculas que coincidan en el título
                Predicate predicatePelTit = cb.equal(pelTit.get("titulo"), titulo.toUpperCase());//como los datos se almacenan en mayusculas hacer uppercase para que siempre concida
                //Construye la query en orden: select + where. Utilizamos el objeto Root para que devuelva solo objetos
                obtenerPeliculasTitulo.select(pelTit).where(predicatePelTit);
                //realización de consulta
                List<Pelicula> resultPeliculasTitulo = db.createQuery(obtenerPeliculasTitulo).getResultList();
                //Volcar el resultado de la query en el arraylist
                for (Object p : resultPeliculasTitulo) 
                {
                    peliculas.add((Pelicula) p);
                }
            } 
            else //director no vacio
            {
                //Caso busqueda por titulo y director
                //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
                CriteriaQuery<Pelicula> obtenerPeliculasTituloYDir = cb.createQuery(Pelicula.class);
                //Especifica la clase sobre la que se va a buscar (FROM)
                Root<Pelicula> pelTitYdir = obtenerPeliculasTituloYDir.from(Pelicula.class);
                //Crea la unión (JOIN) con otro objeto
                Join<Pelicula, Director> peliculasYdirectores = pelTitYdir.join("director", JoinType.INNER);
                //Especifica las condiciones de filtrado (WHERE);Peliculas que coincidan en el título
                Predicate predicatePelTit = cb.equal(pelTitYdir.get("titulo"), titulo.toUpperCase());//como los datos se almacenan en mayusculas hacer uppercase para que siempre concida
                Predicate predicatePeldir = cb.equal(peliculasYdirectores.get("nombre"), director);
                Predicate predicateAnd = cb.and(predicatePelTit, predicatePeldir);
                //Construye la query en orden: select + where. Utilizamos el objeto Root para que devuelva solo objetos peliculas
                obtenerPeliculasTituloYDir.select(pelTitYdir).where(predicateAnd);
                //realización de consulta
                List<Pelicula> resultPeliculasTitulo = db.createQuery(obtenerPeliculasTituloYDir).getResultList();
                //Volcar el resultado de la query en el arraylist
                for (Object p : resultPeliculasTitulo) 
                {
                    peliculas.add((Pelicula) p);
                }
            }
        } 
        else //Titulo vacio
        {
            if(!(director.isEmpty()))//Director no vacio
            {
                //caso busqueda por director
                //Caso busqueda por titulo y director
                //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
                CriteriaQuery<Pelicula> obtenerPeliculasDir = cb.createQuery(Pelicula.class);
                //Especifica la clase sobre la que se va a buscar (FROM)
                Root<Pelicula> pelDir = obtenerPeliculasDir.from(Pelicula.class);
                //Crea la unión (JOIN) con otro objeto
                Join<Pelicula, Director> peliculasDirectores = pelDir.join("director", JoinType.INNER);
                //Especifica las condiciones de filtrado (WHERE);Peliculas que coincidan con el nombre director
                Predicate predicatePeldir = cb.equal(peliculasDirectores.get("nombre"), director);
                //Construye la query en orden: select + where. Utilizamos el objeto Root para que devuelva solo objetos peliculas
                obtenerPeliculasDir.select(pelDir).where(predicatePeldir);
                //realización de consulta
                List<Pelicula> resultPeliculasTitulo = db.createQuery(obtenerPeliculasDir).getResultList();
                //Volcar el resultado de la query en el arraylist
                for (Object p : resultPeliculasTitulo) {
                    peliculas.add((Pelicula) p);
                }

            }
            else//Titulo y director vacios
            {
                //caso los dos campos vacios
                CriteriaQuery<Pelicula> obtenerPeliculasAll = cb.createQuery(Pelicula.class);
                //Especifica la clase sobre la que se va a buscar (FROM)
                Root<Pelicula> pelAll = obtenerPeliculasAll.from(Pelicula.class);
                //Especifica qué se va a buscar (SELECT)
                obtenerPeliculasAll.select(pelAll);
                //realización de consulta
                List<Pelicula> resultDirectorAll = db.createQuery(obtenerPeliculasAll).getResultList();
                //Volcar el resultado de la query en el arraylist
                for (Object p : resultDirectorAll) 
                {
                    peliculas.add((Pelicula) p);
                }
            }
        }
       
        //FIN
        return peliculas;
    }

    public static ArrayList<String> buscarDirectores() {

        ArrayList<String> directores = new ArrayList<String>();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "directores" con la lista de todos los nombres de directores de la base de datos
        //Utiliza la libreria Criteria (javax.persistence.criteria) para realizar la búsqueda      
        //obtner todos los directores de la BBDD
        //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
        CriteriaQuery<Director> obtenerDirectoresAll = cb.createQuery(Director.class);
        //Especifica la clase sobre la que se va a buscar (FROM)
        Root<Director> dir = obtenerDirectoresAll.from(Director.class);
        //Especifica qué se va a buscar (SELECT)
        obtenerDirectoresAll.select(dir);
        //realización de consulta
        List<Director> resultDirectorAll = db.createQuery(obtenerDirectoresAll).getResultList();
        Director dirAux = null;
        for (Object p : resultDirectorAll) {
            dirAux = (Director) p;
            directores.add(dirAux.getNombre());
        }
        //FIN
        return directores;
    }

    public static String nuevaPelicula(String titulo, int duracion, String director) {
        String mensaje = "La película se ha creado correctamente";

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea una nueva pelicula en la base de datos con los datos pasados por parámetro
        //Si ocurre cualquier error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Ten en cuenta que pueden llegar datos inválidos (textos en lugar de números...). Muestra errores apropiados en cada caso
         try {
             //Comprobar que la duiracion sea un entero y no un texto
            if(comprobTexto(duracion))
                mensaje = "La duración tiene que ser un entero!";
            else
            {
                //comprobar que el título y el director sean texto
                if (comprobTexto(titulo) && comprobTexto(director))
                { 
                    //Crear objeto Pelicula
                    Pelicula p1 = new Pelicula();
                    //Crear objeto director auxiliar
                    Director dirApartirDeNombre = null;
                    //EntityManager db = emf.createEntityManager();
                    //Set el objeto con los parámetros
                    p1.setTitulo(titulo);
                    p1.setDuracion(duracion);
                    //obtner director a partir de string
                    dirApartirDeNombre = obtenerDirPorNombre(director);
                    //Si existe el director pasarlo 
                    //Comporbar que el director existe en la BBDD
                    if (dirApartirDeNombre != null)
                    //Pasar el director
                    {
                        p1.setDirector(dirApartirDeNombre);
                        db.getTransaction().begin();
                        db.persist(p1);
                        db.getTransaction().commit();
                    }
                    else
                        mensaje = "El nombre del director no existe en la BBDD!";
                }
                else
                    if(comprobTexto(titulo) == false)
                        mensaje = "El tirulo tiene que ser una cadena de caráteres!";
                    else
                        mensaje = "El nombre del director tiene que ser una cadena de caráteres!";
            }
        } finally {
            if (db.getTransaction().isActive()) 
            {
                db.getTransaction().rollback();
            }
        }
        
        //FIN
        return mensaje;
    }

    public static String nuevoDirector(String nombre, String nacionalidad) {
        String mensaje = "El director se ha creado correctamente";

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea un nuevo director en la base de datos con los datos pasados por parámetro
        ///Si ocurre cualquier error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        
        try {
            //Comprobar que el nombre y la nacionalidad sean cadenas de texto
            if (comprobTexto(nombre) && comprobTexto(nacionalidad))
            {    
                //Crear objeto Director
                Director d1 = new Director(nombre,nacionalidad);
                //Se almacena el objeto en la base de datos utilizando una transacción
                db.getTransaction().begin();
                db.persist(d1);
                db.getTransaction().commit();
            }
            else
                if(comprobTexto(nombre) == false)
                    mensaje = "El nombre tiene que ser una cadena de caráteres!";
                else
                    mensaje = "La nacionalidad tiene que ser una cadena de caráteres!";
        } finally {
            if (db.getTransaction().isActive()) 
            {
                db.getTransaction().rollback();
            }
        }
        System.out.println(mensaje);
        //EntityManager db = emf.createEntityManager();
        //FIN
        return mensaje;
    }

    public static String editarPelicula(String titulo, int duracion, String director) {

        String mensaje = "La película se ha modificado correctamente";

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Modifica la pelicula con el titulo proporcionado con los datos pasados por parámetro
        //Si ocurre cualquier error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Ten en cuenta que pueden llegar datos inválidos (textos en lugar de números...). Muestra errores apropiados en cada caso
        try 
        {
            //Primero hay que buscar los objetos que queremos borrar  
            Pelicula updatePelicula = db.find(Pelicula.class, titulo); //Este método permite buscar un objeto por su clave primaria
            //Para actulizar utilizamos una transacción
            //Obtener el objeto director a partir del nombre
            db.getTransaction().begin();
            updatePelicula.setDuracion(duracion); //No es necesario utilizar el método persist, cualquier modificación se traslada a la base de datos al terminar la transacción (commit)
            updatePelicula.setDirector(obtenerDirPorNombre(director));
            db.getTransaction().commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
            mensaje = "Ha ocurrido un error: " + e;
        } finally 
        {
            if (db.getTransaction().isActive()) 
            {
                db.getTransaction().rollback();
            }//No cerrar conexion para el update de la tabla
        }
        //FIN
        return mensaje;
    }

    public static String borrarPelicula(String titulo) {

        String mensaje = "La película se ha eliminado correctamente";

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Elimina la pelicula con el titulo proporcionado
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        try 
        {
            //Primero hay que buscar los objetos que queremos borrar  
            Pelicula borrarPelicula = db.find(Pelicula.class, titulo); //Este método permite buscar un objeto por su clave primaria
            //Para borrar utilizamos una transacción
            db.getTransaction().begin();
            db.remove(borrarPelicula);
            db.getTransaction().commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
            mensaje = "Ha ocurrido un error: " + e;
        } finally 
        {
            if (db.getTransaction().isActive()) 
            {
                db.getTransaction().rollback();
            }//No cerrar conexion para el update de la tabla
        }  
        //FIN
        return mensaje;
    }
      
    /**
     * Método Auxiliar que compruaba si la cadena de texto es correcta
     * @param texto Texto a evaluar
     * @return Devuelve un booleano con la comprobaciíon del texto
     */

    public static boolean comprobTexto(Object texto)
    {
        if (texto.equals(texto.toString())) 
        {
          return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * Método Auxiliar que devuelve el objeto director a partir de un nombre
     * @param nombre String con el nombre del director
     * @return Devuelve objeto director a aprtir del nombre
     */

    public static Director obtenerDirPorNombre(String nombre)
    {
        //obtner director a partir de string
        //Crea la consulta de tipo CriteriaQuery especificando el tipo de objeto resultado
        CriteriaQuery<Director> obtenerDirector = cb.createQuery(Director.class);
        //Especifica la clase sobre la que se va a buscar (FROM)
        Root<Director> dir = obtenerDirector.from(Director.class);
        //Especifica las condiciones de filtrado (WHERE)
        Predicate predicateDir = cb.equal(dir.get("nombre"), nombre);
        //Construye la query en orden: select + where. Utilizamos el objeto Root para que devuelva solo objetos de tipo director
        obtenerDirector.select(dir).where(predicateDir);
        //realización de consulta
        //List<Director> resultDirector = db.createQuery(obtenerDirector).getResultList();
        //Como sólo hay un resultado, podemos utilizar el método getSingleResult()
        //realización de consulta
        return db.createQuery(obtenerDirector).getSingleResult();
    }
    
}
