/*
 * Acceso a Datos - Tarea UT6
 */
package com.ad06_tarea;

import java.util.ArrayList;
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
        //obtenerListas(municipios,"Municipio");
        //Tener en cuenta los valores que se pasan de los filtros
        if (comunidad == null) //Caso comunidad no seleccionada, null
        {
            obtenerListasFiltro(municipios, "for $p in distinct-values(/playas/playa/Municipio) order by $p return $p");
        } 
        else
        {
            if (comunidad.equals("TODAS")) //Caso comunidad no seleccionado, seleccionado TODAS
            {
                obtenerListasFiltro(municipios, "for $p in distinct-values(/playas/playa/Municipio) order by $p return $p");
            } 
            else 
            {
                if (provincia == null) //Comunidad seleccionada pero no provincia, null
                {
                    obtenerListasFiltro(municipios, "for $p in distinct-values(/playas/playa[Comunidad='" + comunidad + "']/Municipio) order by $p return $p");
                } else 
                    if (provincia.equals("TODAS")) //Comunidad seleccionado pero no provincia, seleccionado TODAS
                    {
                        obtenerListasFiltro(municipios, "for $p in distinct-values(/playas/playa[Comunidad='" + comunidad + "']/Municipio) order by $p return $p");
                    } 
                    else // Comunidad y provincia seleccionados
                    {
                        obtenerListasFiltro(municipios, "for $p in distinct-values(/playas/playa[Comunidad='" + comunidad + "'][Provincia='" + provincia + "']/Municipio) order by $p return $p");
                    }
            }
        }
        //FIN
        return municipios;
    }

    /**
     * Método para obtener las listas de los desplegables
     *
     * @param lista Lista a rellenar
     * @param query la consulta que se hará a la BBDD
     * @throws DOMException
     */
    private static void obtenerListasFiltro(ArrayList<String> lista, String query) throws DOMException {
        RemoteXMLResource resource = null;

        try {
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Recursos para al consulta
            ResourceSet result = xpqs.query(query);
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {//iterar el resultado obteniendo los valores de la consulta y pasandolos a ArrayList
                resource = (RemoteXMLResource) i.nextResource();
                resource.getContent();
                //añadir al arraylist resultado
                lista.add(resource.getContent().toString());
            }
        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resource != null) {//Liberear res
                try {
                    ((EXistResource) resource).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
            if (col != null) {//cerrar conexión
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
        //El valor no puede ser null
        if (municipio != null) {
            if (!municipio.equals("TODOS"))//No hacer la consula en valor TODOS
            {
                provincia = filtrar("for $p in distinct-values(/playas/playa[Municipio='" + municipio + "']/Provincia) order by $p return $p");//Caso municipio seleccionado
            } else {
                provincia = "TODAS";//En este caso devolver "TODAS" al select de provincia, caso municipio no seleccionado
            }
        }
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
        if (comunidad == null) //Caso comunidad no seleccionada
        {
            obtenerListasFiltro(provincias, "for $p in distinct-values(/playas/playa/Provincia) order by $p return $p");
        } else if (comunidad.equals("TODAS"))//Para rellenar todas las provincias
        {
            obtenerListasFiltro(provincias, "for $p in distinct-values(/playas/playa/Provincia) order by $p return $p");
        } else//En caso de tener un filtro activo
        {
            obtenerListasFiltro(provincias, "for $p in distinct-values(/playas/playa[Comunidad='" + comunidad + "']/Provincia) order by $p return $p");
        }
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
        //No ejecutar consulta si es nulo
        if (provincia != null) {
            if (!provincia.equals("TODAS"))//Tampoco si el campo TODAS es el selcceionado
            {
                comunidad = filtrar("for $p in distinct-values(/playas/playa[Provincia='" + provincia + "']/Comunidad) order by $p return $p");//Hay provincia seleccionada
            } else {
                comunidad = "TODAS";//Devolver al select en caso de que no haya nada seleccionado
            }
        }
        return comunidad;
    }

    /**
     * Para ejecutar una query de un unico resultado
     *
     * @param query Consulta a ejecutar en la BBDD
     * @return String resultado de la ejecución
     */
    private static String filtrar(String query) {
        abrirConexion();
        RemoteXMLResource resource = null;
        String resultado = "";
        try {
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Ejecutar consulta
            ResourceSet resultBusquedaUno = xpqs.query(query);
            ResourceIterator i = resultBusquedaUno.getIterator();
            resource = (RemoteXMLResource) i.nextResource();
            resource.getContent();
            resultado = resource.getContent().toString();//Obtener el string del resultado de la consulta

        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resource != null) {//Liberear res
                try {
                    ((EXistResource) resource).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
            if (col != null) {//cerrar conexión
                cerrarConexion();
            }
        }
        return resultado;
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
        //obtener la lista ordenada 
        //obtenerListas(comunidades,"Comunidad");
        obtenerListasFiltro(comunidades, "for $p in distinct-values(/playas/playa/Comunidad) order by $p return $p");
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
        abrirConexion();
        RemoteXMLResource resource = null;
        //Auxiliares para crear el objeto playa
        Playa playaAux;
        String nombre = "";
        String longitud = "";
        String comunidad = "";
        String provincia = "";
        String municipio = "";
        //Dependiendo de los valores seleccionados de los filtros habrá que ejecutar diferentes querys
        String query="";
        try {
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            
            if(comunidadSeleccionada.equals("TODAS"))//caso en el que no se ha seleccionado ningún valor; todos los campos estan en TODA/OS
                query = "for $p in (/playas/playa) order by $p/Nombre return $p";
            else
            {
                if (provinciaSeleccionada.equals("TODAS"))//caso hay comunidad seleccionada pero no hay provincia seleecionada
                {
                    query = "for $p in (/playas/playa[Comunidad='" + comunidadSeleccionada + "']) order by $p/Nombre return $p";
                }
                else
                    if (municipioSeleccionado.equals("TODOS"))//Caso hay comunidad y provincia pero no municipio seleccionado
                        query = "for $p in (/playas/playa[Comunidad='" + comunidadSeleccionada + "'][Provincia='" + provinciaSeleccionada + "']) order by $p/Nombre return $p";
                    else//Caso están todos los campos seleccionados
                        query = "for $p in (/playas/playa[Comunidad='" + comunidadSeleccionada + "'][Provincia='" + provinciaSeleccionada + "'][Municipio='" + municipioSeleccionado + "']) order by $p/Nombre return $p";
            }
            //Ejecutar la consulta
            ResourceSet result = xpqs.query(query);
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {//iterar el resultado
                resource = (RemoteXMLResource) i.nextResource();
                Element eElement = (Element) resource.getContentAsDOM();//Trabajar el resultado como DOM
                //Obtener el nombre y la longitud de la playa y la localización ya que en caso de no seleecionar ningún filtro saldria el strinng TODA/OS 
                nombre = (eElement.getElementsByTagName("Nombre").item(0).getTextContent());
                longitud = (eElement.getElementsByTagName("Longitud").item(0).getTextContent());
                comunidad = (eElement.getElementsByTagName("Comunidad").item(0).getTextContent());
                provincia = (eElement.getElementsByTagName("Provincia").item(0).getTextContent());
                municipio = (eElement.getElementsByTagName("Municipio").item(0).getTextContent());
                //Crear objeto playa
                playaAux = new Playa(nombre,municipio,provincia,comunidad,longitud);
                //Add a  la colección
                playas.add(playaAux);
            }
        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resource != null) {//Liberear res
                try {
                    ((EXistResource) resource).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
            if (col != null) {//cerrar conexión
                cerrarConexion();
            }
        }
        return playas;
    }

}
