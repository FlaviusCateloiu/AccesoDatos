import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ejericio1_2 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<Integer> listaPrimos = new ArrayList();
        int num;

        System.out.print("Introduce un numero entero: ");
        num = leer.nextInt();

        System.out.print(num + " = ");

        for (int i = 2; i <= Math.sqrt(num); i++) {
            while (num % i == 0) {
                num = num / i;
                listaPrimos.add(i);
            }
        }

        if (num > 2)
            listaPrimos.add(num);

        System.out.print(listaPrimos.stream().map(Object::toString).collect(Collectors.joining(" * ")));
    }
}
