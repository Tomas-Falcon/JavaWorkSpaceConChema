public class Sumador {

	public static int sumar(int n1, int n2) {
		int suma = n1 + n2;
		return suma;
	}

	public static void main(String[] args) {
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		int suma = sumar(n1, n2);
		System.out.println(suma);
		System.out.flush();
	}
}