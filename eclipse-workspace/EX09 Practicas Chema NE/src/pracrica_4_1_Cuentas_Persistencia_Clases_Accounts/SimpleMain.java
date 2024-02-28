package pracrica_4_1_Cuentas_Persistencia_Clases_Accounts;



import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Rutina principal de ejemplo para mostrar cómo realizar operaciones básicas con el paquete.
 * 
 * <p>
 * <b>NOTA:</b> Utilizamos aserciones en algunos lugares para verificar los resultados, pero si este fuera un código de producción real, tendríamos un manejo de errores adecuado.
 * </p>
 */
public class SimpleMain {

    // Estamos utilizando la base de datos sqlite en memoria
    private final static String URL_BASE_DE_DATOS = "jdbc:sqlite:bbdd.ssqlite";

    private Dao<Cuenta, Integer> daoCuenta;

    public static void main(String[] args) throws Exception {
        // Convertimos nuestro método estático en una instancia de Main
        new SimpleMain().realizarMain(args);
    }

    private void realizarMain(String[] args) throws Exception {
        ConnectionSource fuenteDeConexion = null;
        try {
            // Creamos nuestra fuente de conexión para la base de datos
            fuenteDeConexion = new JdbcConnectionSource(URL_BASE_DE_DATOS);
            // Configuramos nuestra base de datos y DAOs
            configurarBaseDeDatos(fuenteDeConexion);
            // Leemos y escribimos algunos datos
            leerEscribirDatos();
            // Realizamos varias operaciones a granel
            leerEscribirLote();
            // Mostramos cómo usar el objeto SelectArg
            usarCaracteristicaSelectArg();
            // Mostramos cómo usar transacciones
            usarTransacciones(fuenteDeConexion);
            System.out.println("\n\nParece que ha funcionado\n\n");
        } finally {
            // Destruimos la fuente de datos, lo que debería cerrar las conexiones subyacentes
            if (fuenteDeConexion != null) {
                fuenteDeConexion.close();
            }
        }
    }

    /**
     * Configuramos nuestra base de datos y DAOs
     */
    private void configurarBaseDeDatos(ConnectionSource fuenteDeConexion) throws Exception {

        daoCuenta = DaoManager.createDao(fuenteDeConexion, Cuenta.class);

        // Si necesitas crear la tabla
        TableUtils.createTable(fuenteDeConexion, Cuenta.class);
    }

    /**
     * Leemos y escribimos algunos datos de ejemplo.
     */
    private void leerEscribirDatos() throws Exception {
        // Creamos una instancia de Cuenta
        String nombre = "Jim Coakley";
        Cuenta cuenta = new Cuenta(nombre);

        // Persistimos el objeto de cuenta en la base de datos
        daoCuenta.create(cuenta);
        int id = cuenta.getId();
        verificarBaseDeDatos(id, cuenta);

        // Asignamos una contraseña
        cuenta.setPassword("_secreta");
        // Actualizamos la base de datos después de cambiar el objeto
        daoCuenta.update(cuenta);
        verificarBaseDeDatos(id, cuenta);

        // Consultamos todos los elementos en la base de datos
        List<Cuenta> cuentas = daoCuenta.queryForAll();
        //assertEquals("Deberíamos haber encontrado 1 cuenta coincidente con nuestra consulta", 1, cuentas.size());
        verificarCuenta(cuenta, cuentas.get(0));

        // Recorremos los elementos en la base de datos
        int cuentaC = 0;
        for (Cuenta cuenta2 : daoCuenta) {
            verificarCuenta(cuenta, cuenta2);
            cuentaC++;
        }
        //assertEquals("Deberíamos haber encontrado 1 cuenta en el bucle for", 1, cuentaC);

        // Construimos una consulta utilizando QueryBuilder
        QueryBuilder<Cuenta, Integer> constructorDeDeclaracion = daoCuenta.queryBuilder();
        // No deberíamos encontrar nada: name LIKE 'hello" no coincide con nuestra cuenta
        constructorDeDeclaracion.where().like(Cuenta.NAME_FIELD_NAME, "hello");
        cuentas = daoCuenta.query(constructorDeDeclaracion.prepare());
        //assertEquals("No deberíamos haber encontrado cuentas que coincidan con nuestra consulta", 0, cuentas.size());

        // debería encontrar nuestra cuenta: name LIKE 'Jim%' debería coincidir con nuestra cuenta
        constructorDeDeclaracion.where().like(Cuenta.NAME_FIELD_NAME, nombre.substring(0, 3) + "%");
        cuentas = daoCuenta.query(constructorDeDeclaracion.prepare());
        //assertEquals("Deberíamos haber encontrado 1 cuenta coincidente con nuestra consulta", 1, cuentas.size());
        verificarCuenta(cuenta, cuentas.get(0));

        // eliminamos la cuenta ya que hemos terminado con ella
        daoCuenta.delete(cuenta);
        // no deberíamos encontrarla ahora
        //assertNull("La cuenta fue eliminada, no deberíamos encontrar ninguna", daoCuenta.queryForId(id));
    }

