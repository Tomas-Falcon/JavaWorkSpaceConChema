//Uso de Sincronizacion para controlar el acceso.
public class sumArray {
	private int sum;

	// sumArray está sincronizado
	synchronized int sumArray(int nums[]) {
		sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);
			try {
				Thread.sleep(1000);// permitir el cambio de tarea
			} catch (InterruptedException exc) {
				System.out.println("Hilo interrumpido"+exc);
			}
		}
		return sum;
	}
}
