/*
 * Acceso a Datos - Tarea UT3
 */
package com.mycompany.ad03_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gainfranco Álvarez
 */
public class AccesoDatos {

    private static String url = "jdbc:mysql://localhost:3306/aerolinea";
    private static String user = "root";
    private static String password = "root";

    // Intenta obtener la conexión con la base de datos
    public static Connection getConnection() {
        Connection connection = null;
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Establece una conexión con la base de datos utilizando las variables estáticas "url", "user" y "password"
        //Si ocurre cualquier otro error durante la ejecución, muestra el mensaje en el log de la aplicación
        //establecer la coexion con la base de datos de Mysql
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("No se ha podido conectar" + e.getMessage());
        }
        //FIN
        return connection;
    }

    public static ArrayList<Vuelo> buscarVuelos() {

        ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
        Connection con = getConnection();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "vuelos" con la lista de todos los vuelos de la base de datos
        //Cada objeto vuelo debe contener el codigo de vuelo, la fecha y hora, el origen y el destino
        //Cierra la conexion a base de datos al terminar
        //Rellenar el array list con los vuelos
        try {
            //objeto temporal para rellenar la lista
            Vuelo vueloTemp = null;
            //Statement para la consulta
            Statement sentenciaVuelos = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los vuelos
            String sqlBuscarVuelos = "SELECT cod_vuelo,hora_salida,destino,procedencia FROM VUELOS ORDER BY cod_vuelo ASC";
            //Resulset de la ejecución de la consulta 
            ResultSet vuelosResultSet = sentenciaVuelos.executeQuery(sqlBuscarVuelos);
            //Recorrer el resulset para llenar el array
            while (vuelosResultSet.next()) {
                //Almacenar lo que devuelve cada interaccion de un resulset, una fila para generar un objeto vuelo que meter a la lista de vuelos que 
                //que es lo que se mostrará en la tabla
                String codVuelo = vuelosResultSet.getString(1);
                //Parsear la fecha para generar un date a partir del string de la consulta; habria que formatear en la parte del codigo que muestra la tabla 
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                Date horaSalida = null;//Null en caso de que el parseo de error
                try {
                    horaSalida = formatoFecha.parse(vuelosResultSet.getString(2));
                } catch (ParseException ex) {
                    Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
                String destino = vuelosResultSet.getString(3);
                String origen = vuelosResultSet.getString(4);
                //Crear un objeto vuelo temporal para llenarlo con los datos de la fila
                vueloTemp = new Vuelo();
                vueloTemp.setCodigo_vuelo(codVuelo);
                vueloTemp.setFecha_hora(horaSalida);
                vueloTemp.setOrigen(origen);
                vueloTemp.setDestino(destino);
                //System.out.println("datos " + codVuelo + " " + horaSalida + " " + destino + " " + origen);
                //añadir el objeto vuelo con los datos de la fila al arraylist de vuelos
                vuelos.add(vueloTemp);
            }
            vuelosResultSet.close(); // Cerrar ResultSet
            sentenciaVuelos.close(); // Cerrar Statement
            con.close(); // Cerrar conexión
        } catch (SQLException cn) {
            cn.printStackTrace();
        }

        //FIN
        return vuelos;
    }

    public static ArrayList<Pasajero> buscarPasajeros(String codigoVuelo) {

        ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
        Connection con = getConnection();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "pasajeros" con la lista de todos los pasajeros del vuelo pasado por parámetro
        //Cada objeto pasajero debe contener el dni, el tipo de plaza (Turista o Primera) y el asiento que ocupa
        //Cierra la conexion a base de datos al terminar
        //Rellenar el array list con los pasajeros
        try {
            //objto temporal para rellenar la lista
            Pasajero pasajerosTemp = null;
            //Statement para la consulta
            Statement sentenciaPasajeros = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los pasajeros del codigo de vuel seleecionado en la tabla
            String sqlBuscarPasajeros = "SELECT dni,tipo_plaza,plaza FROM PASAJEROS WHERE cod_vuelo LIKE '" + codigoVuelo + "'ORDER BY dni ASC";
            //Resulset de la ejecución de la consulta 
            ResultSet pasajerosResultSet = sentenciaPasajeros.executeQuery(sqlBuscarPasajeros);
            //Comporbar el resulset
            //si no hay pasajeros regestrados mostrarlo en la tabla
            if (!pasajerosResultSet.isBeforeFirst()) 
            {
                System.out.println("No hay pasajeros en el vuelo: " + codigoVuelo);
                //Agregar texxto a los atributos del objeto para mostrarlo en la tabla
                pasajerosTemp = new Pasajero();
                pasajerosTemp.setDni("No hay");
                pasajerosTemp.setTipo_plaza("pasajeros ");
                pasajerosTemp.setPlaza("registrados");
                pasajeros.add(pasajerosTemp);
            }
            //Recorrer el resulset para llenar el array
            while (pasajerosResultSet.next()) {
                //Almacenar lo que devuelve cada interaccion de un resulset, una fila para generar un objeto pasajer que meter a la lista de pasajeros  
                //que es lo que se mostrará en la tabla
                String dniPasajero = pasajerosResultSet.getString(1);
                String tipoPlazaPasajero = pasajerosResultSet.getString(2);
                String plazaPasajero = pasajerosResultSet.getString(3);
                //Crear un objeto pasajero temporal para llenarlo con los datos de la fila
                pasajerosTemp = new Pasajero();
                pasajerosTemp.setDni(dniPasajero);
                pasajerosTemp.setTipo_plaza(tipoPlazaPasajero);
                pasajerosTemp.setPlaza(plazaPasajero);
                //añadir el objeto pasajero con los datos de la fila al arraylist de pasajeros
                pasajeros.add(pasajerosTemp);

            }
            pasajerosResultSet.close(); // Cerrar ResultSet
            sentenciaPasajeros.close(); // Cerrar Statement
            con.close(); // Cerrar conexión
        } catch (SQLException cn) {
            cn.printStackTrace();
        }
        //FIN
        return pasajeros;
    }

    public static String nuevoPasajero(String codigoVuelo, String dni, String tipoPlaza, String asiento) {

        String mensaje = "El pasajero se ha creado correctamente";
        Connection con = getConnection();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea un nuevo pasajero en la base de datos con los datos pasados por parámetro
        //Si ya existe un pasajero con el mismo DNI en el mismo vuelo, rellena la variable local "mensaje" con un mensaje relevante
        //Si el asiento seleccionado ya está ocupado en ese vuelo, rellena la variable local "mensaje" con un mensaje relevante
        //Si se ha superado el número máximo de plazas de turista y/o primera clase en el vuelo, rellena la variable local "mensaje" con un mensaje relevante
        //Si ocurre cualquier otro error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Cierra la conexion a base de datos al terminar
       // boolean dniExiste = false;
       // boolean asientoExiste = false;
        int totalPlazasTurista = 0;//Número total de plazas turista 
        int totalPlazasPrimera = 0;//Numero total de plazas de primera
        int totalPlazasTuristaOcupadas = 0;//Numero total de plazas ocupadas turista
        int totalPlazasPrimeraOcupadas = 0;//Numero total de plazas ocupadas primera
        try 
        {
            //comprobar datos del vuelo, que haya asientos disponibles
            Statement sentenciaDatosVuelo = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán el total de asientos del codvuelo
            String sqlTotalAsientos = "SELECT plazas_turista,plazas_primera FROM vuelos WHERE cod_vuelo LIKE '" + codigoVuelo + "'";
            //Resulset de la ejecución de la consulta 
            ResultSet pasajerosResultSetTotal = sentenciaDatosVuelo.executeQuery(sqlTotalAsientos);
            //Recoger el número total de plazas que tiene el vuelo de cada tipo
            while(pasajerosResultSetTotal.next())
            {
            totalPlazasTurista = pasajerosResultSetTotal.getInt(1);
            totalPlazasPrimera = pasajerosResultSetTotal.getInt(2);
            }
            
            //Obtener el numero de personas que va en cada clase de asiento del vuelo
            //Plazas ocupadas en turista
            Statement sentenciaAsientosVueloTurista = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán el total de asientos turista ocuapdos del codvuelo
            String sqlTotalAsientosOcupadosTurista = "SELECT COUNT(dni) FROM pasajeros WHERE cod_vuelo LIKE '" + codigoVuelo + "'"+ " AND tipo_plaza like 'TU'";
            //Resulset de la ejecución de la consulta 
            ResultSet pasajerosResultSetTotalAsientosOcupadosturista = sentenciaAsientosVueloTurista.executeQuery(sqlTotalAsientosOcupadosTurista);
            //Recoger el número total de plazas ocuapdas en turista
            while(pasajerosResultSetTotalAsientosOcupadosturista.next())
            {
            totalPlazasTuristaOcupadas = pasajerosResultSetTotalAsientosOcupadosturista.getInt(1);//La consulta devuelve un resgitro
            }
            
            //Plazas ocupadas en primera
            Statement sentenciaAsientosVueloPrimera = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán el total de asientos primera ocuapdos del codvuelo
            String sqlTotalAsientosOcupadosPrimera = "SELECT COUNT(dni) FROM pasajeros WHERE cod_vuelo LIKE '" + codigoVuelo + "'"+ " AND tipo_plaza like 'PR'";
            //Resulset de la ejecución de la consulta 
            ResultSet pasajerosResultSetTotalAsientosOcupadosPrimera = sentenciaAsientosVueloPrimera.executeQuery(sqlTotalAsientosOcupadosPrimera);
            //Recoger el número total de plazas ocuapdas en primera
            while(pasajerosResultSetTotalAsientosOcupadosPrimera.next())
            {
            totalPlazasPrimeraOcupadas = pasajerosResultSetTotalAsientosOcupadosPrimera.getInt(1);
            }
            //Comporbar plazas disponibles
            if((totalPlazasPrimeraOcupadas + totalPlazasTuristaOcupadas + 1) > (totalPlazasPrimera + totalPlazasTurista))//No habria asiento al añadir uno
                mensaje = "El número total de plazas ha sido superado";
            else
            {
                if (tipoPlaza.equals("Turista")) 
                    if ((totalPlazasTuristaOcupadas + 1) > totalPlazasTurista)//no habria asientos en turista
                        mensaje = "No hay plaza disponible en turista";
                    else
                        //caso se puden añadir ;llmar metodo aux
                        mensaje = comprobarAsiento(codigoVuelo, dni, tipoPlaza, asiento);
                        
                if (tipoPlaza.equals("Primera")) 
                    if ((totalPlazasPrimeraOcupadas + 1) > totalPlazasPrimera)//No abria aientos en primera
                        mensaje = "No hay plaza disponible en primera";
                    else
                    {
                        //caso se puden añadir;llamar metodo aux 
                        mensaje = comprobarAsiento(codigoVuelo, dni, tipoPlaza, asiento);
                    }                    
            }    
        } 
        catch (SQLException cn) {
            cn.printStackTrace();
        }
        //FIN
        return mensaje;
    }
    /**
     * Método auxialiar para comprobar que el Dni ni el asiento esten resgistrados, si se puede se realiza el insert
     * @param codigoVuelo
     * @param dni
     * @param tipoPlaza
     * @param asiento
     * @return Devuelve un mensaje con el estado del alta del pasajero
     */
    private static String comprobarAsiento(String codigoVuelo, String dni,String tipoPlaza, String asiento)
    {
        Connection con = getConnection();
        String mensaje ="";
        //Booleanos para comoprrobar si el pasjero se puede insertar
        boolean dniDisponible = false;
        boolean asientoDisponible= false;
        try 
        {
            //comprobar que el pasajero y el asiento no existan        
            //comprobar si ya existe el pasajero
            Statement sentenciaPasajerosExiste = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los pasajeros del codigo de vuel seleecionado en la tabla
            String sqlExistePasajeros = "SELECT dni,plaza FROM pasajeros WHERE cod_vuelo LIKE '" + codigoVuelo + "'";
            //Resulset de la ejecución de la consulta 
            ResultSet pasajerosResultSetExiste = sentenciaPasajerosExiste.executeQuery(sqlExistePasajeros);
            //Sttring con el insert
            String sqlInsert= ("INSERT INTO pasajeros VALUES (?,?,?,?)");
            //Preparedstatement para el insert
            PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert);
            //cargar la sentencia con los parámetros
            pstmtInsert.setString(1, dni);
            pstmtInsert.setString(2, codigoVuelo);
            //Pasar el tipo de plaza bien, en al aplicacion recoge la informacion como Primera o Turista. Para que cuadre con la BBDD
            if (tipoPlaza.equals("Primera"))   
                pstmtInsert.setString(3, "PR");
            if (tipoPlaza.equals("Turista")) 
                pstmtInsert.setString(3, "TU");
           pstmtInsert.setString(4, asiento);          
            while (pasajerosResultSetExiste.next())
            {   //Comprobar si existe el DNI
                if (!(pasajerosResultSetExiste.getString(1).equals(dni)))
                {
                    dniDisponible = true;//Dni disponible
                    //Comporbar si el asiento esta ocupado
                    if (!(pasajerosResultSetExiste.getString(2).equals(asiento)))
                        asientoDisponible = true;//Asiento disponible
                    else
                    {
                        mensaje = "El asiento esta ocupado";
                        asientoDisponible = false;//Asiento no disponible
                    }
                }
                else
                {
                    mensaje = "El dni ya existe en el vuelo";
                    dniDisponible = false;//dni no disponible
                }
            }
            //insert en caso de que DNI y asiento esten disponibles
            if ((dniDisponible = true) && (asientoDisponible = true) )
            {
                pstmtInsert.executeUpdate();
                pstmtInsert.close();
                sentenciaPasajerosExiste.close();
                pasajerosResultSetExiste.close();
                con.close();
                mensaje = "Pasajero añadido";              
            }
        } 
        catch (SQLException cn) 
        {
            cn.printStackTrace();
        }

        return mensaje;
    }
    
    public static String cambiarAsiento(String codigoVuelo, String dni, String tipoPlaza, String asiento) {

        String mensaje = "El asiento se ha cambiado correctamente";
        Connection con = getConnection();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Permite cambiar el tipo y número de plaza de un pasajero en un vuelo.
        //Si el asiento seleccionado ya está ocupado en ese vuelo, rellena la variable local "mensaje" con un mensaje relevante
        //Si se ha superado el número máximo de plazas de turista y/o primera clase en el vuelo, rellena la variable local "mensaje" con un mensaje relevante
        //Si ocurre cualquier otro error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Cierra la conexion a base de datos al terminar
        //FIN
        return mensaje;
    }

    public static String borrarPasajero(String codigoVuelo, String dni) {

        String mensaje = "El pasajero se ha eliminado correctamente";
        Connection con = getConnection();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Elimina el pasajero seleccionado del vuelo seleccionado
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Cierra la conexion a base de datos al terminar
        //FIN
        return mensaje;
    }

}
