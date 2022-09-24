import java.util.Scanner;

public class Ejercicio1_1 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        String nombre;

        System.out.print("Dime tu nombre: ");
        nombre = leer.nextLine();

        for (int i = 0; i < 5; i++) {
            System.out.println("Hola, " + nombre);
        }
    }
}
