import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4_2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String anotacion = "";
        List<String> listaAnotaciones = new ArrayList<>();
        Path anotaciones = Path.of("Unidad1/Ejercicio04EscribirFicheros/Ejercicio4.2/anotaciones.txt");

        do {
            System.out.print("Introduce una anotacion: ");
            anotacion = teclado.nextLine();

            if (!anotacion.isEmpty()) {
                listaAnotaciones.add(anotacion);
            }
        } while (!anotacion.isEmpty());

        try {
            if (!Files.exists(anotaciones)) {
                Files.write(anotaciones, String.join("\n", listaAnotaciones).getBytes());
            } else {
                Files.writeString(anotaciones, "\n", StandardOpenOption.APPEND);
                Files.write(anotaciones, String.join("\n", listaAnotaciones).getBytes(), StandardOpenOption.APPEND);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
