import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Ej03LecturaFicherosTexto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opMenu;
        List<Product> listaProductos  = new ArrayList<Product>();
        Product producto;
        String[] linea;
        Path productos = Path.of("Unidad1/Ejercicio03LecturaFicherosTexto/Ej03-LeerFicheros.csv");
        try {
            List<String> listaLineas = Files.readAllLines(productos);
            for (int i = 1; i < listaLineas.size(); i++) {
                linea = listaLineas.get(i).split(",");
                producto = new Product(Integer.parseInt(linea[0]), linea[1], Integer.parseInt(linea[2]), Integer.parseInt(linea[3]), Double.parseDouble(linea[5]), Integer.parseInt(linea[6]));
                listaProductos.add(producto);
            }

            System.out.println("- Lista de todos los productos: ");
            listaProductos.forEach(System.out::println);
            System.out.println();

            System.out.println("- Los nombres de los productos: ");
            listaProductos.stream().map(Product::getName).forEach(System.out::println);
            System.out.println();

            System.out.println("- Los nombres de los productos cuyas unidades de stock sean menor que 10: ");
            listaProductos.stream().filter(p -> p.getUnitsInStock() < 10).map(Product::getName).forEach(System.out::println);
            System.out.println();

            System.out.println("- Los nombres de los productos cuyas unidades de stock sean mayor que 10 ordenados por unidades de stock de forma descendent: ");
            listaProductos.stream().filter(p -> p.getUnitsInStock() > 10).sorted(Comparator.reverseOrder()).map(Product::getName).forEach(System.out::println);
            System.out.println();

            System.out.println("- El numero de productos agrupados por proveedor: ");

            System.out.println();

            System.out.println("- El producto con el precio unitario mas alto: ");
            listaProductos.stream().sorted((p1, p2) -> Double.compare(p2.getUnitPrice(), p1.getUnitPrice())).limit(1).forEach(System.out::println);
            System.out.println();

            System.out.println("- El promedio de existencias en almacen: ");
            int sum = listaProductos.stream().mapToInt(i -> i.getUnitsInStock()).sum();
            System.out.println(sum / listaProductos.size());
            System.out.println();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
