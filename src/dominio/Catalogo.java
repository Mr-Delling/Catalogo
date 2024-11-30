package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona el catálogo de productos.
 */
public class Catalogo {
    private List<Producto> productos;
    private Persistencia persistencia;

    public Catalogo() {
        this.productos = new ArrayList<>();
        this.persistencia = new Persistencia("catalogo.txt");
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        persistencia.guardarProductos(productos); // Guardar cambios al fichero
    }

    public void eliminarProducto(String modelo) {
        productos.removeIf(producto -> producto.getModelo().equalsIgnoreCase(modelo));
        persistencia.guardarProductos(productos); // Guardar cambios al fichero
    }

    public void modificarProducto(String modelo, Producto nuevoProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getModelo().equalsIgnoreCase(modelo)) {
                productos.set(i, nuevoProducto); // Actualizar producto
                persistencia.guardarProductos(productos); // Guardar cambios al fichero
                return;
            }
        }
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    public void cargarDesdeFichero() {
        productos = persistencia.cargarProductos();
    }

    /**
     * Comprueba si un modelo existe en el catálogo.
     *
     * @param modelo El modelo a buscar.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeModelo(String modelo) {
        return productos.stream()
                .anyMatch(producto -> producto.getModelo().equalsIgnoreCase(modelo));
    }
}

