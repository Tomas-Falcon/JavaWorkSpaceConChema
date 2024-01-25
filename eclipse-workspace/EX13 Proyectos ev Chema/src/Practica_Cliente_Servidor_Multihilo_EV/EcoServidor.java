package Practica_Cliente_Servidor_Multihilo_EV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EcoServidor {
    private static final List<ClienteHandler> clientesConectados = new ArrayList<>();
    private static int contadorClientes = 1;

    public static void main(String[] args) {
        final int puerto = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado como Cliente " + contadorClientes);

                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

                // Crear instancia del ClienteHandler y agregarla a la lista
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket, salida, contadorClientes);
                clientesConectados.add(clienteHandler);

                Thread clienteThread = new Thread(clienteHandler);
                clienteThread.start();

                contadorClientes++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private PrintWriter salida;
        private int numeroCliente;

        public ClienteHandler(Socket socket, PrintWriter salida, int numeroCliente) {
            this.clienteSocket = socket;
            this.salida = salida;
            this.numeroCliente = numeroCliente;
        }

        @Override
        public void run() {
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    System.out.println("Cliente " + numeroCliente + " dice: " + mensajeCliente);

                    // Enviar el eco a todos los clientes conectados
                    for (ClienteHandler otroCliente : clientesConectados) {
                        otroCliente.salida.println("ECHO Cliente " + numeroCliente + ": " + mensajeCliente);
                    }

                    // Verificar si el cliente quiere desconectarse
                    if (mensajeCliente.equalsIgnoreCase("adios")) {
                        System.out.println("Cliente " + numeroCliente + " se desconectó.");
                        break;
                    }
                }

                // Eliminar el ClienteHandler de la lista al desconectarse
                clientesConectados.remove(this);

                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
