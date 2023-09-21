package Java_ProcessBuilder_Salida_de_datos_al_ejecutar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderEx {
    
    public static void main(String[] args) throws IOException {
        
        // Creamos una instancia de ProcessBuilder para configurar y ejecutar un proceso externo.
        var processBuilder = new ProcessBuilder();
        
        // Configuramos el comando que se va a ejecutar (en este caso, un comando ping a google.com).
        processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");
        
        // Iniciamos el proceso externo.
        var process = processBuilder.start();
        
        // Creamos un BufferedReader para leer la salida del proceso.
        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            
            // Leemos y mostramos cada línea de la salida del proceso.
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
