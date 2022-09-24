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
        boolean esPrimo = true;

        if (num <= 1) {
            esPrimo = false;
        }

        for (int i = 2; i != num && esPrimo; i++) {
            if (num % i == 0 ) {
                esPrimo = false;
            }
        }

        return esPrimo;
    }

    private static boolean esPalindromo(int num) {
        boolean esPalindromo = true;
        int numPal = num, sum = 0, r;

        while (num > 0) {
            r = num % 10;
            sum = (sum * 10) + r;
            num = num / 10;
        }

        if (numPal != sum) {
            esPalindromo = false;
        }

        return esPalindromo;
    }
}
