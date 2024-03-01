


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.Query;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
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
    private final static String URL_BASE_DE_DATOS = "jdbc:sqlite:Discoteca.db";

    private Dao<Portero, Integer> daoPortero;
    private Dao<Cliente, Integer> daoCliente;
 
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

        daoPortero = DaoManager.createDao(fuenteConexion, Portero.class);
        daoCliente = DaoManager.createDao(fuenteConexion, Cliente.class);

        // Si necesitas crear la tabla
        TableUtils.createTable(fuenteConexion, Portero.class);
        TableUtils.createTable(fuenteConexion, Cliente.class);
    }

    private void leerEscribirDatos() throws Exception {
       int i = 0;
       int b = 0;
       ArrayList<Portero> porteros = new ArrayList<Portero>();
    	while(i < 2) {
	    	// Creamos la primera instancia de Portero
	        String nombreP [] = {"Axel", "Chenchon"};
	        int altura [] = {175, 195};
	        int peso [] = {85, 130}; 
	        int simpatia [] = {5,2};
	        Portero portero1  = new Portero(nombreP[i], altura[i], peso[i], simpatia[i]);
	        daoPortero.create(portero1);
	        porteros.add(portero1);
	        
	        
	    	i++;
    	}
    	Portero p1 = porteros.get(0);
    	Portero p2 = porteros.get(1);
    	porteros.add(p1);
    	porteros.add(p2);
    	porteros.add(p1);
	    	while(b < 5) {
		        // Creamos un Cliente asociado a la primera Portero
		        String nombre [] = {"Juan", "Maria", "Carlos", "Laura", "Martina"};
		        String sexo [] = {"Hombre", "Mujer", "Hombre", "Mujer", "Mujer"};;
		        int edad [] = {30,35,40,30,23};
		        int precio [] = {10,10,10,10,10};;
		        Cliente ClienteA1 = new Cliente(porteros.get(b), nombre[b], sexo[b],  precio[b],edad[b]);
		        daoCliente.create(ClienteA1);
		        b++;
	    	}
	    
        
       

      

        // Construimos una consulta utilizando QueryBuilder
        QueryBuilder<Cliente, Integer> constructorConsulta = daoCliente.queryBuilder();
        // Debería encontrar todos los Clientes
        List<Cliente> Clientes = daoCliente.query(constructorConsulta.prepare());
        System.out.println("---------------");
        for (Iterator iterator = Clientes.iterator(); iterator.hasNext();) {
			Cliente cliente = (Cliente) iterator.next();
			System.out.println(cliente.getnombre().toString() + " "+ cliente.getEdad() + " " + cliente.getsexo() + " "+ cliente.getPrecio());
		}
        
        
        
        String seleccionarUsuarioSQL = "SELECT * FROM Clientes WHERE sexo = 'Hombre'";
        
        String seleccionarUsuarioSQL3 = "SELECT * FROM Clientes WHERE sexo = 'Mujer' and edad <= 30";
        
        String seleccionarUsuarioSQL2 = "SELECT * FROM Porteros";
        
        
        Statement sentencia = null;
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:Discoteca.db");
        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(seleccionarUsuarioSQL);
        System.out.println("----------------------------");
        while(resultado.next()) {
            int edad = resultado.getInt("edad");
            int precio= resultado.getInt("precio");
            String nombreUsuario = resultado.getString("nombre");
            String sexo = resultado.getString("sexo");
            
            System.out.println("Cliente: nombre = " + nombreUsuario + ", Edad = " + edad + ", sexo = " + sexo + ", Precio = "+precio);
        }
        
        
        resultado = sentencia.executeQuery(seleccionarUsuarioSQL2);
        System.out.println("----------------------------");
        while(resultado.next()) {
            int edad = resultado.getInt("altura");
            int peso= resultado.getInt("peso");
            String nombreUsuario = resultado.getString("nombre");
            int simpatia = resultado.getInt("simpatia");
            
            System.out.println("Portero: nombre = " + nombreUsuario + ", Altura = " + edad + ", Peso = " + peso + ", Simpatia = "+simpatia);
        }
        for(int v = 1; v < 4 ; v++) {
	        String actualizarUsuarioSQL = "UPDATE Clientes SET precio = 0 WHERE id = ?";
	        PreparedStatement declaracionPreparada = conexion.prepareStatement(actualizarUsuarioSQL);
	        declaracionPreparada.setInt(1, v);
	        declaracionPreparada.execute();
        }
        
        String consultin = "SELECT * FROM Clientes";
        resultado = sentencia.executeQuery(consultin);
        System.out.println("----------------------------");
        while(resultado.next()) {
            int edad = resultado.getInt("edad");
            int precio= resultado.getInt("precio");
            String nombreUsuario = resultado.getString("nombre");
            String sexo = resultado.getString("sexo");
            
            System.out.println("Cliente: nombre = " + nombreUsuario + ", Edad = " + edad + ", sexo = " + sexo + ", Precio = "+precio);
        }
        
        
        
        
        resultado = sentencia.executeQuery(seleccionarUsuarioSQL3);
        System.out.println("----------------------------");
        while(resultado.next()) {
            int edad = resultado.getInt("edad");
            int precio= resultado.getInt("precio");
            String nombreUsuario = resultado.getString("nombre");
            String sexo = resultado.getString("sexo");
            
            System.out.println("Cliente: nombre = " + nombreUsuario + ", Edad = " + edad + ", sexo = " + sexo + ", Precio = "+precio);
        }
        
        sentencia.close();
        conexion.close(); 
        resultado.close();
    }
}
