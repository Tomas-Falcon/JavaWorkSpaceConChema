package p4_3_cuentas_listas_pedidos_ev;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Ejemplo de un objeto de cuenta que se persiste en disco mediante el DAO y otras clases de ejemplo.
 */
@DatabaseTable(tableName = "cuentas")
public class Cuenta {

    // Para que QueryBuilder pueda encontrar los campos
    public static final String NOMBRE_CAMPO = "nombre";
    public static final String CONTRASEÑA_CAMPO = "contraseña";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = NOMBRE_CAMPO, canBeNull = false)
    private String nombre;

    @DatabaseField(columnName = CONTRASEÑA_CAMPO)
    private String contraseña;

    @ForeignCollectionField
    private ForeignCollection<Pedido> pedidos;

    Cuenta() {
        // Todas las clases persistentes deben definir un constructor sin argumentos con al menos visibilidad de paquete
    }

    public Cuenta(String nombre) {
        this.nombre = nombre;
    }

    public Cuenta(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ForeignCollection<Pedido> getPedidos() {
        return pedidos;
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
