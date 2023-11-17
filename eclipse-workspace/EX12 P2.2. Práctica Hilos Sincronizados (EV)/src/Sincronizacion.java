
public class Sincronizacion {
	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5 };
		MiHilo mh1 = MiHilo.creaEInicia("#1", a);
		MiHilo mh2 = MiHilo.creaEInicia("#2", a);

		try {
			mh1.hilo.join();
			mh2.hilo.join();
		} catch (InterruptedException exc) {
			System.out.println("Hilo principal interrumpido.");
		}
	}
}

/*
----Horiginal:

	#2 iniciando.
	#1 iniciando.
	Total acumulado de #2 es 1
	Total acumulado de #2 es 3
	Total acumulado de #2 es 6
	Total acumulado de #2 es 10
	Total acumulado de #2 es 15
	Total acumulado de #1 es 1
	Suma para #2 es 15	
	#2 terminado.
	Total acumulado de #1 es 3
	Total acumulado de #1 es 6
	Total acumulado de #1 es 10
	Total acumulado de #1 es 15
	Suma para #1 es 15
	#1 terminado.

 
 
 ---Modificado:
	#2 iniciando.
	#1 iniciando.
	Total acumulado de #2 es 1
	Total acumulado de #2 es 3
	Total acumulado de #2 es 6
	Total acumulado de #2 es 10
	Total acumulado de #2 es 15
	Total acumulado de #1 es 1
	Suma para #2 es 15
	#2 terminado.
	Total acumulado de #1 es 3
	Total acumulado de #1 es 6
	Total acumulado de #1 es 10
	Total acumulado de #1 es 15
	Suma para #1 es 15
	#1 terminado.
 */