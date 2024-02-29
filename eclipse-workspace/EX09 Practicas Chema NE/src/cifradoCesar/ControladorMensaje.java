package cifradoCesar;

import java.nio.file.FileSystemNotFoundException;

public class ControladorMensaje {
    
    public static void main(String[] args) {
        // Crear una instancia de la clase CifradorCesar
        CifradorCesar cifrar = new CifradorCesar();
        
        // Definir una clave para el cifrado
        int clave = 999298939;
        
        // Definir el mensaje original
        String mensaje = "A partir del codigo que se adjunta, resolver un algoritmo con cifrado CESAR.";
        
        // Cifrar el mensaje utilizando el cifrador César
        String mensajeCifrado = cifrar.cifrar(mensaje, clave);
        
        // Mostrar el mensaje cifrado
        System.out.println("Mensaje cifrado: " + mensajeCifrado);
        
        // Intentar descifrar el mensaje con una clave incorrecta (para mostrar un error)
        String mensajeMalDesifrado = cifrar.descifrar(mensajeCifrado, clave - 1);
        
        // Mostrar el mensaje mal descifrado (debería contener caracteres incorrectos)
        System.out.println("Mensaje mal desifrado debido a que la clave no es correcta: " + mensajeMalDesifrado);
        
        // Descifrar el mensaje correctamente utilizando la clave correcta
        String mensajeBienDesifrado = cifrar.descifrar(mensajeCifrado, clave);
        
        // Mostrar el mensaje correctamente descifrado
        System.out.println("Mensaje bien desifrado: " + mensajeBienDesifrado);
        
        // Mostrar el mensaje original para comparación
        System.out.println("Mensaje original: " + mensaje);
    }
}
