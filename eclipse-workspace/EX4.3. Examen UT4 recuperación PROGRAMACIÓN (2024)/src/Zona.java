
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de Zona que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "Zonas")
public class Zona {

	public static final String NOMBRE_CAMPO_ID_Flor = "Flor";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(foreign = true, columnName = NOMBRE_CAMPO_ID_Flor)
	private Flor flor;
	
	@DatabaseField(columnName = "nombre Flor")
	private String nombreFlor;
	
	@DatabaseField(columnName = "cantidad")
	private int cantidad;

	@DatabaseField
	private String nombre;



	Zona() {
		// Todas las clases persistidas deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}

	public Zona(String nombre, Flor flor) {
		this.flor = flor;
		this.nombre = nombre;
		nombreFlor = flor.getNombre();
		cantidad = flor.getCantidad();
		
	}

	public int getId() {
		return id;
	}

	public Flor getFlor() {
		return flor;
	}

	public void setFlor(Flor flor) {
		this.flor = flor;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	
}
