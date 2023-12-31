package Bolsas;

public class Principal {

    public static void main(String[] args) {
        // Crear una instancia de la clase Bolsa
        Bolsa bolsa = new Bolsa();
        
        // Crear una instancia de la clase HiloEnvio y pasarle la Bolsa como parámetro
        HiloEnvio hilo = new HiloEnvio(bolsa);
        
        // Iniciar el hilo de envío
        hilo.start();

        // Bucle para agregar productos a la bolsa
        for (int i = 0; i <= 10; i++) {
            // Crear un nuevo producto
            Producto p = new Producto();

            try {
                // Bloquear el objeto bolsa para la sincronización
                synchronized (bolsa) {
                    // Hacer que el hilo principal duerma durante 1000 milisegundos (1 segundo)
                    Thread.sleep(1000);

                    // Verificar si la bolsa está llena
                    if (bolsa.estaLlena()) {
                        // Notificar al hilo de envío que puede proceder
                        bolsa.notify();
                    }
                }
            } catch (InterruptedException e) {
                // Imprimir detalles de la excepción si se produce
                e.printStackTrace();
            }

            // Agregar el producto a la bolsa
            bolsa.addProducto(p);

            // Imprimir el tamaño actual de la bolsa
            System.out.println(bolsa.getSize());
        }
    }
}