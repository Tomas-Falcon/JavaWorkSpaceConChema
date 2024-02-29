package cifradoCesar;

public class CifradorCesar {
    
    // Definición del abecedario que se utilizará para el cifrado
    public static String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789,.";

    // Método para cifrar un mensaje utilizando el cifrado César
    public static String cifrar(String mensaje, int clave) {
        // Inicialización de la cadena que almacenará el mensaje cifrado
        String mensajecifrado = "";
        
        // Convertir el mensaje a mayúsculas para asegurar consistencia
        mensaje = mensaje.toUpperCase();
        
        // Recorrer cada caracter del mensaje original
        for(int i=0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            // Verificar si el caracter es un espacio y mantenerlo sin cambios
            if(caracter == ' ') {
                mensajecifrado += caracter;
            } else {
                // Obtener la posición actual del caracter en el abecedario
                int posicion = abecedario.indexOf(caracter);
                // Calcular la nueva posición aplicando la clave
                int posicionNueva = (posicion + clave) % abecedario.length();
                // Obtener el nuevo caracter cifrado
                char caracterNuevo = abecedario.charAt(posicionNueva);
                // Agregar el caracter cifrado al mensaje
                mensajecifrado += caracterNuevo;
            }
        }
        
        // Devolver el mensaje cifrado
        return mensajecifrado;
    }
    
    // Método para descifrar un mensaje cifrado con el cifrado César
    public static String descifrar(String mensaje, int clave) {
        // Inicialización de la cadena que almacenará el mensaje descifrado
        String mensajedescifrado = "";
        
        // Convertir el mensaje a mayúsculas para asegurar consistencia
        mensaje = mensaje.toUpperCase();
        
        // Recorrer cada caracter del mensaje cifrado
        for(int i=0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            // Verificar si el caracter es un espacio y mantenerlo sin cambios
            if(caracter == ' ') {
                mensajedescifrado += caracter;
            } else {
                // Obtener la posición actual del caracter en el abecedario
                int posicion = abecedario.indexOf(caracter);
                // Calcular la nueva posición restando la clave y ajustar por desbordamiento
                int posicionNueva = (posicion - clave + abecedario.length()) % abecedario.length();
                // Obtener el nuevo caracter descifrado
                char caracterNuevo = abecedario.charAt(posicionNueva);
                // Agregar el caracter descifrado al mensaje
                mensajedescifrado += caracterNuevo;
            }
        }
        
        // Devolver el mensaje descifrado
        return mensajedescifrado;
    }
}
