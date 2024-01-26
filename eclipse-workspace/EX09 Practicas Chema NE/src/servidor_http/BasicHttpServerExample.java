
package servidor_http;

//Importando las clases necesarias
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class BasicHttpServerExample {
    // Método principal
    public static void main(String[] args) throws IOException {
        // Crear una instancia de HttpServer que escucha en el puerto 8500
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        
        // Crear un contexto para la ruta "/"
        HttpContext context = server.createContext("/");
        
        // Establecer el manejador para la solicitud en el contexto creado
        context.setHandler(BasicHttpServerExample::handleRequest);
        
        // Iniciar el servidor
        server.start();
    }

    // Método que maneja la solicitud HTTP
    private static void handleRequest(HttpExchange exchange) throws IOException {
        // Respuesta HTML que se enviará al cliente
        String response = "<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Mi Página HTML</title></head><body><h1>Hola, Mundo!</h1><p>Lorem ipso?.</p></body></html>\r\n";
        
        // Configurar los encabezados de la respuesta HTTP
        exchange.sendResponseHeaders(200, response.getBytes().length);
        
        // Obtener el flujo de salida para escribir la respuesta
        OutputStream os = exchange.getResponseBody();
        
        // Escribir la respuesta en el flujo de salida
        os.write(response.getBytes());
        
        // Cerrar el flujo de salida
        os.close();
    }
}
