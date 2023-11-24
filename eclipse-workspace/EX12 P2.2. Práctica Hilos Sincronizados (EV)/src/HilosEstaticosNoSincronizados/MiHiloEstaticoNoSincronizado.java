package HilosEstaticosNoSincronizados;

public class MiHiloEstaticoNoSincronizado implements Runnable {
	Thread hilo;
	static sumArrayEstaticoNoSincronizado sumarray = new sumArrayEstaticoNoSincronizado();
	int a[];
	int resp;

	 
	MiHiloEstaticoNoSincronizado(String nombre, int nums[]) {
		hilo = new Thread(this, nombre);
		a = nums;
	}

	 
	public static MiHiloEstaticoNoSincronizado creaEInicia(String nombre, int nums[]) {
		MiHiloEstaticoNoSincronizado miHilo = new MiHiloEstaticoNoSincronizado(nombre, nums);
		 
		miHilo.hilo.start();
		return miHilo;
	}
 
	public void run() {
		int sum;
		System.out.println(hilo.getName() + " iniciando.");

		resp = sumArrayEstaticoNoSincronizado.sumArray(a);
		System.out.println("Suma para " + hilo.getName() + " es " + resp);
		System.out.println(hilo.getName() + " terminado.");
	}
}