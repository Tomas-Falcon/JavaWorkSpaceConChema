import java.io.*;
import java.util.Random;

public class Lanzador {
		//Se hace una variable llamada "NUM_PROCESOS" en el cual se gurda la cantidad de "procesos" que se van a hacer
    static final int NUM_PROCESOS = 4;
    static final String PREFIJO_FICHEROS = "fich";

    public static void lanzarSumador(int n1, int n2, String fichResultados) throws IOException {
        String comando = "Sumador"; //se especifica el nombre del programa a lanzar
        File directorioSumador = new File("C:\\Users\\Tomas\\Git\\JavaWorkSpaceConChema\\eclipse-workspace\\EX08 Practica sumador\\bin"); //en que ruta se encuentra dicho programa
        File fichResultado = new File(fichResultados); // se crea un file con el nobre del fichero
        ProcessBuilder pb; //se instancia el ProcessBuilder 
        String s1 = n1 + "";
        String s2 = n2 + "";
        pb = new ProcessBuilder("java", comando, s1, s2); //se inicializa el ProcessBuilder, se prepara para ejecutar el comando "java Sumador x x" x = parametro
        pb.directory(directorioSumador); //se especifica que el ProcessBuilder tiene que estar ejecutandose en la carpeta directorioSumador
        pb.redirectOutput(ProcessBuilder.Redirect.to(fichResultado)); //se le pide al ProcessBuilder que rediridija la salida del proceso anterior a fichResultado
        pb.start(); // se inician los pasos anteriormente mencionados
    }

    public static int getResultadoFichero(String nombreFichero) {
        int suma = 0;
        System.out.println("Se crea el fichero con el nombre: \n" + nombreFichero);
        File file = new File(nombreFichero);
        
        if (file.exists() && file.length() > 0) {	//se crean los ficheros si existen y si es mayor a 0 la cantidad de ficheros existentes
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
    	//se obtienen los valores pasados como parametro
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        //se crea un Random (con los posibles valores del 0 a 9)
        Random random = new Random();
        //el bucle for se ejecuta hasta que i sea mayor o igual a NUM_PROCESOS
        for (int i = 1; i <= NUM_PROCESOS; i++) {
        	n1 = n1 + random.nextInt(10); //se modifican los valores ingresados con el random
            n2 = n2 + random.nextInt(10);
            System.out.println("n1: " + n1 + " el valor fue randomizado quedando así"); //se imprime como quedan los numero modificados
            System.out.println("n2: " + n2 + " el valor fue randomizado quedando así");
            lanzarSumador(n1, n2, PREFIJO_FICHEROS + String.valueOf(i)); //se ejecuta el metodo "lanzarSumador" que se le pasan los parametros de n1, n2, PREFIJO_FICHEROS + String.valueOf(i)
            System.out.println("Suma lanzada...");
        }
        Thread.sleep(1000); //se pausa el hilo por un segundo
        int sumaTotal = getSumaTotal(NUM_PROCESOS);
        System.out.println("La suma total es: " + sumaTotal);
    }
}
