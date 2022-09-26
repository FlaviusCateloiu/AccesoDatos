import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ej03LecturaFicherosTexto {
    public static void main(String[] args) {
        String productoTexto;
        List<String> listaProduct2 = null;
        Path productos = Path.of("Unidad1/Ejercicio03LecturaFicherosTexto/Ej03-LeerFicheros.csv");
        try {
            List<String> listaProduct = Files.readAllLines(productos);
            for (int i = 0; i < listaProduct.size(); i++) {
                productoTexto = listaProduct.get(i);
                productoTexto.split(",");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
