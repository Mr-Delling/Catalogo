package dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar la persistencia de datos en un fichero.
 */
public class Persistencia {
    private final String nombreFichero;

    public Persistencia(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public void guardarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
            for (Producto producto : productos) {
                writer.write(producto.getMarca() + "," +
                        producto.getModelo() + "," +
                        producto.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los productos: " + e.getMessage());
        }
    }

    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String marca = partes[0];
                String modelo = partes[1];
                double precio = Double.parseDouble(partes[2]);
                productos.add(new Producto(marca, modelo, precio));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No hay datos previos.");
        } catch (IOException e) {
            System.err.println("Error al cargar los productos: " + e.getMessage());
        }
        return productos;
    }
}
