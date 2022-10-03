public class Ejercicio4_4 {
    public static void main(String[] args) {
        Product producto = new Product(1, "Pepe", 3, 4, 3.45, 7);
        String ruta = "Unidad1/Ejercicio04EscribirFicheros/Ejercicio4.4/productos2_0.csv";
        producto.writeFile(ruta);
        producto = new Product(2, "Losas", 23, 4, 6.45, 22);
        producto.writeFile(ruta);
    }
}
