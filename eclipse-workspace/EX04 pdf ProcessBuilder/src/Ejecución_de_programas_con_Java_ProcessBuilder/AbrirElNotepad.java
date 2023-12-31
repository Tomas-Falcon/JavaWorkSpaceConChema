package Ejecución_de_programas_con_Java_ProcessBuilder;


import java.io.IOException;

public class AbrirElNotepad {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Se genera una variable tipo "var" y se le asigna el valor del ProcessBuilder
		// El ProcessBuilder crea procesos
		var processBuilder = new ProcessBuilder();
		
		//se utiliza la variable var ProcessBuilder y se ejecuta el metodo "comand"
		//que ejecuta ejecuta el comando "notepad.exe" en la consola (cmd)
		//y abre el bloc de notas
		processBuilder.command("notepad.exe");
		
		// se inicia el proceso con el .start();
		var process = processBuilder.start();
		
		//el waitFor(); espera.
		var ret = process.waitFor();
		
		System.out.printf("Program exited with code: %d", ret);
	}
}
