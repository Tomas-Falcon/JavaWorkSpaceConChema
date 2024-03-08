import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

public class ClaseMain {
	 // URL de la base de datos
    private final static String DATABASE_URL = "jdbc:sqlite:jardin.db";

    // Objetos DAO para Zona y Flor
    private Dao<Zona, Integer> zonaDao;
    private Dao<Flor, Integer> florDao;
    
    private Zona z1, z2, z3;
    private Flor rosa, margarita, claveles, tulipanes, violetas, begonias;
    
 // Método principal
    public static void main(String[] args) {
        try {
            new ClaseMain().doMain(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Método principal de lógica
    private void doMain(String[] args) throws Exception {
        JdbcConnectionSource conexionbd = null;
        try {
            // Establecer conexión con la base de datos
            conexionbd = new JdbcConnectionSource(DATABASE_URL);
            
            
            // Crear tablas si no existen
            crearTablas(conexionbd);

            // Insertar datos iniciales en las tablas
            insertardatos();

            //se imprimiran todas las flores antes de modificarse
            System.out.println("se han ingresado todas las flores y zonas respectivas, agora se imprimiras\n");
            imprimirFlores();
            
            // Modificar precios de los tres primeros clientes
            modificarClavelesTulipanesDoble();
            
            //imprimir todas las flores existentes
            imprimirFlores();
            
            
            //imprimir solo rosas y margaritas
            imprimirRosasMargaritas();
         
        } finally {
            // Cerrar la conexión de la base de datos en un bloque finally para asegurar que se cierre
            if (conexionbd != null) {
                conexionbd.close();
            }
        }
    }

	private void imprimirRosasMargaritas() {
		QueryBuilder<Flor, Integer> queryBuilder = florDao.queryBuilder();
		ArrayList<Flor> flores = new ArrayList<Flor>();
		List<Flor> florList = null;
		try {
			queryBuilder.where().eq("nombre", "Rosa");
			florList = queryBuilder.query();
			for (Flor flor : florList) {
				flores.add(flor);
			}

			queryBuilder.where().eq("nombre", "Margarita");
			florList = queryBuilder.query();
			for (Flor flor : florList) {
				flores.add(flor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
        
        
        
        
        System.out.println("Flores\n");
        
        for (Flor flor : flores) {System.out.println(flor.toString());}System.out.println();
    }
    
    private void imprimirFlores() {
    	try {
    		System.out.println("Se imprimiran todas las flores existentes");
			List<Flor> florLis = florDao.queryForAll();
			
			for (Flor flor : florLis) {
				
				System.out.println(flor.toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    // Insertar datos iniciales en las tablas
    private void insertardatos() throws Exception {
    	
    	 // Insertar datos de flor
        florDao.create(rosa = new Flor("Rosa", "Roja", 1.5f, 10));
        florDao.create(margarita = new Flor("Margarita", "Amarilla", 1f, 10));
        
        florDao.create(claveles = new Flor("Claveles", "Marron", 2f, 15));
        florDao.create(tulipanes = new Flor("Tulipanes", "Violeta", 2.5f, 8));
        
        florDao.create(violetas = new Flor("Violetas", "Violeta", 1.5f, 20));
        florDao.create(begonias = new Flor("Begonias", "Blancas", 1.2f, 10));
        
        zonaDao.create(z1 = new Zona("Zona 1", rosa));
        zonaDao.create(z1 = new Zona("Zona 1", margarita));
        
        zonaDao.create(z2 = new Zona("Zona 2", claveles));
        zonaDao.create(z2 = new Zona("Zona 2", tulipanes));
        
        zonaDao.create(z3 = new Zona("Zona 3", violetas));
        zonaDao.create(z3 = new Zona("Zona 3", begonias));
       
       
       
    }
    
    
    //Obtener las flores a modificar y modificarlas
    private void modificarClavelesTulipanesDoble() throws Exception {
    	int cantidad = claveles.getCantidad();
    	cantidad = cantidad * 2;
    	
    	claveles.setCantidad(cantidad);
        florDao.update(claveles);
        cantidad = tulipanes.getCantidad();
        cantidad = cantidad * 2;

        tulipanes.setCantidad(cantidad);
        florDao.update(tulipanes);
       zonaDao.update(z2);
        System.out.println("-----------------------\nSe modifican las cantidades de lso claveles y de los tulipanes * 2\nquedando como");
        System.out.println(claveles.toString());
        System.out.println(tulipanes.toString());
    }
    
 // Crear tablas si no existen
    private void crearTablas(JdbcConnectionSource conexionbd) throws SQLException {
        zonaDao = DaoManager.createDao(conexionbd, Zona.class);
        florDao = DaoManager.createDao(conexionbd, Flor.class);

        TableUtils.createTable(conexionbd, Zona.class);
        TableUtils.createTable(conexionbd, Flor.class);
    }
    
    
}