    /**
     * Ejemplo de lectura y escritura de un número grande de objetos.
     */
    private void leerEscribirLote() throws Exception {

        Map<String, Cuenta> cuentas = new HashMap<String, Cuenta>();
        for (int i = 1; i <= 100; i++) {
            String nombre = Integer.toString(i);
            Cuenta cuenta = new Cuenta(nombre);
            // persistimos el objeto de cuenta en la base de datos, debería devolver 1
            daoCuenta.create(cuenta);
            cuentas.put(nombre, cuenta);
        }

        // consultamos todos los elementos en la base de datos
        List<Cuenta> todas = daoCuenta.queryForAll();
        //assertEquals("Deberíamos haber encontrado el mismo número de cuentas en el mapa", cuentas.size(), todas.size());
        for (Cuenta cuenta : todas) {
            //assertTrue("Deberíamos haber encontrado la cuenta en el mapa", cuentas.containsValue(cuenta));
            verificarCuenta(cuentas.get(cuenta.getName()), cuenta);
        }

        // recorremos los elementos en la base de datos
        int cuentaC = 0;
        for (Cuenta cuenta : daoCuenta) {
            //assertTrue("Deberíamos haber encontrado la cuenta en el mapa", cuentas.containsValue(cuenta));
            verificarCuenta(cuentas.get(cuenta.getName()), cuenta);
            cuentaC++;
        }
        //assertEquals("Deberíamos haber encontrado el número correcto de cuentas en el bucle for", cuentas.size(), cuentaC);
    }

    /**
     * Ejemplo de creación de una consulta con un argumento ? utilizando el objeto {@link SelectArg}. Luego puedes establecer el valor de este objeto en un momento posterior.
     */
    private void usarCaracteristicaSelectArg() throws Exception {

        String nombre1 = "foo";
        String nombre2 = "bar";
        String nombre3 = "baz";
        //assertEquals(1, daoCuenta.create(new Cuenta(nombre1)));
        //assertEquals(1, daoCuenta.create(new Cuenta(nombre2)));
        //assertEquals(1, daoCuenta.create(new Cuenta(nombre3)));

        QueryBuilder<Cuenta, Integer> constructorDeDeclaracion = daoCuenta.queryBuilder();
        SelectArg selectArg = new SelectArg();
        // construimos una consulta con la cláusula WHERE configurada en 'name = ?'
        constructorDeDeclaracion.where().like(Cuenta.NAME_FIELD_NAME, selectArg);
        PreparedQuery<Cuenta> consultaPreparada = constructorDeDeclaracion.prepare();

        // ahora podemos establecer el argumento de selección (?) y ejecutar la consulta
        selectArg.setValue(nombre1);
        List<Cuenta> resultados = daoCuenta.query(consultaPreparada);
        //assertEquals("Deberíamos haber encontrado 1 cuenta coincidente con nuestra consulta", 1, resultados.size());
        //assertEquals(nombre1, resultados.get(0).getName());

        selectArg.setValue(nombre2);
        resultados = daoCuenta.query(consultaPreparada);
        //assertEquals("Deberíamos haber encontrado 1 cuenta coincidente con nuestra consulta", 1, resultados.size());
        //assertEquals(nombre2, resultados.get(0).getName());

        selectArg.setValue(nombre3);
        resultados = daoCuenta.query(consultaPreparada);
        //assertEquals("Deberíamos haber encontrado 1 cuenta coincidente con nuestra consulta", 1, resultados.size());
        //assertEquals(nombre3, resultados.get(0).getName());
    }

    /**
     * Ejemplo de creación de una consulta con un argumento ? utilizando el objeto {@link SelectArg}. Luego puedes establecer el valor de este objeto en un momento posterior.
     */
    private void usarTransacciones(ConnectionSource fuenteDeConexion) throws Exception {
        String nombre = "trans1";
        final Cuenta cuenta = new Cuenta(nombre);
        //assertEquals(1, daoCuenta.create(cuenta));

        TransactionManager transactionManager = new TransactionManager(fuenteDeConexion);
        try {
            // intentamos algo en una transacción
            transactionManager.callInTransaction(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    // hacemos la eliminación
                    //assertEquals(1, daoCuenta.delete(cuenta));
                    //assertNull(daoCuenta.queryForId(cuenta.getId()));
                    // pero luego (como ejemplo) lanzamos una excepción que revierte la eliminación
                    throw new Exception("¡Lanzamos para revertir!!");
                }
            });
            //fail("Esto debería haber lanzado una excepción");
        } catch (SQLException e) {
            // esperado
        }

        //assertNotNull(daoCuenta.queryForId(cuenta.getId()));
    }

    /**
     * Verificamos que la cuenta almacenada en la base de datos sea la misma que el objeto esperado.
     */
    private void verificarBaseDeDatos(int id, Cuenta esperada) throws SQLException, Exception {
        // aseguramos de que podemos leerlo de nuevo
        Cuenta cuenta2 = daoCuenta.queryForId(id);
        if (cuenta2 == null) {
            throw new Exception("Deberíamos haber encontrado id '" + id + "' en la base de datos");
        }
        verificarCuenta(esperada, cuenta2);
    }

    /**
     * Verificamos que la cuenta sea la misma que la esperada.
     */
    private static void verificarCuenta(Cuenta esperada, Cuenta cuenta2) {
        //assertEquals("El nombre esperado no es igual al nombre de la cuenta", esperada, cuenta2);
        //assertEquals("La contraseña esperada no es igual al nombre de la cuenta", esperada.getPassword(), cuenta2.getPassword());
    }
}
