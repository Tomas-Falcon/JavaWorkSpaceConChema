package P2_2_Practica_CRUD_en_MySQL;

import java.sql.*;

public class Conection {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String usuario = "root";
	private String contraseña = "mysql";
	
	public Conection(String tabla) {
		url = url + tabla;
	}
	
	public void escribir(String nombre, String apellido, int edad, String gustoMusical, String tabla){

			/* try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}*/
			try (Connection conexion = DriverManager.getConnection(url,usuario,contraseña); Statement stmt = conexion.createStatement();){
				
				if(conexion != null)
					System.out.println("Conectin!");
				
				String sql = "insert into "+tabla+"(Nombre, Apellido, Edad, gustoMusical) values (?, ?, ?, ?)";
			    PreparedStatement pstmt;
			    try {
			        pstmt = conexion.prepareStatement(sql);
			        pstmt.setString(1, nombre);
			        pstmt.setString(2, apellido);
			        pstmt.setInt(3, edad);
			        pstmt.setString(4, gustoMusical);
			        pstmt.execute();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
				
				
				System.out.println(conexion);
				//String insertar = "insert into tablin values (\"Dani\",\"??\",19)";
				//String modificar = "UPDATE tablin SET apellido = \"mn2\" WHERE apellido = 'mn'";
				//String borrar = "delete from tablin where Apellido = \"mn\" ";
				//stmt.execute(insertar);
				//stmt.execute(modificar);
				//stmt.execute(borrar);
	  
				
				System.out.println(conexion.isClosed());
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}

	

}
