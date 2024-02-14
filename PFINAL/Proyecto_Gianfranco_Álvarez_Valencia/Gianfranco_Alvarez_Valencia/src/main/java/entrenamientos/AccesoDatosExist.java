/*
 * Acceso a Datos - Proyecto final
 */
package entrenamientos;

import entrenamientos.objetos.Cliente;
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
public class AccesoDatosExist {

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
            Logger.getLogger(AccesoDatosExist.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Obtener el nombre de una persona dado el apellido
     *
     * @param apel1
     * @return Devuelve el nombre de la personna
     */
    public static String buscarNombreUnico(String apel1) {

        String nombre = "";

        //El valor no puede ser null
        if (apel1 != null) {
            if (!apel1.equals("TODOS"))//No hacer la consula en valor TODOS
            {
                nombre = filtrar("for $p in distinct-values(/clientes/cliente[Apel1='" + apel1 + "']/Nombre) order by $p return $p");
            } else {
                nombre = "TODOS";//En este caso devolver "TODOS" 
            }
        }
        return nombre;
    }

    /**
     * Dado un nobnre mostrar los apellidos
     *
     * @param nombre
     * @return
     */
    public static ArrayList<String> buscarApels(String nombre) {
        ArrayList<String> apel1S = new ArrayList();
        abrirConexion();
        //obtener la lista ordenada y no repedita de apellidos
        if (nombre == null) //Caso nombre no seleccionada
        {
            obtenerListasFiltro(apel1S, "for $p in distinct-values(/clientes/cliente/Apel1) order by $p return $p");
        } else if (nombre.equals("TODOS"))//Para rellenar todos los apellidos
        {
            obtenerListasFiltro(apel1S, "for $p in distinct-values(/clientes/cliente/Apel1) order by $p return $p");
        } else//En caso de tener un filtro activo
        {
            obtenerListasFiltro(apel1S, "for $p in distinct-values(/clientes/cliente[Nombre='" + nombre + "']/Apel1) order by $p return $p");
        }
        return apel1S;
    }

    /**
     * Método para buscar nombres a aprtir del apel
     *
     * @param nombre
     * @return
     */
    public static ArrayList<String> buscarNombrePorApel(String apel1) {
        ArrayList<String> nombresApels = new ArrayList();
        abrirConexion();
        //obtener la lista ordenada y no repedita de apel1S
        if (apel1 == null) //Caso nombre no seleccionada
        {
            obtenerListasFiltro(nombresApels, "for $p in distinct-values(/clientes/cliente/Nombre) order by $p return $p");
        } else if (apel1.equals("TODOS"))//Para rellenar todas las apel1S
        {
            obtenerListasFiltro(nombresApels, "for $p in distinct-values(/clientes/cliente/Nombre) order by $p return $p");
        } else//En caso de tener un filtro activo
        {
            obtenerListasFiltro(nombresApels, "for $p in distinct-values(/clientes/cliente[Apel1='" + apel1 + "']/Nombre) order by $p return $p");
        }
        return nombresApels;
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
            Logger.getLogger(AccesoDatosExist.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Método para rellenar la lista con los nombres de los clientes
     *
     * @return Devuelve una lista con los nombres de los clientes
     */
    public static ArrayList<String> buscarNombre() {
        ArrayList<String> nombresClientes = new ArrayList();
        abrirConexion();
        obtenerListasFiltro(nombresClientes, "for $p in distinct-values(/clientes/cliente/Nombre) order by $p return $p");
        return nombresClientes;
    }

    public static String buscarIdCliente(String nombreCliente, String apellido) {
        abrirConexion();
        String idCliente = "";
        String query ="for $p in distinct-values(/clientes/cliente[Nombre='" + nombreCliente + "'][Apel1='" + apellido + "']/ID) order by $p return $p";
        if (!nombreCliente.equals("TODOS")) 
        {
                if (!apellido.equals("TODOS"))//
                {
                    idCliente = filtrar(query);
                }
        }
        return idCliente;
    }

    /**
     * Método para buscar la informacion de un cliente daodo su nombre y
     * apellido
     *
     * @param nombreCliente Nombre del cliente
     * @param apellido Apellido del cliente
     * @return Devuelve un objeto cliente con la información
     */
    public static Cliente buscarInfoCliente(String nombreCliente, String apellido) {
        abrirConexion();
        RemoteXMLResource resource = null;
        //Auxiliares para crear el objeto playa
        Cliente clienteAux = null;
        String id = "";
        String nombre = "";
        String apel1 = "";
        String apel2 = "";
        String telefono = "";
        String correo = "";

        String query = "";
        try {
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            if (!nombreCliente.equals("TODOS")) {
                if (!apellido.equals("TODOS"))//
                {
                    query = "for $p in (/clientes/cliente[Nombre='" + nombreCliente + "'][Apel1='" + apellido + "']) order by $p/Nombre return $p";

                    //Ejecutar la consulta
                    ResourceSet result = xpqs.query(query);
                    ResourceIterator i = result.getIterator();
                    while (i.hasMoreResources()) {//iterar el resultado
                        resource = (RemoteXMLResource) i.nextResource();
                        Element eElement = (Element) resource.getContentAsDOM();//Trabajar el resultado como DOM
                        id = (eElement.getElementsByTagName("ID").item(0).getTextContent());
                        nombre = (eElement.getElementsByTagName("Nombre").item(0).getTextContent());
                        apel1 = (eElement.getElementsByTagName("Apel1").item(0).getTextContent());
                        apel2 = (eElement.getElementsByTagName("Apel2").item(0).getTextContent());
                        telefono = (eElement.getElementsByTagName("Telefono").item(0).getTextContent());
                        correo = (eElement.getElementsByTagName("Email").item(0).getTextContent());
                        //Crear objeto cliente
                       
                        clienteAux = new Cliente(id, nombre, apel1, apel2, telefono, correo);
                    }
                }
            }

        } catch (XMLDBException ex) {
            Logger.getLogger(AccesoDatosExist.class.getName()).log(Level.SEVERE, null, ex);
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
        return clienteAux;
    }

}
