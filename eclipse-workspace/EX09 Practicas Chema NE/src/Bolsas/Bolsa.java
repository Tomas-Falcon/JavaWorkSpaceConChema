package Bolsas;

import java.util.ArrayList;

public class Bolsa {
    // ArrayList para almacenar los productos en la bolsa
    private ArrayList<Producto> listaProductos = new ArrayList<Producto>();

    // Método para agregar un producto a la bolsa si no está llena
    public void addProducto(Producto producto) {
        // Verifica si la bolsa no está llena antes de agregar el producto
        if (!estaLlena()) {
            listaProductos.add(producto);
        }
    }

    // Método para obtener la lista de productos en la bolsa
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    // Método para obtener el tamaño (cantidad de productos) de la bolsa
    public int getSize() {
        return listaProductos.size();
    }

    // Método para verificar si la bolsa está llena
    public boolean estaLlena() {
        // Devuelve true si la cantidad de productos es mayor o igual a 5
        return listaProductos.size() >= 5;
    }
}
