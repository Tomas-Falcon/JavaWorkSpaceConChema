package algoritmoHash;

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class DigestExample {

    public static void main(String[] args) {
        String password = "passwordsin D:";

        try {
            // SHA-512
            generarYMostrarHash("SHA-512", password);

            // SHA-1
            generarYMostrarHash("SHA-1", password);

            // MD5
            generarYMostrarHash("MD5", password);

            // Ejemplo adicional: SHA-256
            generarYMostrarHash("SHA-256", password);

            // Modificación/Prueba adicional
            String otraCadena = "otra cadena de prueba";
            generarYMostrarHash("MD5", otraCadena);

            // Verificar si un hash coincide con una cadena dada
            String hashMD5 = generarHash("MD5", password);
            boolean coincidencia = comprobarHash("MD5", password, hashMD5);
            System.out.println("La cadena coincide con el hash MD5: " + coincidencia);

        } catch (NoSuchAlgorithmException e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }

    private static void generarYMostrarHash(String algoritmo, String cadena) throws NoSuchAlgorithmException {
        String hash = generarHash(algoritmo, cadena);
        System.out.println(algoritmo + " Hash: " + hash);
    }

    private static String generarHash(String algoritmo, String cadena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algoritmo);
        md.update(cadena.getBytes());
        byte[] mb = md.digest();
        return new String(Hex.encodeHex(mb));
    }

    private static boolean comprobarHash(String algoritmo, String cadena, String hashEsperado)
            throws NoSuchAlgorithmException {
        String hashGenerado = generarHash(algoritmo, cadena);
        return hashGenerado.equals(hashEsperado);
    }
}
