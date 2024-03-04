package p4_3_cuentas_listas_pedidos_ev;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de un objeto de pedido que se persiste en disco mediante el DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "pedidos")
public class Pedido {

    public static final String ACCOUNT_ID_FIELD_NAME = "account_id";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ACCOUNT_ID_FIELD_NAME)
    private Cuenta cuenta;

    @DatabaseField
    private int numeroArticulo;

    @DatabaseField
    private int cantidad;

    @DatabaseField
    private float precio;

    Pedido() {
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
