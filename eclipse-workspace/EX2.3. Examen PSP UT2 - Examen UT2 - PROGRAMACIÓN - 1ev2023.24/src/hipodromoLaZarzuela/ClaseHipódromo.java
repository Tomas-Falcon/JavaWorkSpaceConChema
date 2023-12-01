package hipodromoLaZarzuela;


import java.util.*;


public class ClaseHipódromo extends Thread {

	private Thread[] hilos;
    private Vector <String> posiciones;
    ArrayList<ClaseCaballo> caballos = new ArrayList();
    
	 public static void main(String[] args) {
		ClaseHipódromo carrera = new ClaseHipódromo();
        carrera.iniciarCarrera();
    }
	

    public ClaseHipódromo() {
        hilos = new Thread[10];
        posiciones = new Vector<>();
    }

    public void iniciarCarrera() {
        for (int i = 0; i < hilos.length; i++) {
        	ClaseCaballo c = new ClaseCaballo((i+""),i,0);
            c.setCarrera(this);
            hilos[i] = new Thread(c);
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList <ClaseCaballo> a = ordenar(caballos);
        mostrarPosiciones(a);
    }

    public synchronized void notificarAvance(ClaseCaballo caballo, int posicion) throws InterruptedException {
        System.out.println("Caballo " + caballo.getNombre() + " quedando en: " + posicion);
        caballos.add(caballo);
        
        if (posicion >= 100) {
        	for(Thread hilo : hilos) {
            	hilo.interrupt();
            }
        	if (!posiciones.contains("Caballo " + caballo.getNombre())) {
                posiciones.add("Caballo " + caballo.getNombre());
                
                System.out.println("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                System.out.println("#-----------Caballo " + caballo.getNombre() + " ha completado la carrera---------#");

                
                
                System.out.println("");
                Thread.sleep(5000);
                System.out.println("");
                
            }

          
        }
    }


   
    
    public static ArrayList<ClaseCaballo> ordenar(ArrayList<ClaseCaballo> lista) {
        // Ordenar la lista de menor a mayor posición
        Collections.sort(lista, Comparator.comparingInt(ClaseCaballo::getPosicion));

        // Obtener las últimas 10 posiciones
        int tamañoOriginal = lista.size();
        int inicio = Math.max(0, tamañoOriginal - 10); // Evitar índices negativos
        int fin = tamañoOriginal;
        
        ArrayList<ClaseCaballo> lista2 = new ArrayList<>();
        HashSet<Integer> numerosCaballos = new HashSet<>(); // Para evitar duplicados

        while (inicio < fin) {
            ClaseCaballo caballo = lista.get(inicio);

            // Agregar a la lista2 solo si el número de caballo no está en la lista de númerosCaballos
            if (numerosCaballos.add(caballo.getNumero())) {
                lista2.add(caballo);
            }

            inicio++;
        }
        
        // Devolver la lista2 con las últimas 10 posiciones sin números de caballo repetidos
        return lista2;
    }
   

    private void mostrarPosiciones(ArrayList <ClaseCaballo> caballin) {
        System.out.println("Posiciones finales:");
        int i = 1;
        for (ClaseCaballo caballo : caballin) {
            System.out.println(i + ". " + caballo.getNombre());
            i++;
        }
    }
}