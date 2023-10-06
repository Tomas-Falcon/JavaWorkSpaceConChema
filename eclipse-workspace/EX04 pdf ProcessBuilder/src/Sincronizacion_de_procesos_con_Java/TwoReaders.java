package Sincronizacion_de_procesos_con_Java;

public class TwoReaders {
	public static void main(String[] args) throws Exception {
		System.out.println("Dos procesos empiezan sincronizados");
		
		//se especifica el proceso "pa" para que ejecute "notepad.exe" y cree el book1.txt
		//lo mismo con el "pb"
		ProcessBuilder pa = new ProcessBuilder("notepad.exe", "C:\\book1.txt");
		ProcessBuilder pb = new ProcessBuilder("notepad.exe", "C:\\book2.txt");
		
		// el proceso p1 se asigna a el processBuilder pa.start;
		//que hace que empiece el proceso
		//Lo mismo con el p2
		Process p1 = pa.start();
		Process p2 = pb.start();
		
		//espera a los procesos p1 y p2 como si estuvieran pausados
		p1.waitFor();
		p2.waitFor();
		
		System.out.println("Espera a que ambos procesos se completen!");
	}
}