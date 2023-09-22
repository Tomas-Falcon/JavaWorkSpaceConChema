package Java_ProcessBuilder_para_redirigir_la_entrada_salida_de_datos;

import java.io.File;
import java.io.IOException;

public class ProcessBuilderRedirectIOEx {
    public static void main(String[] args) throws IOException {
        // Crear un objeto ProcessBuilder
        var processBuilder = new ProcessBuilder();

        // Configurar el comando a ejecutar como "cmd.exe" y "copy con"
        processBuilder.command("cmd.exe", "copy con");

        // Redirigir la entrada estándar del proceso desde "input.txt"
        processBuilder.redirectInput(new File("src/recursos", "input.txt"));

        // Redirigir la salida estándar del proceso a "output.txt"
        processBuilder.redirectOutput(new File("src/recursos/", "output.txt"));

        // Iniciar el proceso
        processBuilder.start();
    }
}
