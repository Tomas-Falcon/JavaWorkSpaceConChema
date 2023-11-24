package HilosDinamicosNoSincronizados;

public class DinamicoNoSincronizado {
    public static void main(String[] args) {
        // Array de números
        int a[] = { 1, 2, 3, 4, 5 };

        // Crear e iniciar dos hilos con el mismo array de números
        MiHiloDinamicoNoSincronizado mh1 = MiHiloDinamicoNoSincronizado.creaEInicia("#1", a);
        MiHiloDinamicoNoSincronizado mh2 = MiHiloDinamicoNoSincronizado.creaEInicia("#2", a);

        try {
            // Esperar a que ambos hilos terminen
            mh1.hilo.join();
            mh2.hilo.join();
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}
