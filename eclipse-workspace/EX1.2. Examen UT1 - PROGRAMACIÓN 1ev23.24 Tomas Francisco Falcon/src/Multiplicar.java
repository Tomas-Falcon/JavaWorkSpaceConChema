
public class Multiplicar {
	private static int n1, n2;
	
	public static void main(String[] args)  {
		int numA = Integer.parseInt(args[0]);
		int numB = Integer.parseInt(args[1]); //se obtienen los valores pasados como parametro
		n1 = numA;
		n2 = numB;// se asignan a variables estaticas de control
		
			multiplicar(numA, numB);
		//se pasan variables con los valores obtenidos
	}
	private static void multiplicar(int numA, int numB) {
		int resultado = numA * numB; // se multiplican los valores y se asigna en uan variable 
		
		imprimir(resultado); // se pasa la multiplicacion y se imprime
	
	}
	private static void imprimir(int resultado) {
		try {// se intenda hacer esperar el hilo
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //esperamos 0,3 segundos
		System.out.println("La multiplicacion de "+ n1 + " * " + n2 + " da como resultado: "+ resultado); // imprimimos el resultado

	}
}
