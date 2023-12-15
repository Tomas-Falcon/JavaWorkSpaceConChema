package sokets_Server_Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
			try {
				Socket s = new Socket("localhost", 0);
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				System.out.println(br);
				
			}catch (UnknownHostException une) {
				System.out.println("NO se encuentra el servidor");
			}catch (IOException une) {
				System.out.println("Error en Ia comunicación") ;
		}
	}
}
	
