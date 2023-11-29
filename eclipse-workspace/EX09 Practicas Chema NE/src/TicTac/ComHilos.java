package TicTac;

// Clase que representa la coordinación de hilos en el juego Tic-Tac-Toe
public class ComHilos {
    public static void main(String[] args) {
        // Se crea una instancia de la clase TicTac que será compartida entre los hilos
        TicTac tt = new TicTac();

        // Se crea e inicia un hilo llamado "Tic" y otro hilo llamado "Tac"
        MiNHilo mh1 = MiNHilo.crearEIniciar("Tic", tt);
        MiNHilo mh2 = MiNHilo.crearEIniciar("Tac", tt);

        try {
            // El hilo principal espera a que los hilos "Tic" y "Tac" finalicen su ejecución
            mh1.hilo.join();
            mh2.hilo.join();
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
    }
}
