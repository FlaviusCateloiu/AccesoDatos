import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4_1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String frase = "";
        List<String> listaFrases = new ArrayList<>();
        Path frases = Path.of("Unidad1/Ejercicio04EscribirFicheros/Ejercicio4.1/frases.txt");

        do {
            System.out.print("Introduce una frase: ");
            frase = teclado.nextLine();

            if (!frase.isEmpty()) {
                listaFrases.add(frase);
            }
        } while (!frase.isEmpty());

        try {
            Files.write(frases, String.join("\n", listaFrases).getBytes());
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
