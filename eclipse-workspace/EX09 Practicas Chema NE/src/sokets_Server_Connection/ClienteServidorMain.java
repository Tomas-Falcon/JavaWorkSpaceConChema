package sokets_Server_Connection;

public class ClienteServidorMain {

    public static void main(String[] args) {
        // Crear e iniciar hilos para dos clases distintas

        // Hilo para una nueva clase llamada MiServidor
        Thread serverThread = new Thread(() -> Servidor.main(args));
        serverThread.start();

        try {
            Thread.sleep(1000);  // Esperar un momento antes de iniciar la clase cliente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hilo para una nueva clase llamada MiCliente
        Thread clientThread = new Thread(() -> Cliente.main(args));
        clientThread.start();
    }
}
