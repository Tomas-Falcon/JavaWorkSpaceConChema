package p3_5_practica_sockets_udp_tareas_1_2_pareja_ejercicio_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteDiferenciaHora {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.2.195", 12345); // Conectar al servidor en localhost y puerto 12345
            socket.setSoTimeout(5000); // Establecer un tiempo de espera de 5000 milisegundos

            // Leer la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String respuesta = in.readLine();

            // Obtener la hora local
            Date horaLocal = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String horaLocalStr = formatoFecha.format(horaLocal);

            // Mostrar la hora local y la hora del servidor
            System.out.println("Hora local: " + horaLocalStr);
            System.out.println("Hora del servidor: " + respuesta);

            // Calcular la diferencia entre la hora local y la hora del servidor
            try {
                Date fechaServidor = formatoFecha.parse(respuesta);
                long diferenciaEnMilisegundos = horaLocal.getTime() - fechaServidor.getTime();
                long diferenciaEnSegundos = diferenciaEnMilisegundos / 1000 / 60;

                System.out.println("Diferencia: " + diferenciaEnSegundos + " Minutos");
            } catch (ParseException e) {
                System.out.println("Error al parsear la fecha del servidor.");
            }

            // Cerrar la conexión con el servidor
            socket.close();
        } catch (SocketTimeoutException e) {
            System.out.println("Error: Tiempo de espera agotado. No se recibió respuesta del servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
