package HilosEstaticosSincronizados;

public class Sincronizacion {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5};

        // Crea e inicia el primer hilo
        MiHilo mh1 = MiHilo.creaEInicia("#1", a);

        // Crea e inicia el segundo hilo
        MiHilo mh2 = MiHilo.creaEInicia("#2", a);

        try {
            // Espera a que ambos hilos terminen su ejecución
            mh1.hilo.join();
            mh2.hilo.join();
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}
