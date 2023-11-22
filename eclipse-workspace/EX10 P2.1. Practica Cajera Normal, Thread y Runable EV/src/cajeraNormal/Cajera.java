package cajeraNormal;
public class Cajera {

    private String nombre;

    // Constructor de la clase Cajera
    Cajera(String nombre) {
        this.nombre = nombre;
    }

    // Método para procesar la compra de un cliente
    public void procesarCompra(Cliente cliente, long timeStamp) {

        // Mensaje al inicio del proceso de compra
        System.out.println("La cajera " + this.nombre +
                " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                "seg");

        // Iterar sobre los productos en el carro de compra del cliente
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            // Simular el procesamiento de cada producto
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            // Mensaje indicando que se ha procesado un producto
            System.out.println("Procesado el producto " + (i + 1) +
                    " ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                    "seg");
        }

        // Mensaje al finalizar el proceso de compra
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
                cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
    }

    // Método privado para simular la espera de ciertos segundos
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Métodos getter y setter para el nombre de la cajera
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}