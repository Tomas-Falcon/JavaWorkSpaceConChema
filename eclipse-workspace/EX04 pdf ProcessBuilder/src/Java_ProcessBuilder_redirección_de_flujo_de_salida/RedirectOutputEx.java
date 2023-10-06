package Java_ProcessBuilder_redirección_de_flujo_de_salida;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedirectOutputEx {
	
	public static void main(String[] args) throws IOException {
		
		// Obtenemos el directorio de inicio del usuario actual.
		var homeDir = System.getProperty("user.home");
		
		// Creamos una instancia de ProcessBuilder para configurar y ejecutar un proceso externo.
		var processBuilder = new ProcessBuilder();
		
		// Configuramos el comando que se va a ejecutar (en este caso, "date /t" en el símbolo del sistema de Windows).
		processBuilder.command("cmd.exe", "/c", "date /t");
		
		// Especificamos el nombre del archivo en el que redirigiremos la salida del proceso.
		var fileName = new File(String.format("%s\\Documents\\output.txt", homeDir));
		//Crea un archivo con la fecha de hoy en esa dereccion 
		
		// Redirigimos la salida del proceso hacia el archivo especificado.
		//si se comenta la siguiente linea, se ejecuta el while y la salida de la informacion sale por consola
		processBuilder.redirectOutput(fileName);
		
		// Iniciamos el proceso externo.
		var process = processBuilder.start();
		
		// Creamos un BufferedReader para leer la salida del proceso.
		try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			//la condicion del while no se cumple debido a que ya se redirecciono la salida con "processBuilder.redirectOutput(fileName);"
			//y la informacion esta en la ruta "fileName" en un archivollamado "output.txt"
			while ((line = reader.readLine()) != null) {
				// Mostramos cada línea de la salida del proceso en la consola.
				System.out.println(line);
			}
		}
	}
}
