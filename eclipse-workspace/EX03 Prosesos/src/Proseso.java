import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class Proseso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ProcessBuilder pb = new ProcessBuilder("java","ClaseMain","2","6","2");
		
		pb.directory(new File("C:\\Users\\Tomas\\Git\\JavaWorkSpaceConChema\\eclipse-workspace\\EX03 Suma Parte 2 del EX03\\bin"));
		
		File f = new File("salida.txt");
		
		pb.redirectErrorStream(true);
		pb.redirectOutput(f);
		
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}