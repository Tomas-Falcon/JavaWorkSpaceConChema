package p3_5_practica_sockets_udp_tareas_1_2_pareja_ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.2.195", 12345); // Conectar al servidor en localhost y puerto 12345
            socket.setSoTimeout(5000); // Establecer un tiempo de espera de 5000 milisegundos

            // Leer la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String respuesta = in.readLine();

            // Imprimir la respuesta en la salida estándar
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar la conexión con el servidor
            socket.close();
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Tiempo de espera agotado. No se recibió respuesta del servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
