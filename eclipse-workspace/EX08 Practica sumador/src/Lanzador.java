import java.io.*;
import java.util.Random;

public class Lanzador {

	static final int NUM_PROCESOS = 1;
	static final String PREFIJO_FICHEROS = "fich";

	public static void lanzarSumador(int n1, int n2, String fichResultados) throws IOException {
		String comando;
		comando = "Sumador";
		File directorioSumador;
		directorioSumador = new File("C:\\Users\\Tomas\\Git\\JavaWorkSpaceConChema\\eclipse-workspace\\EX08 Practica sumador\\bin");
		File fichResultado = new File(fichResultados);
		ProcessBuilder pb;
		
		pb = new ProcessBuilder("java", comando, String.valueOf(n1), String.valueOf(n2));
		pb.directory(directorioSumador);
		pb.redirectOutput(fichResultado);
		pb.start();
	}

	public static int getResultadoFichero(String nombreFichero) {
		int suma = 0;
		try {
			FileInputStream fichero = new FileInputStream(nombreFichero);
			InputStreamReader fir = new InputStreamReader(fichero);
			BufferedReader br = new BufferedReader(fir);
			String linea = br.readLine();
			suma = Integer.parseInt(linea);
			return suma;
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo abrir " + nombreFichero);
		} catch (IOException e) {
			System.out.println("No hay nada en " + nombreFichero);
		}
		return suma;
	}

	public static int getSumaTotal(int numFicheros) {
		int sumaTotal = 0;
		for (int i = 1; i <= NUM_PROCESOS; i++) {
			sumaTotal += getResultadoFichero(PREFIJO_FICHEROS + String.valueOf(i));
		}
		return sumaTotal;
	}

	/*
	 * Recibe dos parámetros y hará la suma de los valores comprendidos entre ambos
	 * parametros
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		Random random = new Random();
        int b = random.nextInt(10);
        int b1 = random.nextInt(10);
        n1 = n1 + b;
        n2 = n2 + b1;
		for (int i = 1; i <= NUM_PROCESOS; i++) {
			System.out.println("n1:" + n1);
			System.out.println("n2:" + n2);
			lanzarSumador(n1, n2, PREFIJO_FICHEROS + String.valueOf(i));
			System.out.println("Suma lanzada...");
		}
		Thread.sleep(1000);
		int sumaTotal = getSumaTotal(NUM_PROCESOS);
		System.out.println("La suma total es:" + sumaTotal);
	}
}