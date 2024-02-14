/*
 * Acceso a Datos - Tarea UT6
 */
package com.ad06_tarea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.exist.xmldb.EXistResource;
import org.exist.xmldb.RemoteXMLResource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author Gianfranco Álvarez
 */
public class AccesoDatos {

    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static Collection col = null;

    //Obtiene una conexión a la base de datos
    public static void abrirConexion() {

        try {
            // initialize database driver
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
            // get the collection
            col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db", "admin", "root");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void cerrarConexion() {
        if (col != null) {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    public static ArrayList<String> buscarMunicipios(String comunidad, String provincia) {
        ArrayList<String> municipios = new ArrayList();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "municipios" con la lista de todos los nombres de municipios de la base de datos
        //Muéstralos ordenados alfabéticamente
        //Utiliza una consulta que NO devuelva valores repetidos
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda  
        //iniciar la conexión
        abrirConexion();
        //obtener la lista ordenada y no repedita de municipios
        obtenerListas(municipios,"Municipio");
    //FIN
    return municipios ;
}

    private static void obtenerListas(ArrayList<String> municipios,String nombreLista) throws DOMException {
        RemoteXMLResource resource = null;
        
        try {
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = xpqs.query("/playas/playa");//.xml y el nivel
            //System.out.println("esto?"+result.toString());
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {//iterar el resultado
                resource = (RemoteXMLResource) i.nextResource();
                Element element = (Element) resource.getContentAsDOM();
                //System.out.println(element.getElementsByTagName("Municipio").item(0).getTextContent());//Se consiguen todos los municipios pero repetidos y sin ordenar
                //añadir al arraylist
                municipios.add(element.getElementsByTagName(nombreLista).item(0).getTextContent());//obtener los municipios
            }
            //El rray list está sin ordenar y con repetidos
            //quitar repetidos
            Set<String> hashSet = new HashSet<String>(municipios);
            municipios.clear();
            municipios.addAll(hashSet);
            //ordenarlo
            Collections.sort(municipios);
        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (resource != null) 
            {//Liberear res
                try 
                {
                    ((EXistResource) resource).freeResources();
                } catch (XMLDBException xe) 
                {
                    xe.printStackTrace();
                }
            }
            if (col != null)
            {//cerrar conexión
                cerrarConexion();
            }
        }
    }

public static String buscarProvincia(String municipio) {

        String provincia = null;

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "provincia" con la provincia asociada al municipio pasado por parámetro
        //Utiliza una consulta que asegure que se obtiene un único resultado
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda  
        //FIN
        return provincia;
    }

    public static ArrayList<String> buscarProvincias(String comunidad) {
        ArrayList<String> provincias = new ArrayList();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "provincias" con la lista de todos los nombres de provincias de la base de datos
        //Muéstralas ordenadas alfabéticamente
        //Utiliza una consulta que NO devuelva valores repetidos
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda      
        //FIN
        abrirConexion();
        //obtener la lista ordenada y no repedita de provincias
        obtenerListas(provincias,"Provincia");
        return provincias;
    }

    public static String buscarComunidad(String provincia) {

        String comunidad = null;

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "comunidad" con la comunidad asociada a la provincia pasada por parámetro
        //Utiliza una consulta que asegure que se obtiene un único resultado
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda  
        //FIN
        RemoteXMLResource resource = null;
        
        try {
            ResourceSet resultBusquedaUno = null;
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            resultBusquedaUno = xpqs.query("for $comunidad in(/playas/playa)\n" +
                                            "where $comunidad/Provincia=\"" + provincia+ "\"\n" +
                                            "let $uniq_comunidad := distinct-values($comunidad/Comunidad)\n" +
                                            "return $uniq_comunidad");
            System.out.println("ejecutada query que llena camunidad a partir de municiio ");
            System.out.println("resultBusquedaUno.toString()");
            //System.out.println("esto?"+result.toString());
            //ResourceIterator iUno = resultBusquedaUno.getIterator();
            comunidad = resultBusquedaUno.toString();
            
        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
            if (resource != null) 
            {//Liberear res
                try 
                {
                    ((EXistResource) resource).freeResources();
                } catch (XMLDBException xe) 
                {
                    xe.printStackTrace();
                }
            }
            if (col != null)
            {//cerrar conexión
                cerrarConexion();
            }
        }
        return comunidad;
    }

    public static ArrayList<String> buscarComunidades() {
        ArrayList<String> comunidades = new ArrayList();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "comunidades" con la lista de todos los nombres de comunidades de la base de datos
        //Muéstralas ordenadas alfabéticamente
        //Utiliza una consulta que NO devuelva valores repetidos
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda 
        abrirConexion();
        //obtener la lista ordenada y no repedita de provincias
        obtenerListas(comunidades,"Comunidad");
        //FIN
        return comunidades;
    }

    public static ArrayList<Playa> buscarPlayas(String comunidadSeleccionada, String provinciaSeleccionada, String municipioSeleccionado) {
        ArrayList<Playa> playas = new ArrayList();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "playas" con la lista de todas las playas que coincidan los filtros seleccionados
        //Muéstralas ordenadas alfabéticamente por nombre de playa
        //Utiliza la libreria Xquery o XPath para realizar la búsqueda   
        //FIN
        return playas;
    }

}
