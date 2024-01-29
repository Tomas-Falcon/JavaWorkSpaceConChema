package p3_5_practica_sockets_udp_tareas_1_2_pareja_ejercicio_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Puerto del servidor

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera a que un cliente se conecte
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Obtener la hora y el día actual
                Date fechaActual = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String horaYDia = formatoFecha.format(fechaActual);

                // Enviar la hora y el día al cliente
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(horaYDia);

                // Cerrar la conexión con el cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
