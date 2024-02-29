package algoritmo_cifrador_asimetrico_rsa;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CifradoPorTresAlgoritmosTexto {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

		
		
		
		//Creamos el cifrador
		Cipher rsa;
		
		//Generamos el par de claves RSA
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		//Guardamos el par de claves en variables
	    PublicKey publicKey = keyPair.getPublic();
	    PrivateKey privateKey = keyPair.getPrivate();
	    
	    System.out.println(publicKey+"\n");
	    System.out.println(privateKey+"\n");
	    
	    //Establecemos el tipo de cifrado
	    rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    
	    //Cadena a cifrar
	    String text = "Mi texto que va a ser sifrado";
	    
	    //Encriptamos
	    rsa.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] encriptado = rsa.doFinal(text.getBytes());
	    
	    //Mostramos el mensaje encriptado
	    for (byte b : encriptado) {
	    	System.out.print(Integer.toHexString(0xFF & b));
	    }
	    System.out.println(encriptado.toString());
	    
	    //Desencriptamos
	    rsa.init(Cipher.DECRYPT_MODE, privateKey);
	    byte[] bytesDesencriptados = rsa.doFinal(encriptado);
	    
	    //Mostramos el texto desencriptado
	    String textoDesencripado = new String(bytesDesencriptados);
	    System.out.println(textoDesencripado);

	}

}