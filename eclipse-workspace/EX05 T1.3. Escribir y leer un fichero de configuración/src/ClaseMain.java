/**
 * 
 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class ClaseMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Obtenemos el nombre del archivo que esta en la posicion 0 del array args(que tiene que estar en el mismo directorio que los java)
		String ficheroConfiguracion = args [0];
		
		//Le pasamos eso al metodo "leerPropiedades" el nombre del fichero 
		leerPropiedades(ficheroConfiguracion);
		
	}
		public static void leerPropiedades(String ficheroConfiguracion) {
			
			
			Properties configuracion = new Properties();
			//creamos un File con la "direccion de la configuracion"
			File ficheroConfig = new File (ficheroConfiguracion);
			int cantidad = 0;

			try {
				//Toma todos los "Datos" de configuracion
			  configuracion.load(new FileInputStream(ficheroConfig));

			  //de los datos que tomamos, seleccionamos el valor de la clave de "cantidad"
			  //y se la damos a un int llamado cantidad
			  cantidad = Integer.parseInt(configuracion.getProperty("cantidad"));
			  System.out.println("El valor de cantidad es: " + cantidad + " y se le sumara uno");
			  //le sumamos 1
			  cantidad++;
			  
			  //lo pasamos a string
			  String cantidadFinal = String.valueOf(cantidad);
			  
			  //vamos al metodo para sobre escribir el fichero config y le pasamos el string
			  sobreEscribir(cantidadFinal);

			} catch (FileNotFoundException fnfe ) { 

			  fnfe.printStackTrace();

			} catch (IOException ioe) { 

			  ioe.printStackTrace();

			}
		}
		
		public static void sobreEscribir(String cantidad) {
			
			
			Properties configuracion = new Properties();
			//decimos que la clave "cantidad" tendra el valor de cantidad
			configuracion.setProperty("cantidad", cantidad);
			

			try {
				// sobre escribimos el fichero y le damos el nombre de "inventario.conf" si no existe, se crea
				// y la primera linea de ese archivo dice "Fichero de cantidades", la segunda linea dice la ultima modificacion y la tercera linea 
				//contiene la clave cantidad con su valor
			  configuracion.store(new FileOutputStream("inventario.conf"),"Fichero de cantidades");
			  System.out.println("Quedando como: " + cantidad);
			  System.out.println("los cambios ya se realizaron con exito!");

			} catch (FileNotFoundException fnfe ) { 

					fnfe.printStackTrace(); 

				} catch (IOException ioe) { 

					ioe.printStackTrace();
			  
				}
		}
		
		

}
