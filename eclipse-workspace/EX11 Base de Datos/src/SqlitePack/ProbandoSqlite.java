package SqlitePack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ProbandoSqlite {

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        try {
            // ESTABLECER LA CONEXIÓN
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:bdpruebas.db");

            // CREAR ENUNCIADO
            Statement enunciado = conexion.createStatement();

            // CREAR UNA TABLA NUEVA, LA BORRA SI EXISTE
            enunciado.execute("DROP TABLE IF EXISTS tablapruebas;");
            enunciado.execute("CREATE TABLE tablapruebas (id INTEGER PRIMARY KEY, aleatorio INTEGER);");

            // INSERTAR DATOS
            for (int i = 1; i <= 100; i++) {
                enunciado.execute("INSERT INTO tablapruebas (id, aleatorio) values ("
                        + i
                        + ", "
                        + Math.floor(Math.random() * 100 + 1) + ");");
            }

            // CONSULTA DATOS
            ResultSet resultados = enunciado.executeQuery("SELECT * FROM tablapruebas;");

            // Almacenar datos en una lista antes de cerrar el ResultSet
            List<Integer> aleatorios = new ArrayList<>();
            while (resultados.next()) {
                aleatorios.add(resultados.getInt("aleatorio"));
                System.out.println("id " + resultados.getString(1) + ": aleatorio " + resultados.getString(2));
            }

            // INSERTAR DATOS 101-110 COPIANDO 1-10
            String insertQuery = "INSERT INTO tablapruebas (id, aleatorio) VALUES (?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery);

            for (int i = 101; i <= 110; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, aleatorios.get(i - 101)); // Utiliza los valores almacenados en la lista
                preparedStatement.executeUpdate();
            }

            // CONSULTA DATOS DESPUÉS DE LA INSERCIÓN
            resultados = enunciado.executeQuery("SELECT * FROM tablapruebas;");

            // PROCESAR EL RESULTADO
            while (resultados.next()) {
                System.out.println("id " + resultados.getString(1) + ": aleatorio " + resultados.getString(2));
            }

            // CERRAR
            resultados.close();
            enunciado.close();
            preparedStatement.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
