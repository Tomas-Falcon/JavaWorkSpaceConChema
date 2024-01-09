package Practica_Cliente_Servidor_Multihilo_EV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EcoServidor {
    public static void main(String[] args) {
        final int puerto = 12345;

        try {
            // Crear un socket de servidor en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Crear un nuevo hilo para manejar la comunicación con el cliente
                Thread clienteThread = new Thread(new ClienteHandler(clienteSocket));
                clienteThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket clienteSocket;

        public ClienteHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Configurar los flujos de entrada/salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

                // Leer datos del cliente y enviar el eco
                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    System.out.println("Cliente dice: " + mensajeCliente);

                    // Enviar el eco al cliente
                    salida.println("ECHO: " + mensajeCliente);

                    // Verificar si el cliente quiere desconectarse
                    if (mensajeCliente.equalsIgnoreCase("adios")) {
                        System.out.println("Cliente se desconectó.");
                        break;
                    }
                }

                // Cerrar la conexión con el cliente
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
