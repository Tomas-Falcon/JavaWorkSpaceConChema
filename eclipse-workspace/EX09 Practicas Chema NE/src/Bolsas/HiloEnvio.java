package Bolsas;

public class HiloEnvio extends Thread {
    private Bolsa bolsa;

    // Constructor que recibe una instancia de la clase Bolsa
    public HiloEnvio(Bolsa bolsa) {
        super(); // Llama al constructor de la clase Thread que está extendiendo
        this.bolsa = bolsa;
    }

    // Método que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        // Verifica si la bolsa no está llena
        if (!bolsa.estaLlena()) {
            try {
                // Bloquea el objeto bolsa y espera a ser notificado por otro hilo
                synchronized (bolsa) {
                    bolsa.wait(); // El hilo entra en estado de espera
                }
            } catch (InterruptedException e) {
                // Imprime detalles de la excepción si se produce
                e.printStackTrace();
            }
            // Imprime un mensaje indicando que se está enviando la bolsa y su tamaño
            System.out.println("Enviando la bolsa con " + bolsa.getSize() + " elementos");
        }
    }

    // Métodos getter y setter para acceder y modificar la instancia de Bolsa
    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }
}
