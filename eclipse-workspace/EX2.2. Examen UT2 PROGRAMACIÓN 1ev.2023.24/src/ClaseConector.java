import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClaseConector {
	
	
	
	//metodo crea bbdd, primero se crea la fabrica xq los empleados tienen las fk de las fabricas 
	protected void crearBBDD() {
		 try {
			Class.forName("org.sqlite.JDBC");
			
			  // ESTABLECER LA CONEXIÓN
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");
            Statement enunciado = conexion.createStatement();

         enunciado.execute("DROP TABLE IF EXISTS B;");
            enunciado.execute("CREATE TABLE B (id_Fabrica INTEGER PRIMARY KEY, Localidad VARCAHR(25), Telefono VARCAHR(25));");
            // CREAR UNA TABLA NUEVA, LA BORRA SI EXISTE
           enunciado.execute("DROP TABLE IF EXISTS A;");
            enunciado.execute("CREATE TABLE A (id_Empleado INTEGER PRIMARY KEY, Nombre VARCAHR(25), Apellidos VARCAHR(25), Codigo_empleado VARCAHR (25), id_Fabrica FOREING KEY REFERENCE B );");
           
           
            	
            	
            	
                 
                 
            
         
            
            conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     
	} 
	
	//metodo borra por nombre al trabajador llamado pepe, al ejecutar el programa se crea la bbdd y sus tablas y se le mete a pepe, que posteriormente sera borrado
	protected void borrar() {
		try {
			Class.forName("org.sqlite.JDBC");	
			// ESTABLECER LA CONEXIÓN
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");
			  Statement enunciado = conexion.createStatement();
			  enunciado.execute("DELETE FROM A WHERE nombre = \"Pepe\"");
			  System.out.println("Se ha borrado a Pepe");
			  ResultSet resultados = enunciado.executeQuery("SELECT * FROM A ORDER BY id_Empleado;");
			  while (resultados.next()) {
	
	                System.out.println("id " + resultados.getInt(1) + ", Nombre " + resultados.getString(2)+ ", Apellido " + resultados.getString(3)+ ", Codigo de empleado " + resultados.getString(4)+ ", Pertenece a la favbrica con el id " + resultados.getInt(5));
	            }
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	//la funcuion es casi la misma a la de borrar, solo que no se borra, solo ser muestran los datos de lso empleados
	protected void mostrarDatosDeEmpleado() {
		try {
			Class.forName("org.sqlite.JDBC");	
			// ESTABLECER LA CONEXIÓN
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");
			  Statement enunciado = conexion.createStatement();
			 // enunciado.execute("DELETE FROM A WHERE nombre = \"Pepe\"");
			  System.out.println("Se ha borrado a Pepe");
			  ResultSet resultados = enunciado.executeQuery("SELECT * FROM A ORDER BY id_Empleado;");
			  while (resultados.next()) {
	
	                System.out.println("id " + resultados.getInt(1) + ", Nombre " + resultados.getString(2)+ ", Apellido " + resultados.getString(3)+ ", Codigo de empleado " + resultados.getString(4)+ ", Pertenece a la favbrica con el id " + resultados.getInt(5));
	            }
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	//se inserta a los empleados
	protected void insertarEmpleado(Empleado...empleado) {
		try {
			Class.forName("org.sqlite.JDBC");	
			// ESTABLECER LA CONEXIÓN
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");
			
			
			  int i = 0;
	          while(i < empleado.length) {
	          	
	          int idInt = empleado[i].getID();
	  		int idfABRICA = empleado[i].getIdFabrica();
	  		String nombre = empleado[i].getNombre();
	  		String apellido = empleado[i].getApellido();
	  		String codigoEmpleado = empleado[i].getCodigoEmpleado();
	  		
	  		
	  		 PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO A (id_Empleado, Nombre, Apellidos, Codigo_empleado, id_Fabrica) VALUES (?, ?, ?, ?, ?)");

	              // Insertar datos
	              preparedStatement.setInt(1, idInt);
	              preparedStatement.setString(2, nombre);
	              preparedStatement.setString(3, apellido);
	              preparedStatement.setString(4, codigoEmpleado);
	              preparedStatement.setInt(5, idfABRICA);
	              preparedStatement.executeUpdate();
	             
	              i++;
	          }
			
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//se insertan a las fabricas
	protected void insertarFabricas(Fabrica...f) {
		
		try {
			Class.forName("org.sqlite.JDBC");	
			// ESTABLECER LA CONEXIÓN
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");
			
			
			  int i = 0;
	          while(i < f.length) {
	          	

	     		 PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO B (id_Fabrica, Localidad, Telefono) VALUES (?, ?, ?)");
	         	 
	         	 int idFabrica = f[i].getId();
	          	String localidadFabrica = f[i].getLocalidad();
	          	String telefonoFabrica = f[i].getTelefono();
	          	
	              // Insertar datos
	              preparedStatement.setInt(1, idFabrica);
	              preparedStatement.setString(2, localidadFabrica);
	              preparedStatement.setString(3, telefonoFabrica);
	              preparedStatement.executeUpdate();
	              i++;
	          }
			
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
}
