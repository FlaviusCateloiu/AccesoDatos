import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1_7 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<String> listaCadena = new ArrayList();
        String cadeGuardar, cadeRecorer;

        do {
            System.out.print("Introduce una cadena de texto si quieres finalizar itroduce 'fin': ");
            cadeGuardar = leer.nextLine();

            if (!cadeGuardar.equalsIgnoreCase("fin")) {
                listaCadena.add(cadeGuardar);
            }
        } while (!cadeGuardar.equalsIgnoreCase("fin"));

        do {
            System.out.print("Introduce una cadena de texto: ");
            cadeRecorer = leer.nextLine();

            if (!cadeRecorer.equalsIgnoreCase("fin")) {
                if (listaCadena.contains(cadeRecorer)) {
                    System.out.println("Esta cadena de texto esta en la lista.");
                } else {
                    System.out.println("Esta cadena de texto no esta en la lista.");
                }
            }
        } while (!cadeRecorer.equalsIgnoreCase("fin"));

        for (String cadenaLista : listaCadena) {
            System.out.println(cadenaLista);
        }
    }
}
