
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de Cliente que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "Clientes")
public class Cliente {

	public static final String NOMBRE_CAMPO_ID_Portero = "porteroQueLeAbrio";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(foreign = true, columnName = NOMBRE_CAMPO_ID_Portero)
	private Portero portero;

	@DatabaseField
	private String nombre;

	@DatabaseField 
	private String sexo;

	@DatabaseField
	private int edad;
	
	@DatabaseField
	private int precio;

	Cliente() {
		// Todas las clases persistidas deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}

	public Cliente(Portero portero, String nombre, String sexo, int precio, int edad) {
		this.portero = portero;
		this.nombre = nombre;
		this.precio = precio;
		this.sexo = sexo;
		this.edad = edad;
	}

	public int getId() {
		return id;
	}

	public Portero getPortero() {
		return portero;
	}

	public void setPortero(Portero portero) {
		this.portero = portero;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getsexo() {
		return sexo;
	}

	public void setsexo(String sexo) {
		this.sexo = sexo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
