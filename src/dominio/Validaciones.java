package dominio;

/**
 * Clase para validar datos ingresados.
 */
public class Validaciones {
    public static boolean esPrecioValido(double precio) {
        return precio > 0;
    }

    public static boolean esCadenaNoVacia(String cadena) {
        return cadena != null && !cadena.trim().isEmpty();
    }
}
