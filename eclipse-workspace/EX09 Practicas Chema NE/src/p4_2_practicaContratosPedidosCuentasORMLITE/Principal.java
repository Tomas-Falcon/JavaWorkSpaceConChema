package p4_2_practicaContratosPedidosCuentasORMLITE;



import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Rutina principal de muestra para mostrar cómo realizar operaciones básicas con el paquete.
 * 
 * <p>
 * <b>NOTA:</b> Utilizamos afirmaciones (asserts) en algunos lugares para verificar los resultados, pero si este fuera
 * código de producción real, tendríamos un manejo de errores adecuado.
 * </p>
 */
public class Principal {

    // Estamos utilizando la base de datos H2 en memoria
    private final static String URL_BASE_DE_DATOS = "jdbc:sqlite:cuenta.db";

    private Dao<Cuenta, Integer> daoCuenta;
    private Dao<Pedido, Integer> daoPedido;

    public static void main(String[] args) throws Exception {
        // Convertimos nuestro método estático en una instancia de Principal
        new Principal().realizarOperacionesPrincipales(args);
    }

    private void realizarOperacionesPrincipales(String[] args) throws Exception {
        JdbcConnectionSource fuenteConexion = null;
        try {
            // Creamos nuestra fuente de datos
            fuenteConexion = new JdbcConnectionSource(URL_BASE_DE_DATOS);
            // Configuramos nuestra base de datos y DAOs
            configurarBaseDeDatos(fuenteConexion);
            // Leemos y escribimos algunos datos
            leerEscribirDatos();
            System.out.println("\n\nParece que ha funcionado\n\n");
        } finally {
            // Destruimos la fuente de datos, lo que debería cerrar las conexiones subyacentes
            if (fuenteConexion != null) {
                fuenteConexion.close();
            }
        }
    }

    /**
     * Configuramos nuestra base de datos y DAOs
     */
    private void configurarBaseDeDatos(ConnectionSource fuenteConexion) throws Exception {

        daoCuenta = DaoManager.createDao(fuenteConexion, Cuenta.class);
        daoPedido = DaoManager.createDao(fuenteConexion, Pedido.class);

        // Si necesitas crear la tabla
        TableUtils.createTable(fuenteConexion, Cuenta.class);
        TableUtils.createTable(fuenteConexion, Pedido.class);
    }

    private void leerEscribirDatos() throws Exception {
        // Creamos la primera instancia de Cuenta
        String nombre1 = "Buzz Lightyear";
        Cuenta cuenta1 = new Cuenta(nombre1);
        daoCuenta.create(cuenta1);

        // Creamos un Pedido asociado a la primera Cuenta
        int cantidad1 = 2;
        int numeroArticulo1 = 21312;
        float precio1 = 12.32F;
        Pedido pedidoA1 = new Pedido(cuenta1, numeroArticulo1, precio1, cantidad1);
        daoPedido.create(pedidoA1);
        
        
        Pedido pedidoA2 = new Pedido(cuenta1, 895674, (float) 137.79, 1);
        daoPedido.create(pedidoA2);

        // Creamos otra instancia de Cuenta
        String nombre2 = "Woody";
        Cuenta cuenta2 = new Cuenta(nombre2);
        daoCuenta.create(cuenta2);

        // Creamos un Pedido asociado a la segunda Cuenta
        int cantidad2 = 1;
        int numeroArticulo2 = 785;
        float precio2 = 7.98F;
        
        Pedido pedidoB1 = new Pedido(cuenta2, numeroArticulo2, precio2, cantidad2);
        daoPedido.create(pedidoB1);
        
        
        Pedido pedidoB2 = new Pedido(cuenta2, 1241, (float) 99.99, 3);
        daoPedido.create(pedidoB2);

        // Construimos una consulta utilizando QueryBuilder
        QueryBuilder<Pedido, Integer> constructorConsulta = daoPedido.queryBuilder();
        // Debería encontrar todos los pedidos
        List<Pedido> pedidos = daoPedido.query(constructorConsulta.prepare());

      
    }
}
