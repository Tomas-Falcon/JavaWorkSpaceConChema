package P2_3_Practica_Crear_y_Consultar_Tabla_DB4O;

import java.util.*;

public class Padre extends Persona {
    private Persona [] hijos;

    // Constructor de la clase Hijo que inicializa atributos heredados de Persona y el nuevo atributo direccion
    Padre( String nombre, int edad, double peso, double altura, Persona... hijos) {
        // Llama al constructor de la clase padre (Persona) para inicializar sus atributos
        super(nombre, edad, peso, altura);
        
        this.hijos = hijos;
    }

    /**
     * @return la dirección del hijo
     */
    public Persona [] getHijos() {
        return hijos;
    }

    /**
     * @param se le pasa hijos y seea el array de hijos
     */
    public void setHijos(Persona [] hijos) {
        this.hijos = hijos;
    }
    
    public String toString() {

        return super.toString() + ", Hijos=" + hijos;

    }
}
