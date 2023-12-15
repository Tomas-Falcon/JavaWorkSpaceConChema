package P2_2_Practica_CRUD_en_MySQL;

import java.sql.*;

public class EjemploCRUD {

    // URL, usuario y contraseña de MySQL
    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/tu_nombre_de_base_de_datos";
    private static final String USUARIO_JDBC = "tu_usuario";
    private static final String CONTRASEÑA_JDBC = "tu_contraseña";

    // Variables JDBC para abrir, cerrar y gestionar la conexión
    private static Connection conexion;
    private static Statement sentencia;
    private static ResultSet resultado;

    public static void main(String[] args) {
        try {
            // Abrir la conexión
            abrirConexion();

            // Crear la tabla si no existe
            crearTabla();

            // Insertar un nuevo usuario
            int idUsuario = crearUsuario("JuanPerez", "juan.perez@ejemplo.com");
            System.out.println("Usuario creado con ID: " + idUsuario);

            // Leer todos los usuarios
            leerTodosLosUsuarios();

            // Actualizar la información del usuario
            actualizarUsuario(idUsuario, "JuanPerezActualizado", "actualizado.email@ejemplo.com");

            // Leer el usuario actualizado
            leerUsuario(idUsuario);

            // Eliminar el usuario
            eliminarUsuario(idUsuario);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión, la sentencia y el conjunto de resultados
            cerrarConexion();
        }
    }

    private static void abrirConexion() throws SQLException {
        conexion = DriverManager.getConnection(URL_JDBC, USUARIO_JDBC, CONTRASEÑA_JDBC);
        sentencia = conexion.createStatement();
    }

    private static void cerrarConexion() {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void crearTabla() throws SQLException {
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nombre_usuario VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL"
                + ")";
        sentencia.executeUpdate(crearTablaSQL);
    }

    private static int crearUsuario(String nombreUsuario, String email) throws SQLException {
        String insertarUsuarioSQL = "INSERT INTO Usuario (nombre_usuario, email) VALUES (?, ?)";
        try (PreparedStatement declaracionPreparada = conexion.prepareStatement(insertarUsuarioSQL, Statement.RETURN_GENERATED_KEYS)) {
            declaracionPreparada.setString(1, nombreUsuario);
            declaracionPreparada.setString(2, email);
            declaracionPreparada.executeUpdate();

            // Obtener la clave generada automáticamente (ID de usuario)
            ResultSet clavesGeneradas = declaracionPreparada.getGeneratedKeys();
            if (clavesGeneradas.next()) {
                return clavesGeneradas.getInt(1);
            } else {
                throw new SQLException("Error al crear el usuario, no se obtuvo el ID.");
            }
        }
    }

    private static void leerTodosLosUsuarios() throws SQLException {
        String seleccionarTodosLosUsuariosSQL = "SELECT * FROM Usuario";
        resultado = sentencia.executeQuery(seleccionarTodosLosUsuariosSQL);
        while (resultado.next()) {
            int id = resultado.getInt("id");
            String nombreUsuario = resultado.getString("nombre_usuario");
            String email = resultado.getString("email");
            System.out.println("Usuario: ID = " + id + ", Nombre de usuario = " + nombreUsuario + ", Email = " + email);
        }
    }

    private static void leerUsuario(int idUsuario) throws SQLException {
        String seleccionarUsuarioSQL = "SELECT * FROM Usuario WHERE id = " + idUsuario;
        resultado = sentencia.executeQuery(seleccionarUsuarioSQL);
        if (resultado.next()) {
            int id = resultado.getInt("id");
            String nombreUsuario = resultado.getString("nombre_usuario");
            String email = resultado.getString("email");
            System.out.println("Usuario: ID = " + id + ", Nombre de usuario = " + nombreUsuario + ", Email = " + email);
        } else {
            System.out.println("Usuario no encontrado con ID: " + idUsuario);
        }
    }

    private static void actualizarUsuario(int idUsuario, String nuevoNombreUsuario, String nuevoEmail) throws SQLException {
        String actualizarUsuarioSQL = "UPDATE Usuario SET nombre_usuario = ?, email = ? WHERE id = ?";
        try (PreparedStatement declaracionPreparada = conexion.prepareStatement(actualizarUsuarioSQL)) {
            declaracionPreparada.setString(1, nuevoNombreUsuario);
            declaracionPreparada.setString(2, nuevoEmail);
            declaracionPreparada.setInt(3, idUsuario);
            int filasAfectadas = declaracionPreparada.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado exitosamente.");
            } else {
                System.out.println("Usuario no encontrado con ID: " + idUsuario);
            }
        }
    }

    private static void eliminarUsuario(int idUsuario) throws SQLException {
        String eliminarUsuarioSQL = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement declaracionPreparada = conexion.prepareStatement(eliminarUsuarioSQL)) {
            declaracionPreparada.setInt(1, idUsuario);
            int filasAfectadas = declaracionPreparada.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("Usuario no encontrado con ID: " + idUsuario);
            }
        }
    }
}

