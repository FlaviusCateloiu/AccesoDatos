import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio6 {
    public static void main(String[] args) throws Exception {
        /* http://puntocomnoesunlenguaje.blogspot.com/2013/06/java-ficheros-acceso-aleatorio.html */
        Product producto = new Product(11, "Madera", 1, 11, 9.2, 9);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String ruta = "Unidad1/Ejercicio06RandomAccessFile/productos2_0.csv";

        producto.writeFile(ruta);
    }
}
