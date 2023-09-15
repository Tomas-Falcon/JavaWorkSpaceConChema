import java.util.Scanner;

public class ClaseMain {

	public static void main(String[] args) {
		 		 int resultado = 0;
		 		 int i = 0;
		 		 while(i < args.length) {
		 			 resultado = resultado + Integer.parseInt(args [i]);
		 			 i++;
		 		 }
		  
		  System.out.println("El resultado de las sumas es: " + resultado);


	}

}
