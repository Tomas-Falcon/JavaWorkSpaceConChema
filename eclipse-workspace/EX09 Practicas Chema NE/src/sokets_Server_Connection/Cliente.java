package sokets_Server_Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 50165);
            System.out.println("Conectado al servidor en el puerto: " + s.getLocalPort());

            // Recibir datos del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String datosRecibidos = br.readLine();
            System.out.println("Datos recibidos del servidor: " + datosRecibidos);

            // Cerrar el socket del cliente
            s.close();
        } catch (UnknownHostException une) {
            System.out.println("NO se encuentra el servidor");
        } catch (IOException une) {
            System.out.println("Error en la comunicación");
        }
    }
}
