package HilosEstaticosNoSincronizados;

public class EstaticoNoSincronizado {
	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5 };
		MiHiloEstaticoNoSincronizado mh1 = MiHiloEstaticoNoSincronizado.creaEInicia("#1", a);
		MiHiloEstaticoNoSincronizado mh2 = MiHiloEstaticoNoSincronizado.creaEInicia("#2", a);

		try {
			
			 
			mh1.hilo.join();
			mh2.hilo.join();
		} catch (InterruptedException exc) {
			System.out.println("Hilo principal interrumpido.");
		}
	}
}