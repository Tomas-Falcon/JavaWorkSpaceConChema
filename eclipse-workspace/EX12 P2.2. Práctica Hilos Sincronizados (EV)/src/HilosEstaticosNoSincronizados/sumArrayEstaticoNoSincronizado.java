package HilosEstaticosNoSincronizados;

public class sumArrayEstaticoNoSincronizado {
    private static int sum;

    // El método sumArray no está sincronizado y es estático
    static int sumArray(int nums[]) {
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println("Total acumulado de " + Thread.currentThread().getName() + " es " + sum);
            try {
                // Pausa el hilo por 10 milisegundos (simula algún tipo de operación)
                Thread.sleep(10);
            } catch (InterruptedException exc) {
                System.out.println("Hilo interrumpido");
            }
        }
        return sum;
    }
}