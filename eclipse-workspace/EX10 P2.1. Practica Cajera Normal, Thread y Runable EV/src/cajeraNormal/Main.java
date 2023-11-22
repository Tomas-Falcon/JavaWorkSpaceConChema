package cajeraNormal;

public class Main {

    public static void main(String[] args) {

        // Crear dos instancias de la clase Cliente con diferentes listas de productos en el carro de compra
        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });

        // Crear dos instancias de la clase Cajera con diferentes nombres
        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        // Obtener el tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();

        // Procesar la compra del primer cliente con la primera cajera
        cajera1.procesarCompra(cliente1, initialTime);

        // Procesar la compra del segundo cliente con la segunda cajera
        cajera2.procesarCompra(cliente2, initialTime);
    }
}
