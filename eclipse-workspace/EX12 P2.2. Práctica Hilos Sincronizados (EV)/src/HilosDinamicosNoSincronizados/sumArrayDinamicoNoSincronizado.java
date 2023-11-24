package HilosDinamicosNoSincronizados;

public class sumArrayDinamicoNoSincronizado {
    private int suma;

    // sumArray no está sincronizado y no es estático
    int sumArray(int nums[]) {
        suma = 0;
        for (int i = 0; i < nums.length; i++) {
            suma += nums[i];
            System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es de: " + suma);
            try {
                Thread.sleep(10); // Simula alguna operación que toma tiempo
            } catch (InterruptedException exc) {
                System.out.println("Hilo parado");
            }
        }
        return suma;
    }
}
