import java.io.*;
/**
 * 
 */
public class ClaserMain {

	/**
	 * @param args
	 */
	
	
	// Escribir y crear el fichero
	public static void main(String[] args) {
		FileWriter fichero = null;

		PrintWriter escritor = null;

		 

		try {

		  fichero = new FileWriter("C:\\Users\\Tomas\\Desktop\\carpetin\\ficherin.txt");
		  //Ubicacion
		  
		  escritor = new PrintWriter(fichero) ;
		  //Escritura
		  escritor.println("Esto es una línea del fichero");

		} catch (IOException ioe) {

		  ioe.printStackTrace() ;

		} finally {

		if (fichero != null)

		  try {

		    fichero.close();

		  } catch (IOException ioe) {
			  
		  }

		}

	}

}
