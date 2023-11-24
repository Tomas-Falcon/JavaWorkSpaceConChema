package P2_3_Practica_Crear_y_Consultar_Tabla_DB4O;

// Definición de la clase Persona
public class Persona {

    // Atributos de la clase
    protected String nombre;
    protected int edad;
    protected double peso;
    protected double altura;

    // Constructor por defecto
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombre, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    // Métodos getter y setter para cada atributo

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    // Método toString para representación de cadena
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' + ", edad=" + edad + ", peso=" + peso +", altura=" + altura + '}';
    }
}
