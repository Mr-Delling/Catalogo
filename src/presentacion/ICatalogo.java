package presentacion;

import dominio.Producto;

/**
 * Interfaz para definir las operaciones del catálogo.
 */
public interface ICatalogo {
    void agregarProducto(Producto producto);
    void eliminarProducto(String modelo);
    void modificarProducto(String modelo, Producto actualizado);
    void mostrarProductos();
    void cargarDesdeFichero();
}
