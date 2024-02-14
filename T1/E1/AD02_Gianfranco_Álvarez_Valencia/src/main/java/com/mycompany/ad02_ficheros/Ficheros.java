/*
 * Acceso a Datos - Tarea UT2
 */
package com.mycompany.ad02_ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alba Tortosa
 */
public class Ficheros {

    public static ArrayList<Fichero> buscarFicheros(String ruta) {

        ArrayList<Fichero> ficheros = new ArrayList<Fichero>();

        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Rellena la variable local "ficheros" con la lista de ficheros adecuada
        //crear un file f
        File f = new File(ruta);
        File[] datosFicheros = f.listFiles();//guardar en un array todos los obejtos ficheros de la ruta
        for (int x = 0;x < datosFicheros.length;x++)//Recorrer todos los obetos obetenidos en el array
        {
            // Obtener el tipo de archivo
            String tipodeArchivo="";
            //obtener el tipo
            long ultimaMod = datosFicheros[x].lastModified();//para darle formato fecha
            Date ultimaModDate = new Date(ultimaMod);
            try {
                if (datosFicheros[x].isDirectory())//Comprobar si es un directorio
                    tipodeArchivo = "\tDir\t";
                else
                    tipodeArchivo = Files.probeContentType(datosFicheros[x].toPath());
            } catch (IOException ioException) 
            {
                System.out.println("Error: " + ioException.getMessage());
            }
            //crear el objeto de la clase Fichero
            Fichero ficheroAux = new Fichero(datosFicheros[x].getName(), tipodeArchivo , datosFicheros[x].length()/1024, ultimaModDate);
            //Añadir el obejto a la lista de ficheros
            ficheros.add(ficheroAux);
        }
        //Cada objeto fichero debe contener el nombre del fichero, el tipo (Fichero / Carpeta), el tamaño en Kilobytes y la última fecha de modificación
        //FIN
        return ficheros;
    }

    public static String exportarTxt(ArrayList<Fichero> ficheros, File ficheroDestino) {
        String mensaje = "La lista de ficheros se ha exportado correctamente";
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Escribe en el fichero pasado por parámetro la lista de ficheros pasada por parámetro
        //Incluye las cabeceras de la tabla y utiliza tabuladores para que se mantenga el formato de tabla
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Buff para escribir en el fichero
        BufferedWriter writer = null;
        
        try {
            writer = new BufferedWriter(new FileWriter(ficheroDestino,true));
            //Escribir cabeceras
            if (ficheroDestino.length() == 0)//Si esta vacio escribir las cabeceras la primera vez que se escribe en el archivo
            {
                writer.write("Nombre\t\tTipo\t\tTamaño\tFecha modificacion");
                writer.newLine();
            }
            // Hay que recorrer la lista y escirbir la informacion de cada objeto en el txt.
            // Declarar iterador
            Iterator<Fichero> it;
            it = ficheros.iterator();
            while(it.hasNext())
            {
                Fichero fich = it.next();
                //escribir los detalles  en el fichero
                writer.write(fich.getNombre() + "\t" + fich.getTipo() + "\t" + fich.getKilobytes()+ "\t" + fich.getUltimaModificacion() + "\n");// 

            }
        } catch (IOException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Ha ocurrido un error";
        }
        finally 
        {
            try 
            {
                writer.close();
            } catch (IOException ex) 
            {
                Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
                mensaje = "Ha ocurrido un error";
            }
	}
        //FIN
        return mensaje;
    }

    public static String nuevaCarpeta(String ruta, String nombreCarpeta) {
        String mensaje = "La carpeta se ha creado correctamente";
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea una carpeta en la ruta "ruta" pasada por parámetro y con el nombre "nombreCarpeta" pasado por parámetro
        //Si ya existe una carpeta con ese nombre en esa ruta, rellena la variable local "mensaje" con un mensaje relevante
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        System.out.println(ruta + File.separator + nombreCarpeta);//file separator para el slash
        File directorio = new File(ruta + File.separator + nombreCarpeta);//crear dir
        System.out.println(ruta);
        if (!directorio.exists()) //comprobar el directorio
        {
            if (!directorio.mkdirs())
               mensaje = ("Error al crear directorio");
        }
        else
            mensaje = ("Ya existe la carpeta " + nombreCarpeta); 
        //FIN
        return mensaje;
    }

    public static String nuevoFichero(String ruta, String nombreFichero) {
        String mensaje = "El fichero se ha creado correctamente";
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea un fichero en la ruta "ruta" pasada por parámetro y con el nombre "nombreFichero" pasado por parámetro
        //La extensión del fichero será TXT
        //Si ya existe un fichero con ese nombre en esa ruta, rellena la variable local "mensaje" con un mensaje relevante
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        File nuevoFichero = new File (ruta,nombreFichero + ".txt");//añadir extension txt
        try {
            if (nuevoFichero.createNewFile()) 
            {
                System.out.println("El fichero se ha creado correctamente");
            } else 
            {
                mensaje = ("No se ha podido crear el fichero");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            mensaje = "Ha ocurrido un error";
        }
        //FIN
        return mensaje;
    }

    public static String borrarFichero(String ruta, String nombreFichero) {
        String mensaje = "El fichero se ha borrado correctamente";
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Borra el fichero pasado por parámetro de la ruta pasada por parámetro
        //Si el fichero no existe (o es un directorio), rellena la variable local "mensaje" con un mensaje relevante
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        File ficheroAux = new File(ruta,nombreFichero);
        if (ficheroAux.isDirectory())//En caso de que sea directorio
            mensaje = "No se puede borrar, es un directorio";
        else
            if (ficheroAux.delete())
                System.out.println("El fichero ha sido borrado satisfactoriamente");
            else
                mensaje = ("No se ha podido borrar el fichero");
        //FIN
        return mensaje;
    }

    public static String copiarFichero(String ruta, String fichero) {
        String mensaje = "El fichero se ha copiado correctamente";
        //ESCRIBE AQUI TU CODIGO
        //INICIO
        //Crea una copia del fichero pasado por parámetro en la ruta pasada por parámetro
        //El nombre del fichero debe ser el texto "Copia de " seguido del nombre original
        //Si ocurre algún error durante la ejecución, rellena la variable local "mensaje" con un mensaje relevante
        //Copiar mediante Stream comprobando que nosea un directorio o ya halla sido copiado
        File origen = new File(ruta,fichero);
        File copia = new File(ruta,"Copia de " + fichero);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (origen.isDirectory())
            mensaje = "Error es un directorio";
        else
            if (!copia.exists()) 
            {
                try {
                    inputStream = new FileInputStream(origen);
                    outputStream = new FileOutputStream(copia);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) 
                    {
                        outputStream.write(buffer, 0, length);
                    }
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    mensaje = "Error al copiar";
                }
            }
            else
                mensaje = "El archivo ya fue copiado";
        //FIN
        return mensaje;
    }

}
