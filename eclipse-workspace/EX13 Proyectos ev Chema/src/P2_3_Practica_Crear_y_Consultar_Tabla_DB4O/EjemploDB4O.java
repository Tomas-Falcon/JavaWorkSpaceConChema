package P2_3_Practica_Crear_y_Consultar_Tabla_DB4O;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class EjemploDB4O {
    // Nombre del archivo de la base de datos
    private static final String NOMBRE_ARCHIVO_DB = "personas.db4o";
    
    
    
    public static void main(String[] args) {
        // Abrimos la conexión a la base de datos
        ObjectContainer db = Db4oEmbedded.openFile(NOMBRE_ARCHIVO_DB);

        // Creamos instancias de la clase Persona
        Persona persona1 = new Persona("Fernando", 30, 1.73, 73);
        Persona persona2 = new Persona("Pepe", 30, 1.75, 80);
        Persona persona3 = new Persona("Alfredo", 20, 1.9, 90);
        Persona persona4 = new Persona("Roberto", 35, 1.70, 70);
        Persona persona5 = new Persona("Domingo", 30, 1.73, 80);
        
       
        
        Padre padre1 = new Padre("Lunatico", 30, 1.73, 80, persona1, persona2);
        Padre padre2 = new Padre("Lunatico", 30, 1.73, 80, persona3);
        Padre padre3 = new Padre("Lunatico", 30, 1.73, 80, persona4);

        // Almacenamos las instancias en la base de datos
        almacenarPersonas(db, persona1, persona2, persona3, persona4, persona5);
        almacenarPersonas(db, padre1, padre2, padre3);

        // Mostramos todas las personas almacenadas
        consultarYMostrarTodas(db);

        // Consultamos y mostramos personas de 30 años
        consultarYMostrarPorEdad(db, 30);

        // Consultamos y mostramos personas de 30 años y 1.73cm de altura
        consultarYMostrarPorEdadAltura(db, 30, 1.73);

        // Consultamos y mostramos personas llamadas Domingo
        consultarYMostrarPorNombre(db, "Domingo");
 

        // Borramos personas llamadas Pepe
        borrarPorNombre(db, "Pepe");

        // Cerramos la conexión a la base de datos
        db.close();
    }

    // Método para almacenar personas en la base de datos
    private static void almacenarPersonas(ObjectContainer db, Persona... personas) {
        for (Persona persona : personas) {
            db.store(persona);
        }
    }
    
    private static void almacenarPersonas(ObjectContainer db, Padre... padre) {
    	db.store(padre);
    }
    // Método para consultar y mostrar todas las personas en la base de datos
    private static void consultarYMostrarTodas(ObjectContainer db) {
        Persona personaEjemplo = new Persona();
        ObjectSet<Persona> resultado = db.queryByExample(personaEjemplo);
        System.out.println("Todas las personas");
        mostrarResultados(resultado);
    }

    // Método para consultar y mostrar personas por edad
    private static void consultarYMostrarPorEdad(ObjectContainer db, int edad) {
        Persona personaEjemplo = new Persona(null, edad, 0, 0);
        ObjectSet<Persona> resultado = db.queryByExample(personaEjemplo);
        System.out.println("Personas con " + edad + " años");
        mostrarResultados(resultado);
    }

    // Método para consultar y mostrar personas por edad y altura
    private static void consultarYMostrarPorEdadAltura(ObjectContainer db, int edad, double altura) {
        Persona personaEjemplo = new Persona(null, edad, altura, 0);
        ObjectSet<Persona> resultado = db.queryByExample(personaEjemplo);
        System.out.println("Personas con " + edad + " años y " + altura + " cm de altura");
        mostrarResultados(resultado);
    }

    // Método para consultar y mostrar personas por nombre
    private static void consultarYMostrarPorNombre(ObjectContainer db, String nombre) {
        Persona personaEjemplo = new Persona(nombre, 0, 0, 0);
        ObjectSet<Persona> resultado = db.queryByExample(personaEjemplo);
        
        System.out.println("Personas llamadas " + nombre);
        mostrarResultados(resultado);
        
    }

    // Método para borrar personas por nombre
    private static void borrarPorNombre(ObjectContainer db, String nombre) {
        Persona personaEjemplo = new Persona(nombre, 0, 0, 0);
        ObjectSet<Persona> resultado = db.queryByExample(personaEjemplo);
        
        System.out.println("Borradas Personas llamadas " + nombre);
        while (resultado.hasNext()) {
            db.delete(resultado.next());
        }
        
    }

    // Método para mostrar los resultados de una consulta
    private static void mostrarResultados(ObjectSet<Persona> resultado) {
        while (resultado.hasNext()) {
            System.out.println(resultado.next());
        }
    }
    
    
}
