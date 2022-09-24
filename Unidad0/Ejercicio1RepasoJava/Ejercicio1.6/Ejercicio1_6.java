import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1_6 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList listaNum = new ArrayList();
        int numGuardar, numRecorer;

        do {
            System.out.print("Introduce un numero entero para guardar en la lista, para finalizar introduce uno negativo: ");
            numGuardar = leer.nextInt();

            if (numGuardar >= 0) {
                listaNum.add(numGuardar);
            }
        } while (numGuardar >= 0);

        do {
            System.out.print("Introduce un numero entero: ");
            numRecorer = leer.nextInt();

            if (numRecorer >= 0) {
                if (listaNum.contains(numRecorer)) {
                    System.out.println("Este numero esta en la lista.");
                } else {
                    System.out.println("Este numero no esta en la lista.");
                }
            }
        } while (numRecorer >= 0);

        for (int i = 0; i < listaNum.size(); i++) {
            System.out.println(listaNum.get(i));
        }
    }
}
