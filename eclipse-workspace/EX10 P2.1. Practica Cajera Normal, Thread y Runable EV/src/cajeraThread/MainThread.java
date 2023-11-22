package cajeraThread;

public class MainThread {

    public static void main(String[] args) {

        // Crear dos instancias de la clase Cliente con diferentes listas de productos en el carro de compra
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1, 3, 5, 1, 1});

        // Obtener el tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();

        // Crear dos instancias de la clase CajeraThread, cada una con su respectivo cliente
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);

        // Iniciar la ejecución de los hilos
        cajera1.start();
        cajera2.start();
    }
}
