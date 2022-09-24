import java.util.Scanner;

public class Ejericio1_2 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int num;

        System.out.print("Introduce un numero entero: ");
        num = leer.nextInt();

        System.out.print(num + " = ");

        for (int i = 2; i < num; i++) {
            while (num % i == 0) {
                System.out.print(i + " * ");
                num = num / i;
            }
        }
        if (num > 2) {
            System.out.println(num);
        }
    }
}
