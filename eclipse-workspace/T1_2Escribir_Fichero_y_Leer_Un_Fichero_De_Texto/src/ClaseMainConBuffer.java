import java.io.*;
/**
 * 
 */
public class ClaseMainConBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Se dclaran los objetos
		
		File fichero = null;

		FileReader lector = null;

		BufferedReader buffer = null;

		 

		try {
			//Se termina la creacion del objeto y se accede al archivo
		  buffer = new BufferedReader(new FileReader(new File("C:\\Users\\Tomas\\Desktop\\carpetin\\ficherin.txt")));

		  String linea = null;

		  while ((linea = buffer.readLine()) != null)

		    System.out.println(linea);

		} catch (FileNotFoundException fnfe) { 

		  fnfe.printStackTrace();

		} catch (IOException ioe) { 

		  ioe.printStackTrace(); 

		} finally {

		  if (buffer != null)

		  try {

		    buffer.close();

		  } catch (IOException ioe) {
			  
		  }

		}

	}

}
