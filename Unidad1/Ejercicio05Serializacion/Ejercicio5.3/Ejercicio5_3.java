import java.io.*;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ejercicio5_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        boolean formatoFecha;
        String op = "", nombre = "", email = "", fechaNacimiento = "";
        Date fecha = null;
        Persona pers;

        do {
            System.out.printf("%s MENU AGENDA %s\n", "-".repeat(10), "-".repeat(10));
            System.out.println("1.- Introducir a una Persona.");
            System.out.println("2.- Buscar a una Persona.");
            System.out.println("3.- Salir del programa.");
            System.out.print("Introduce el numero de la opcion que quieres: ");
            op = sc.readLine();

            switch (op) {
                case "1":
                    cargarPersonas(listaPersonas);
                    System.out.print("Introduce el nombre: ");
                    nombre = sc.readLine();
                    System.out.println("Introudce el email: ");
                    email = sc.readLine();
                    do {
                        try {
                            System.out.println("Introduce la fecha de nacimiento con el formato dd/MM/yyyy: ");
                            fechaNacimiento = sc.readLine();

                            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
                            formatoFecha = false;
                        } catch (Exception e) {
                            formatoFecha = true;
                            System.out.println("Formato de la fecha incorrecto.");
                        }
                    } while (formatoFecha);

                    pers = new Persona(nombre, email, fecha);
                    listaPersonas.add(pers);
                    guardarPersonas(listaPersonas);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.err.println("Error has introducido una opcion del menu incorrecta.");
            }

        } while (!op.equals("3"));
    }

    public static void guardarPersonas(ArrayList<Persona> listaPersonas) {
        Path rutaArchivo = Path.of("Unidad1/Ejercicio05Serializacion/Ejercicio5.1/personas.dat");
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Persona pers : listaPersonas) {
                oos.writeObject(pers);
            }
        } catch (Exception e) {
            System.out.println("No se ha podido introducir a las personas.");
        }
    }

    public static void cargarPersonas(ArrayList<Persona> listaPersonas) {
        Path rutaArchivo = Path.of("Unidad1/Ejercicio05Serializacion/Ejercicio5.1/personas.dat");
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
    }
}
