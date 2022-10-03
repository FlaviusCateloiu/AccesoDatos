import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4_3 {
    public static void main(String[] args) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss: ");
        Date fechaCrecion;
        Scanner teclado = new Scanner(System.in);
        String anotacion = "", fecha = "";
        List<String> listaAnotaciones = new ArrayList<>();
        Path anotaciones = Path.of("Unidad1/Ejercicio04EscribirFicheros/Ejercicio4.3/anotaciones.txt");

        do {
            System.out.print("Introduce una anotacion: ");
            fechaCrecion = new Date(System.currentTimeMillis());
            fecha = formatoFecha.format(fechaCrecion).toString();
            anotacion = teclado.nextLine();
            fecha = fecha + anotacion;

            if (!anotacion.isEmpty()) {
                listaAnotaciones.add(fecha);
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
