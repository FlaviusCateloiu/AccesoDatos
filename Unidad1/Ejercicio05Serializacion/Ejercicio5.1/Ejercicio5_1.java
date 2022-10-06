import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

public class Ejercicio5_1 {
    public static void main(String[] args) {
        Path rutaArchivo = Path.of("Unidad1/Ejercicio05Serializacion/Ejercicio5.1/personas.dat");
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

        listaPersonas.add(new Persona("Pepe", "pepegamer1990@gmail.com", new Date(1990, 02, 21)));
        listaPersonas.add(new Persona("Manolo", "manoloraptor4000@gmail.com", new Date(2000, 12 , 01)));
        listaPersonas.add(new Persona("Roberto", "roberto94_f@gmail.com", new Date(1994, 06 , 24)));

        try (FileOutputStream fos = new FileOutputStream(rutaArchivo.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Persona pers : listaPersonas) {
                oos.writeObject(pers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
