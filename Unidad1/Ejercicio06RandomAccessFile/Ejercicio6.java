import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio6 {
    public static void main(String[] args) throws Exception {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        boolean supplierCorrecto;
        String nameProduct;
        int supplierProduct;
        String ruta = "Unidad1/Ejercicio06RandomAccessFile/productos2_0.csv";

        System.out.print("Introduce el nombre del producto: ");
        nameProduct = sc.readLine();
        do {
            supplierCorrecto = false;
            try {
                System.out.print("Introduce el nuevo supplier del producto: ");
                supplierProduct = Integer.parseInt(sc.readLine());
            } catch (Exception e) {
                System.err.println("No has introducido un numero para el supplier.");
                supplierCorrecto = true;
            }
        } while(supplierCorrecto);
    }
}
