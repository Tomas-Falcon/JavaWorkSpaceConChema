package p4_2_practicaContratosPedidosCuentasORMLITE;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de objeto de pedido que se persiste en disco mediante DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "pedidos")
public class Pedido {

	public static final String NOMBRE_CAMPO_ID_CUENTA = "id_cuenta";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(foreign = true, columnName = NOMBRE_CAMPO_ID_CUENTA)
	private Cuenta cuenta;

	@DatabaseField
	private int numeroArticulo;

	@DatabaseField
	private int cantidad;

	@DatabaseField
	private float precio;

	Pedido() {
		// Todas las clases persistidas deben definir un constructor sin argumentos con al menos visibilidad de paquete
	}

	public Pedido(Cuenta cuenta, int numeroArticulo, float precio, int cantidad) {
		this.cuenta = cuenta;
		this.numeroArticulo = numeroArticulo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public int getNumeroArticulo() {
		return numeroArticulo;
	}

	public void setNumeroArticulo(int numeroArticulo) {
		this.numeroArticulo = numeroArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}
