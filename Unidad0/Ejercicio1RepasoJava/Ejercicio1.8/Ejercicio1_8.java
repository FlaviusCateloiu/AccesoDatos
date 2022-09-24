import java.util.Scanner;

public class Ejercicio1_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDeDatos listaDatos = new ListaDeDatos();
        String datoGuardado, datoRecorer;

        do {
            System.out.print("Introduce una cadena de texto y para terminar introduce 'fin': ");
            datoGuardado = sc.nextLine();

            if (!datoGuardado.equalsIgnoreCase("fin")) {
                listaDatos.incluir(datoGuardado);
            }
        } while (!datoGuardado.equalsIgnoreCase("fin"));

        do {
            System.out.print("Introduce una cadena de texto: ");
            datoRecorer = sc.nextLine();

            if (!datoRecorer.equalsIgnoreCase("fin")) {
                if (listaDatos.contiene(datoRecorer)) {
                    System.out.println("Esta cadena de texto esta en la lista.");
                } else {
                    System.out.println("Esta cadena de texto no esta en la lista.");
                }
            }
        } while (!datoRecorer.equalsIgnoreCase("fin"));

        listaDatos.mostrarDatosOrdenados();
    }
}
