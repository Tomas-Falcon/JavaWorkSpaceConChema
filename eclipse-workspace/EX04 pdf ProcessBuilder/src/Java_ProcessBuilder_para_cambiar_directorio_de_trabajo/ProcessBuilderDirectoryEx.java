package Java_ProcessBuilder_para_cambiar_directorio_de_trabajo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderDirectoryEx {
	public static void main(String[] args) throws IOException {
		// Obtener el directorio de inicio del usuario actual
		var homeDir = System.getProperty("user.home");

		// Crear un objeto ProcessBuilder
		var pb = new ProcessBuilder();

		// Configurar el comando a ejecutar: "cmd.exe /c dir"
		pb.command("cmd.exe", "/c", "dir");

		// Establecer el directorio de trabajo del proceso como el directorio de inicio del usuario
		pb.directory(new File(homeDir));

		// Iniciar el proceso
		var process = pb.start();

		// Crear un lector de entrada para capturar la salida del proceso
		try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			// Leer y mostrar la salida del proceso línea por línea.
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
