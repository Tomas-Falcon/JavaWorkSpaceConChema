public class Sumador {

	public static int sumar(int n1, int n2) {
		int suma = n1 + n2; //se hace la suma
		return suma; //se retorna la suma
	}

	public static void main(String[] args) {
		int n1 = Integer.parseInt(args[0]);//se obtienen los valores pasados como parametro (que los pasa la clase Lanzador)
		int n2 = Integer.parseInt(args[1]);
		int resultado = sumar(n1, n2); // se llama al metodo sumar, que lo modifique para que no altene los parametros pasados
		System.out.println(resultado); //y se imprime x pantalla el resultado de la suma, que la clase Lanzador toma como valor a escribir en los ficheros
		System.out.flush(); // el flush() asegura que se escriban/envien de inmediato los datos, tambien libera los procesos
	}
}