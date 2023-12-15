package sokets_Server_Connection;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
	

		try {
			ServerSocket server = new ServerSocket(0) ;
			Socket recepcion = server.accept() ;
			}catch(){
				
			}
		
	}
	

}