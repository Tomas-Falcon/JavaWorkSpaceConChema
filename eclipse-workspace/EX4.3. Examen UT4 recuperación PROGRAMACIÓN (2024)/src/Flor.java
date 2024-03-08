
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de Flores que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "Flor")
public class Flor {

	// Para que QueryBuilder pueda encontrar los campos
	public static final String NOMBRE_CAMPO = "nombre";
	public static final String COLOR_CAMPO = "color";
	public static final String PRECIO_CAMPO = "precio";
	public static final String CANTIDAD_CAMPO = "cantidad";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = NOMBRE_CAMPO, canBeNull = false)
	private String nombre;

	@DatabaseField(columnName = COLOR_CAMPO)
	private String color;
	
	@DatabaseField(columnName = PRECIO_CAMPO)
	private float precio;
	
	@DatabaseField(columnName = CANTIDAD_CAMPO)
	private int cantidad;


	Flor() {
		// Todas las clases persistentes deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}


	public Flor(String nombre, String color, float precio, int cantidad) {
		this.cantidad = cantidad;
		this.precio = precio;
		this.color = color;
		this.nombre = nombre;
		
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
		return nombre.equals(((Flor) otro).nombre);
	}


	@Override
	public String toString() {
		return "Flor [id=" + id + ", nombre=" + nombre + ", color=" + color + ", precio=" + precio + ", cantidad="
				+ cantidad + "]";
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}


	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}


	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}


	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}




}