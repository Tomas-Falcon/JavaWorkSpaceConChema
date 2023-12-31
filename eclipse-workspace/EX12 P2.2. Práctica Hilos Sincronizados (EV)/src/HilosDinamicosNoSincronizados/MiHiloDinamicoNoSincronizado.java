package HilosDinamicosNoSincronizados;

public class MiHiloDinamicoNoSincronizado implements Runnable {
    Thread hilo;
    sumArrayDinamicoNoSincronizado sumarray = new sumArrayDinamicoNoSincronizado();
    int a[];
    int resp;

    MiHiloDinamicoNoSincronizado(String nombre, int nums[]) {
        hilo = new Thread(this, nombre);
        a = nums;
    }

    public static MiHiloDinamicoNoSincronizado creaEInicia(String nombre, int nums[]) {
        MiHiloDinamicoNoSincronizado miHilo = new MiHiloDinamicoNoSincronizado(nombre, nums);

        miHilo.hilo.start();
        return miHilo;
    }

    public void run() {
        int sum;
        System.out.println(hilo.getName() + " iniciando.");

        // Llama al método no sincronizado sumArray de la instancia de sumArrayDinamicoNoSincronizado
        resp = sumarray.sumArray(a);

        System.out.println("Suma para " + hilo.getName() + " es " + resp);
        System.out.println(hilo.getName() + " terminado.");
    }
}
