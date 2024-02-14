/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entrenamientos;

import entrenamientos.objetos.Ejercicios;
import entrenamientos.objetos.Rutinas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gianf
 */
public class AccesoDatosSql {

    private static String url = "jdbc:mysql://localhost:3306/entrenamiento";
    private static String user = "root";
    private static String password = "root";

    /**
     * Conectar con la BBDD entrenamiento
     *
     * @return Obj de la conexión
     */
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

    /**
     * Obtener la lista de rutinas de un cliente pasado su id cliente
     *
     * @param codCliente id del cliente
     * @return Devuelve una lista con las rutinas del cliente
     */
    public static ArrayList<Rutinas> buscarRutinas(String codCliente) {

        ArrayList<Rutinas> rutinas = new ArrayList<Rutinas>();
        Connection con = getConnection();
        try {
            //objeto temporal para rellenar la lista
            Rutinas rutinaAux = null;
            //Statement para la consulta
            Statement sentenciaRutinas = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los rutinas
            String sqlBuscarRutinas = "SELECT COD_RUTINA,FRECUENCIA_SEMANA FROM rutinas WHERE ID_CLIENTE LIKE '" + codCliente + "'ORDER BY COD_RUTINA ASC";
            //Resulset de la ejecución de la consulta 
            ResultSet rutinasResultSet = sentenciaRutinas.executeQuery(sqlBuscarRutinas);
            //Recorrer el resulset para llenar el array
            while (rutinasResultSet.next()) {
                //Almacenar lo que devuelve cada interaccion de un resulset, una fila para generar un objeto rutina que meter a la lista de rutinas que 
                //que es lo que se mostrará en la tabla
                String codRutina = rutinasResultSet.getString(1);
                String frecSemana = rutinasResultSet.getString(2);
                //Crear un objeto vuelo temporal para llenarlo con los datos de la fila
                rutinaAux = new Rutinas(codRutina, codCliente, frecSemana);
                //añadir el objeto vuelo con los datos de la fila al arraylist de rutinas
                rutinas.add(rutinaAux);
            }
            rutinasResultSet.close(); // Cerrar ResultSet
            sentenciaRutinas.close(); // Cerrar Statement
            con.close(); // Cerrar conexión
        } catch (SQLException cn) {
            cn.printStackTrace();
        }

        //FIN
        return rutinas;
    }

    /**
     * Obtener la lista de ejercicios de una rutina
     *
     * @param codRutina Identificador de la rutina
     * @return Devuelve una lista de los ejercicios de la rutina
     */
    public static ArrayList<Ejercicios> buscarEjercicios(String codRutina) {

        ArrayList<Ejercicios> ejercicios = new ArrayList<Ejercicios>();
        Connection con = getConnection();
        try {
            //objeto temporal para rellenar la lista
            Ejercicios ejercicioAux = null;
            //Statement para la consulta
            Statement sentenciaEjercicios = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los ejercicios
            String sqlBuscarEjercicios = "SELECT NOMBRE,GRUPO_MUSCULAR,SERIES,REPETICIONES,PESO_ADD FROM ejercicios WHERE COD_RUTINA LIKE '" + codRutina + "'";
            //Resulset de la ejecución de la consulta 
            ResultSet ejerciciosResultSet = sentenciaEjercicios.executeQuery(sqlBuscarEjercicios);
            //Recorrer el resulset para llenar el array
            while (ejerciciosResultSet.next()) {
                //Almacenar lo que devuelve cada interaccion de un resulset, una fila para generar un objeto ejericio que meter a la lista de ejercicos que 
                //que es lo que se mostrará en la tabla
                String nombre = ejerciciosResultSet.getString(1);
                String grupoMuscular = ejerciciosResultSet.getString(2);
                String series = ejerciciosResultSet.getString(3);
                String repeticiones = ejerciciosResultSet.getString(4);
                String peso = ejerciciosResultSet.getString(5);
                //Crear un objeto vuelo temporal para llenarlo con los datos de la fila
                ejercicioAux = new Ejercicios(nombre, codRutina, grupoMuscular, series, repeticiones, peso);
                //añadir el objeto vuelo con los datos de la fila al arraylist de rutinas
                ejercicios.add(ejercicioAux);
            }
            ejerciciosResultSet.close(); // Cerrar ResultSet
            sentenciaEjercicios.close(); // Cerrar Statement
            con.close(); // Cerrar conexión
        } catch (SQLException cn) {
            cn.printStackTrace();
        }

        //FIN
        return ejercicios;
    }

    public static void addRutina(String codRutina, String idCliente, String frecSemana) {
        try {
            int exiteCodrutina = 0;
            Connection con = getConnection();
            //comprobar si ya existe el pasajero
            Statement sentenciaRutinaExiste = con.createStatement();
            //String para la consulta SQL 
            String sqlExisteRutina = "SELECT COUNT(COD_RUTINA) FROM rutinas WHERE COD_RUTINA LIKE '" + codRutina + "'";
            //Resulset de la ejecución de la consulta 
            ResultSet rutinaResultSetExiste = sentenciaRutinaExiste.executeQuery(sqlExisteRutina);
            
            while (rutinaResultSetExiste.next()) {
                exiteCodrutina = rutinaResultSetExiste.getInt(1);//La consulta devuelve un resgitro
            }
            
            //ADD si no existe el cod de rutina
            if (exiteCodrutina != 1) {
                String sqlInsert = ("INSERT INTO rutinas VALUES (?,?,?)");
                //Preparedstatement para el insert
                PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert);
                //cargar la sentencia con los parámetros
                pstmtInsert.setString(1, codRutina);
                pstmtInsert.setString(2, idCliente);
                pstmtInsert.setString(3, frecSemana);
                pstmtInsert.executeUpdate();
                pstmtInsert.close();
                con.close();
            }
            else
                JOptionPane.showMessageDialog(null, "Cod rutina ya existe","", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addEjercicio(String nombre, String codRutina, String gMuscular, String series, String repetciones, String peso) {
        try {
            int exiteEjercicio = 0;
            Connection con = getConnection();
            //comprobar si ya existe el ejercicio
            Statement sentenciaEjercicioExiste = con.createStatement();
            //String para la consulta SQL que se ejecutará en la base de datos y con la que se obtendrán los 
            String sqlExisteRutina = "SELECT COUNT(NOMBRE) FROM ejercicios WHERE COD_RUTINA LIKE '" + codRutina + "' AND NOMBRE LIKE '" + nombre + "'";
            //Resulset de la ejecución de la consulta 
            ResultSet ejerResultSetExiste = sentenciaEjercicioExiste.executeQuery(sqlExisteRutina);
            
            while (ejerResultSetExiste.next()) {
                exiteEjercicio = ejerResultSetExiste.getInt(1);//La consulta devuelve un resgitro
            }
            
            //ADD si no existe el cod de rutina
            if (exiteEjercicio != 1) {
                String sqlInsert = ("INSERT INTO ejercicios VALUES (?,?,?,?,?,?)");
                //Preparedstatement para el insert
                PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert);
                //cargar la sentencia con los parámetros
                pstmtInsert.setString(1, nombre);
                pstmtInsert.setString(2, codRutina);
                pstmtInsert.setString(3, gMuscular);
                pstmtInsert.setInt(4, Integer.parseInt(series) );
                pstmtInsert.setInt(5, Integer.parseInt(repetciones) );
                pstmtInsert.setInt(6, Integer.parseInt(peso) );
                pstmtInsert.executeUpdate();
                pstmtInsert.close();
                con.close();
            }
            else
                JOptionPane.showMessageDialog(null, "El jercicio ya existe en la rutina","", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDatosSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
