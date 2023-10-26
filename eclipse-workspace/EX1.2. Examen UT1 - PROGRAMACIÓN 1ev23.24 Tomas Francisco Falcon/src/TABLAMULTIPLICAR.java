/**
 * 
 */
import java.io.*;
/**
 * 
 */
public class TABLAMULTIPLICAR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numA = Integer.parseInt(args[0]);
		
		procesoDeMultiplicar(numA);
	}
	
	private static void procesoDeMultiplicar(int m1) {
		int i = 1;
		ProcessBuilder pb;
		 String comando = "Multiplicar"; //se especifica el nombre de la clase
		while(i < 11) {
			
			pb = new ProcessBuilder("java", comando, String.valueOf(m1), String.valueOf(i)); //hacemos el ProcessBuilder
					
				String arg [] = {String.valueOf(m1),String.valueOf(i)};
				Multiplicar.main(arg);
			try {
				pb.start();
			} catch (IOException e) {

				e.printStackTrace();
			}
			i++;
		}
		
	}
	
	
}
