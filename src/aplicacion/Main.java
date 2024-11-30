package aplicacion;

import dominio.Catalogo;
import dominio.Producto;
import dominio.Validaciones;

import java.util.Scanner;

/**
 * Clase principal del programa. Proporciona una interfaz de texto
 * para interactuar con el catálogo de productos.
 */
public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.cargarDesdeFichero(); // Cargar datos previos si existen

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Gestor de Catálogo ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer

                    // Verificar si el modelo ya existe
                    if (catalogo.existeModelo(modelo)) {
                        System.out.println("Error: Ya existe un producto con el nombre modelo ingresado.");
                    } else if (Validaciones.esCadenaNoVacia(marca) &&
                            Validaciones.esCadenaNoVacia(modelo) &&
                            Validaciones.esPrecioValido(precio)) {
                        catalogo.agregarProducto(new Producto(marca, modelo, precio));
                        System.out.println("Producto agregado.");
                    } else {
                        System.out.println("Error: Datos inválidos.");
                    }
                }
                case 2 -> {
                    System.out.print("Modelo del producto a eliminar: ");
                    String modelo = scanner.nextLine();
                     // Validación del modelo
                     if (catalogo.existeModelo(modelo)) {
                        catalogo.eliminarProducto(modelo);
                        System.out.println("Producto eliminado.");
                    } else {
                        System.out.println("Error: El modelo no existe en el catálogo.");
                    }
                }
                case 3 -> {
                    System.out.print("Modelo del producto a modificar: ");
                    String modelo = scanner.nextLine();
                
                    // Validación del modelo
                    if (catalogo.existeModelo(modelo)) {
                        System.out.print("Nueva marca: ");
                        String nuevaMarca = scanner.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar buffer
                
                        if (Validaciones.esCadenaNoVacia(nuevaMarca) && Validaciones.esPrecioValido(nuevoPrecio)) {
                            catalogo.modificarProducto(modelo, new Producto(nuevaMarca, modelo, nuevoPrecio));
                            System.out.println("Producto modificado.");
                        } else {
                            System.out.println("Error: Datos inválidos.");
                        }
                    } else {
                        System.out.println("Error: El modelo no existe en el catálogo.");
                    }
                }
                case 4 -> catalogo.mostrarProductos();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
