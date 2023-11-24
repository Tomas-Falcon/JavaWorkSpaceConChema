package HilosDinamicosSincronizados;

public class DinamicoSincronizado {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5};

        // Crear e instancian los hilo
        MiHiloDinamicoSincronizado mh1 = MiHiloDinamicoSincronizado.creaEInicia("#1", a);
        MiHiloDinamicoSincronizado mh2 = MiHiloDinamicoSincronizado.creaEInicia("#2", a);

        try {
            // Esperar a que ambos hilos finalicen su ejecución antes de continuar con el hilo principal
            mh1.hilo.join();
            mh2.hilo.join();
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}