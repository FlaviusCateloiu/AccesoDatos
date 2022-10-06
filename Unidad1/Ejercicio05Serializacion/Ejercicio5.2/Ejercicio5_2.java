import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;

public class Ejercicio5_2 {
    public static void main(String[] args) {
        Path rutaArchivo = Path.of("Unidad1/Ejercicio05Serializacion/Ejercicio5.1/personas.dat");
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        Persona pers;

        try (FileInputStream fis = new FileInputStream(rutaArchivo.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                pers = (Persona) ois.readObject();
                listaPersonas.add(pers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Persona persona : listaPersonas) {
            System.out.println(persona.getNombre());
        }
    }
}
