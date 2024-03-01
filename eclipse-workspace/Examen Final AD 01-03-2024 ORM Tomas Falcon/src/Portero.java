
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de Portero que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "Porteros")
public class Portero {

	// Para que QueryBuilder pueda encontrar los campos
	public static final String NOMBRE_CAMPO = "nombre";
	public static final String ALTURA_CAMPO = "altura";
	public static final String PESO_CAMPO = "peso";
	public static final String SIMPATIA_CAMPO = "simpatia";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = NOMBRE_CAMPO, canBeNull = false)
	private String nombre;

	@DatabaseField(columnName = ALTURA_CAMPO)
	private int altura;
	
	@DatabaseField(columnName = PESO_CAMPO)
	private int peso;
	
	@DatabaseField(columnName = SIMPATIA_CAMPO)
	private int simpatia;


	Portero() {
		// Todas las clases persistentes deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}


	public Portero(String nombre, int altura, int peso, int simpatia) {
		this.nombre = nombre;
		this.altura = altura;
		this.peso = peso;
		this.simpatia  =simpatia;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	
	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}



	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}



	/**
	 * @return the simpatia
	 */
	public int getSimpatia() {
		return simpatia;
	}



	/**
	 * @param simpatia the simpatia to set
	 */
	public void setSimpatia(int simpatia) {
		this.simpatia = simpatia;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}

	@Override
	public boolean equals(Object otro) {
		if (otro == null || otro.getClass() != getClass()) {
			return false;
		}
		return nombre.equals(((Portero) otro).nombre);
	}




}