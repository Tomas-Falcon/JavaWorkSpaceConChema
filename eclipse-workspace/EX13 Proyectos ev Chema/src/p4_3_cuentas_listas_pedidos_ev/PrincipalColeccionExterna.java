package p4_3_cuentas_listas_pedidos_ev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Rutina principal de ejemplo para mostrar cómo realizar operaciones básicas con el paquete.
 *
 * <p>
 * <b>NOTA:</b> Utilizamos afirmaciones (asserts) en algunos lugares para verificar los resultados, pero si este fuera
 * código de producción real, tendríamos un manejo de errores adecuado.
 * </p>
 */
public class PrincipalColeccionExterna {

    private final static String URL_BASE_DE_DATOS = "jdbc:sqlite:db.db";
   

    private Dao<Cuenta, Integer> daoCuenta;
    private Dao<Pedido, Integer> daoPedido;

    public static void main(String[] args) throws Exception {
        new PrincipalColeccionExterna().realizarPrincipal(args);
    }

    private void realizarPrincipal(String[] args) throws Exception {
        JdbcConnectionSource origenConexion = null;
        try {
            origenConexion = new JdbcConnectionSource(URL_BASE_DE_DATOS);
            configurarBaseDeDatos(origenConexion);
            leerEscribirDatos();
            System.out.println("\n\nParece que ha funcionado\n\n");
        } finally {
            if (origenConexion != null) {
                origenConexion.close();
            }
        }
    }

    private void configurarBaseDeDatos(ConnectionSource origenConexion) throws Exception {
        daoCuenta = DaoManager.createDao(origenConexion, Cuenta.class);
        daoPedido = DaoManager.createDao(origenConexion, Pedido.class);

        TableUtils.createTableIfNotExists(origenConexion, Cuenta.class);
        TableUtils.createTableIfNotExists(origenConexion, Pedido.class);
    }

    private void leerEscribirDatos() throws Exception {
        String nombre = "Buzz Lightyear";
        Cuenta cuenta = new Cuenta(nombre);
        daoCuenta.create(cuenta);

        int cantidad1 = 2;
        int numeroArticulo1 = 21312;
        float precio1 = 12.32F;
        Pedido pedido1 = new Pedido(cuenta, numeroArticulo1, precio1, cantidad1);
        daoPedido.create(pedido1);

        int cantidad2 = 1;
        int numeroArticulo2 = 785;
        float precio2 = 7.98F;
        Pedido pedido2 = new Pedido(cuenta, numeroArticulo2, precio2, cantidad2);
        daoPedido.create(pedido2);

        Cuenta cuentaResultado = daoCuenta.queryForId(cuenta.getId());
        ForeignCollection<Pedido> pedidos = cuentaResultado.getPedidos();

        CloseableIterator<Pedido> iterador = pedidos.closeableIterator();
        try {
            assertTrue(iterador.hasNext());
            Pedido pedido = iterador.next();
            assertEquals(numeroArticulo1, pedido.getNumeroArticulo());
            assertSame(cuentaResultado, pedido.getCuenta());
            assertTrue(iterador.hasNext());
            pedido = iterador.next();
            assertEquals(numeroArticulo2, pedido.getNumeroArticulo());
            assertFalse(iterador.hasNext());
        } finally {
            iterador.close();
        }

        int cantidad3 = 50;
        int numeroArticulo3 = 78315;
        float precio3 = 72.98F;
        Pedido pedido3 = new Pedido(cuenta, numeroArticulo3, precio3, cantidad3);

        pedidos.add(pedido3);
        assertEquals(3, pedidos.size());

        List<Pedido> listaPedidos = daoPedido.queryForAll();
        assertEquals(3, listaPedidos.size());
    }
}
