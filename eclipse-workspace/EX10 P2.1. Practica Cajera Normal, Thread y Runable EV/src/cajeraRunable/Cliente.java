package cajeraRunable;  //misma cliente normal
public class Cliente {

    private String nombre;
    private int[] carroCompra;

    // Constructor de la clase Cliente
    Cliente(String nombre, int carroCompra[]) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }

    // Métodos getter y setter para el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos getter y setter para el carro de compra del cliente
    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }
}