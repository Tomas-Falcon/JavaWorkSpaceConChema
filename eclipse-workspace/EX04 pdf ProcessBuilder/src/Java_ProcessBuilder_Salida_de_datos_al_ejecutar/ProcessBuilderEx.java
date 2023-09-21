package Java_ProcessBuilder_Salida_de_datos_al_ejecutar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProcessBuilderEx {
	
	public static void main(String[] args) throws IOException {
		
		var processBuilder = new ProcessBuilder();
		
		processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");
		
		var process = processBuilder.start();
		
		try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
