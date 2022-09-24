import java.util.Scanner;

public class Ejercicio1_4 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int num;
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};



        System.out.print("Introduce un numero del 1 al 12: ");
        num = leer.nextInt();

        if (num < 1 || num > 12) {
            System.err.println("Error no has introducido un numero del 1 al 12.");
        } else {
            System.out.println(meses[num - 1]);
        }
    }
}
