package p4_2_practicaContratosPedidosCuentasORMLITE;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de cuenta que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "cuentas")
public class Cuenta {

	// Para que QueryBuilder pueda encontrar los campos
	public static final String NOMBRE_CAMPO = "nombre";
	public static final String CONTRASENA_CAMPO = "contrasena";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = NOMBRE_CAMPO, canBeNull = false)
	private String nombre;

	@DatabaseField(columnName = CONTRASENA_CAMPO)
	private String contrasena;

	Cuenta() {
		// Todas las clases persistentes deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}

	public Cuenta(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta(String nombre, String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
		return nombre.equals(((Cuenta) otro).nombre);
	}
}
