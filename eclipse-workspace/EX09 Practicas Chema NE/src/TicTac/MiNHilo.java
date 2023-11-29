package TicTac;

public class MiNHilo implements Runnable {
    Thread hilo;        // Objeto Thread que representa el hilo
    TicTac ttob;        // Objeto TicTac que será compartido entre los hilos

    // Constructor que inicializa el hilo y el objeto TicTac asociado
    MiNHilo(String nombre, TicTac tt) {
        hilo = new Thread(this, nombre);
        ttob = tt;
    }

    // Método para crear e iniciar un hilo
    public static MiNHilo crearEIniciar(String nombre, TicTac tt) {
        MiNHilo miNHilo = new MiNHilo(nombre, tt);
        miNHilo.hilo.start(); // Inicia el hilo
        return miNHilo;
    }

    // Método que se ejecuta cuando se inicia el hilo
    public void run() {
        // Si el nombre del hilo es "Tic", realiza la secuencia de "tic" en el objeto TicTac
        if (hilo.getName().compareTo("Tic") == 0) {
            for (int i = 0; i < 5; i++)
                ttob.tic(true);  // Realiza la acción "tic" con el estado inicial como verdadero
            ttob.tic(false);     // Realiza la acción final de "tic" con el estado inicial como falso
        } else {
            // Si el nombre del hilo no es "Tic", realiza la secuencia de "tac" en el objeto TicTac
            for (int i = 0; i < 5; i++)
                ttob.tac(true);  // Realiza la acción "tac" con el estado inicial como verdadero
            ttob.tac(false);     // Realiza la acción final de "tac" con el estado inicial como falso
        }
    }
}
