package hipodromoLaZarzuela;

public class ClaseCaballo  implements Runnable{
	private int numero, posicion = 0;
	private String nombre;
	private ClaseHipódromo carrera = null;
	
	public ClaseCaballo(String nombre, int numero, int posicion) {
	this.nombre = nombre;
	this.posicion = posicion;
	this.numero = numero;
	}

	/**
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(ClaseHipódromo carrera) {
        this.carrera = carrera;
    }
	
	 public void run() {
	        
	        int numAleatorio;
	        try {
	            while (posicion < 100) {
	                numAleatorio = generaNumeroAleatorio(1, 3);
	                System.out.println("Caballo " + nombre + " ha aumentado en " + numAleatorio);

	                if (posicion + numAleatorio > 100) {
	                    numAleatorio = 100 - posicion;
	                }

	                posicion += numAleatorio;
	                
	               if (carrera != null) {
	                    carrera.notificarAvance(this, posicion);
	                }

	                Thread.sleep(1000);
	            }
	        } catch (InterruptedException ex) {
	            System.out.print("");
	        }
	    }

	    private int generaNumeroAleatorio(int nMinimo, int nMaximo) {
	        return (int) (Math.random() * (nMaximo - nMinimo + 1) + (nMinimo));
	    }
	
}


// CABALLO, cuyos atributos son nombre, número y posición actual en metros sobre la pista