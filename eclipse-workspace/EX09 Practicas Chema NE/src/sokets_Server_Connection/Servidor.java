package sokets_Server_Connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        // Crear e iniciar un hilo para el servidor
        Thread serverThread = new Thread(() -> iniciarServidor());
        serverThread.start();

        // Resto de la lógica del programa principal
        System.out.println("Programa principal sigue ejecutándose...");
    }

    private static void iniciarServidor() {
        try {
            ServerSocket server = new ServerSocket(0);
            System.out.println("Servidor esperando conexión en el puerto: " + server.getLocalPort());

            while (true) {
                // Esperar a que un cliente se conecte
                Socket recepcion = server.accept();
                System.out.println("Cliente conectado desde: " + recepcion.getRemoteSocketAddress());

                // Enviar datos al cliente
                String paquete = "Informacion que solicita la clase cliente";
                PrintWriter out = new PrintWriter(recepcion.getOutputStream(), true);
                out.println(paquete);

                // Cerrar el socket del cliente
                recepcion.close();
            }
        } catch (IOException e) {
            // Manejo de la excepción de E/S (IOException)
            e.printStackTrace();
        }
    }
}
