package SqlitePack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProbandoSqlite {

 public static void main(String[] args) throws ClassNotFoundException {

  Class.forName("org.sqlite.JDBC");

  try {

   // ESTABLECER LA CONEXIÓN
   Connection conexion;
   //yo modifique esta ruta pera la coneccion, se le dice 
   //que va a ser un jdbc de tipo sqlite y la direccion "jdbc:sqlite:C:\\Users\\Tomas\\Desktop\\Bases de datos\\bdpruebas.db"
   //esta es la ruta original "jdbc:sqlite:bdpruebas.db" 
   conexion = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Tomas\\Desktop\\Bases de datos\\bdpruebas.db");

   // CREAR ENUNCIADO
   Statement enunciado;
   enunciado = conexion.createStatement();

   // CREAR UNA TABLA NUEVA, LA BORRA SI EXISTE
   enunciado.execute("DROP TABLE IF EXISTS tablapruebas;");
   enunciado.execute("CREATE TABLE tablapruebas (id int primary key, aleatorio int);");

   // INSERTAR DATOS
   int i;
   for (i = 1; i <= 100; i++) {
    enunciado.execute("INSERT INTO tablapruebas (id, aleatorio) values ("
        + i
        + ", "
        + Math.floor(Math.random() * 100 + 1) + ");");
   }

   // CONSULTA DATOS
   ResultSet resultados;
   resultados = enunciado.executeQuery("SELECT * FROM tablapruebas;");
   

   // PROCESAR EL RESULTADO
   while (resultados.next()) {
	    System.out.println("id " + resultados.getString(1)
	      + ": aleatorio " + resultados.getString(2));
	   }
   
   resultados = enunciado.executeQuery("SELECT * FROM tablapruebas LIMIT 10;");
   //selecciona los primeros 10 resultados
   while (resultados.next()) {
	   int id = Integer.parseInt(resultados.getString(1));
	   int numero = Integer.parseInt(resultados.getString(2));
	   
	   enunciado.execute("INSERT INTO tablapruebas (id, aleatorio) values ("
		        + id + ", " + numero);
	    
	   }
   
   //se unserta una copia de los primeros 10 resultados en las posiciones de 101 a 110
   
   // CERRAR
   resultados.close();
   enunciado.close();
   conexion.close();

  } catch (Exception e) {
   System.out.println("ERROR: \"" + e.getMessage() + "\"  fin del error");
  }

 }

}