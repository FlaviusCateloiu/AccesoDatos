public class Ejercicio4_4 {
    public static void main(String[] args) {
        Product producto = new Product(4, "Madera", 2, 6, 8.32, 18);
        String ruta = "Unidad1/Ejercicio04EscribirFicheros/Ejercicio4.4/productos2_0.csv";
        producto.writeFile(ruta);
        producto = new Product(3, "Mangueras", 243, 3, 2.67, 4);
        producto.writeFile(ruta);
    }
}
