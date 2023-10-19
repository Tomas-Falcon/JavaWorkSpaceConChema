import java.io.*;
import java.util.Random;

public class Lanzador {

    static final int NUM_PROCESOS = 4;
    static final String PREFIJO_FICHEROS = "fich";

    public static void lanzarSumador(int n1, int n2, String fichResultados) throws IOException {
        String comando = "Sumador";
        File directorioSumador = new File("C:\\Users\\Tomas\\Git\\JavaWorkSpaceConChema\\eclipse-workspace\\EX08 Practica sumador\\bin");
        File fichResultado = new File(fichResultados);
        ProcessBuilder pb;
        String s1 = n1 + "";
        String s2 = n2 + "";
        pb = new ProcessBuilder("java", comando, s1, s2);
        pb.directory(directorioSumador);
        pb.redirectOutput(ProcessBuilder.Redirect.to(fichResultado));
        pb.start();
    }

    public static int getResultadoFichero(String nombreFichero) {
        int suma = 0;
        File file = new File(nombreFichero);
        
        if (file.exists() && file.length() > 0) {
            try (FileInputStream fichero = new FileInputStream(file);
                 InputStreamReader fir = new InputStreamReader(fichero);
                 BufferedReader br = new BufferedReader(fir)) {
                String linea = br.readLine();
                suma = Integer.parseInt(linea);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("No se pudo abrir " + nombreFichero);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al leer " + nombreFichero);
            }
        } else {
            System.out.println("El archivo " + nombreFichero + " no existe o está vacío.");
        }
        
        return suma;
    }


    public static int getSumaTotal(int numFicheros) {
        int sumaTotal = 0;
        for (int i = 1; i <= numFicheros; i++) {
            sumaTotal += getResultadoFichero(PREFIJO_FICHEROS + String.valueOf(i));
        }
        return sumaTotal;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        Random random = new Random();
        for (int i = 1; i <= NUM_PROCESOS; i++) {
        	n1 = n1 + random.nextInt(10);
            n2 = n2 + random.nextInt(10);
            System.out.println("n1: " + n1 + " el valor fue randomizado quedando así");
            System.out.println("n2: " + n2 + " el valor fue randomizado quedando así");
            lanzarSumador(n1, n2, PREFIJO_FICHEROS + String.valueOf(i));
            System.out.println("Suma lanzada...");
        }
        Thread.sleep(1000);
        int sumaTotal = getSumaTotal(NUM_PROCESOS);
        System.out.println("La suma total es: " + sumaTotal);
    }
}
