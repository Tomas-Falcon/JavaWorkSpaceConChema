import java.io.*;

public class FicheroTextoApp {
	public static void main(String[] args) {
		try {

			FileWriter fw = new FileWriter("C:\\Users\\Tomas\\Desktop\\fichero1.txt");

			fw.write("Esto es una prueb");
			fw.write(95);
			fw.write(97);

			fw.close();
			
			FileReader fr = new FileReader("C:\\Users\\Tomas\\Desktop\\fichero1.txt");

			int valor = fr.read();
			while (valor != -1) {
				System.out.print((char) valor);
				valor = fr.read();
			}

			fr.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}
	}
}