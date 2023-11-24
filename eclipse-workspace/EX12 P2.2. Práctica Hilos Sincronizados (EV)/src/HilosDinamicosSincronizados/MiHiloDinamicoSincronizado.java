package HilosDinamicosSincronizados;

public class MiHiloDinamicoSincronizado implements Runnable {
    Thread hilo;
    sumArrayDinamicoSincronizado sumarray = new sumArrayDinamicoSincronizado();
    int a[];
    int resp;

    // Constructor
    MiHiloDinamicoSincronizado(String nombre, int nums[]) {
        hilo = new Thread(this, nombre);
        a = nums;
    }

    // Método para crear e iniciar el hilo
    public static MiHiloDinamicoSincronizado creaEInicia(String nombre, int nums[]) {
        MiHiloDinamicoSincronizado miHilo = new MiHiloDinamicoSincronizado(nombre, nums);

        miHilo.hilo.start(); // Inicia el hilo
        return miHilo;
    }

    // Punto de entrada del hilo
    public void run() {
        System.out.println(hilo.getName() + " iniciando.");

        // Llama al método sincronizado sumArray de la instancia de sumArrayDinamicoSincronizado
        resp = sumarray.sumArray(a);

        System.out.println("Suma para " + hilo.getName() + " es " + resp);
        System.out.println(hilo.getName() + " terminado.");
    }
}
