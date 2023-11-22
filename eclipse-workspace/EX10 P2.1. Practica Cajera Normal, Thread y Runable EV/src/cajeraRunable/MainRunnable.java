package cajeraRunable;

public class MainRunnable implements Runnable {

    private Cliente cliente;
    private Cajera cajera;
    private long initialTime;

    // Constructor de la clase MainRunnable
    public MainRunnable(Cliente cliente, Cajera cajera, long initialTime) {
        this.cajera = cajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    // Método principal
    public static void main(String[] args) {

        // Crear dos instancias de la clase Cliente con diferentes listas de productos en el carro de compra
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1, 3, 5, 1, 1});

        // Crear dos instancias de la clase Cajera con diferentes nombres
        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();

        // Crear dos instancias de MainRunnable, una para cada cliente y cajera
        Runnable proceso1 = new MainRunnable(cliente1, cajera1, initialTime);
        Runnable proceso2 = new MainRunnable(cliente2, cajera2, initialTime);

        // Iniciar dos hilos para ejecutar los procesos de compra concurrentemente
        new Thread(proceso1).start();
        new Thread(proceso2).start();
    }

    // Implementación del método run de la interfaz Runnable
    @Override
    public void run() {
        this.cajera.procesarCompra(this.cliente, this.initialTime);
    }

    // Métodos getter y setter para los atributos de la clase MainRunnable
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cajera getCajera() {
        return cajera;
    }

    public void setCajera(Cajera cajera) {
        this.cajera = cajera;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }
}
