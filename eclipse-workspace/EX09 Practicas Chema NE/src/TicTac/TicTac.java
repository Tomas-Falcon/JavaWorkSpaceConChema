package TicTac;

// Clase que gestiona la lógica de la interacción entre los hilos "Tic" y "Tac"
public class TicTac {
    String estado;

    // Método que representa una acción "tic"
    synchronized void tic(boolean enProceso) {
        // Si no está en proceso, marca el estado como "ticmarcado" y notifica a otros hilos
        if (!enProceso) {
            estado = "ticmarcado";
            notify();
            return;
        }

        // Si está en proceso, realiza la acción "Tic" y marca el estado como "ticmarcado"
        System.out.print("Tic ");
        estado = "ticmarcado";
        notify();
        try {
            // Espera hasta que el estado cambie a "tacmarcado"
            while (!estado.equals("tacmarcado"))
                wait();
        } catch (InterruptedException exc) {
            System.out.println("Hilo interrumpido.");
        }
    }

    // Método que representa una acción "tac"
    synchronized void tac(boolean enProceso) {
        // Si no está en proceso, marca el estado como "tacmarcado" y notifica a otros hilos
        if (!enProceso) {
            estado = "tacmarcado";
            notify();
            return;
        }

        // Si está en proceso, realiza la acción "Tac" y marca el estado como "tacmarcado"
        System.out.println("Tac");
        estado = "tacmarcado";
        notify();
        try {
            // Espera hasta que el estado cambie a "ticmarcado"
            while (!estado.equals("ticmarcado"))
                wait();
        } catch (InterruptedException exc) {
            System.out.println("Hilo interrumpido.");
        }
    }
}
