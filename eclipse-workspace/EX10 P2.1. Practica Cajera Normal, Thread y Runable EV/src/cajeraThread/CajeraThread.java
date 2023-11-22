package cajeraThread;

public class CajeraThread extends Thread {

    private String nombre;
    private Cliente cliente;
    private long initialTime;

    // Constructor de la clase CajeraThread
    CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    // Método run que se ejecutará cuando se inicie el hilo
    @Override
    public void run() {

        // Mensaje al inicio del proceso de compra
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");

        // Iterar sobre los productos en el carro de compra del cliente
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            // Simular el procesamiento de cada producto
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            // Mensaje indicando que se ha procesado un producto
            System.out.println("Procesado el producto " + (i + 1)
                    + " del cliente " + this.cliente.getNombre() + "->Tiempo: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000
                    + "seg");
        }

        // Mensaje al finalizar el proceso de compra
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000
                + "seg");
    }

    // Método privado para simular la espera de ciertos segundos
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Métodos getter y setter para los atributos de la clase CajeraThread
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }
}
