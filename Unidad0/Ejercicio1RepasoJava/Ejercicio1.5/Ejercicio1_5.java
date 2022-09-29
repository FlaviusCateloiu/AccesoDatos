import java.util.Scanner;

public class Ejercicio1_5 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int num;

        System.out.print("Introduce un numero entero: ");
        num = leer.nextInt();

        if (esPrimo(num)) {
            System.out.println(num + " es primo.");
        } else {
            System.out.println(num + " no es primo.");
        }

        if (esPalindromo(num)) {
            System.out.println(num + " es palindromo.");
        } else {
            System.out.println(num + " no es palindromo.");
        }
    }

    private static boolean esPrimo(int num) {
        if (num <= 1 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= num; i+=2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean esPalindromo(int num) {
        int numPal = num, inverso = 0;

        while (num > 0) {
            inverso = (inverso * 10) + (num % 10);
            num = num / 10;
        }

        return  numPal == inverso;
    }
}
