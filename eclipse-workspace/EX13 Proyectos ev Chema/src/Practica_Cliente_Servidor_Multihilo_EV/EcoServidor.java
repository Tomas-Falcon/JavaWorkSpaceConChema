package Practica_Cliente_Servidor_Multihilo_EV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EcoServidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando conexiones...");

            // Inicia 5 hilos de clientes
            for (int i = 0; i < 5; i++) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String input;
                while ((input = reader.readLine()) != null) {
                    System.out.println("Cliente dice: " + input);

                    if (input.equalsIgnoreCase("adios")) {
                        writer.println("Adiós, que tengas un buen día");
                        break; // Finaliza el hilo si recibe "adios"
                    } else {
                        writer.println("ECO"); // Responde con "ECO"
                    }
                }

                System.out.println("Conexión cerrada con el cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
