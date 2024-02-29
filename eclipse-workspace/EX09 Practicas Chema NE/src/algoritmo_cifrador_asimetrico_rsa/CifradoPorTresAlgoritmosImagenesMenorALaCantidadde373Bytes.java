package algoritmo_cifrador_asimetrico_rsa;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class CifradoPorTresAlgoritmosImagenesMenorALaCantidadde373Bytes {

    public static void main(String[] args) throws Exception {

        // Archivo de imagen a cifrar, esa imagen es menor a 373 bytes
        File archivo = new File("C:/Users/Tomas/Desktop/p42/aa.png");
        FileInputStream imgInputStream = new FileInputStream(archivo);

        // Creamos el cifrador RSA
        Cipher rsa;

        // Generamos el par de claves RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Guardamos el par de claves en variables
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Establecemos el tipo de cifrado RSA
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // Leemos el contenido de la imagen
        byte[] imageBytes = new byte[(int) archivo.length()];
        imgInputStream.read(imageBytes);

        // Encriptamos los bytes de la imagen
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedImage = rsa.doFinal(imageBytes);

        // Guardamos los bytes encriptados como una nueva imagen cifrada
        FileOutputStream encryptedImageOutputStream = new FileOutputStream("C:/Users/Tomas/Desktop/p42/aa_encrypted.png");
        encryptedImageOutputStream.write(encryptedImage);
        encryptedImageOutputStream.close();

        // Desencriptamos los bytes de la imagen
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedImage = rsa.doFinal(encryptedImage);

        // Guardamos los bytes desencriptados como una nueva imagen
        FileOutputStream decryptedImageOutputStream = new FileOutputStream("C:/Users/Tomas/Desktop/p42/aa_decrypted.png");
        decryptedImageOutputStream.write(decryptedImage);
        decryptedImageOutputStream.close();
    }
}
