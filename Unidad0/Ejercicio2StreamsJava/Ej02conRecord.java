import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// A partir de la versión 14 del JDK disponemos de los Java Records, los cuales
// permiten simplificar la construcción de clases para modelar objetos de datos
// simples de transferencia (DTOs) eliminando la necesidad de implementar
// constructores, métodos de lectura, de escritura, hashCode, equals y toString.
record LibroRecord(String titulo, int numPaginas, String autor) {}

public class Ej02conRecord {
    public static void main(String[] args)
    {
        List<LibroRecord> libros = new ArrayList<>();

        libros.add(new LibroRecord("El Señor de los Anillos", 800, "J.R.R. Tolkien"));
        libros.add(new LibroRecord("El Hobbit", 350, "J.R.R. Tolkien"));
        libros.add(new LibroRecord("Cabo Trafalgar", 320, "Arturo Pérez Reverte"));
        libros.add(new LibroRecord("El corazón de la piedra", 560, "José María García López"));
        libros.add(new LibroRecord("Salmos de vísperas", 95, "Esteban Hernández Castelló"));
        libros.add(new LibroRecord("La música en las catedrales españolas del Siglo de Oro", 600, "Robert Stevenson"));
        libros.add(new LibroRecord("Luces de bohemia", 296, "Ramón del Valle-Inclán"));
        libros.add(new LibroRecord("Contando atardecere", 528, "La vecina rubia"));
        libros.add(new LibroRecord("Master - Roger Federer", 456, "Christopher Clarey"));
        libros.add(new LibroRecord("La teoría de los archipiélagos", 300, "Alice Kellen"));
        libros.add(new LibroRecord("Esperando al diluvio", 576, "Dolores Redondo"));
        libros.add(new LibroRecord("El italiano", 400, "Arturo Pérez Reverte"));
        libros.add(new LibroRecord("Línea de fuego", 688, "Arturo Pérez Reverte"));

        for(LibroRecord l: libros)
        {
            System.out.println("Título: " + l.titulo());
            System.out.println("Autor: " + l.autor());
        }

        List numPagMas500 = libros.stream().filter(libro -> libro.numPaginas() > 500).collect(Collectors.toList());
        System.out.println("El numero de libros con mas de 500 paginas es de: " + numPagMas500.size());
        System.out.println();

        List numPagMenos300 = libros.stream().filter(libro -> libro.numPaginas() < 300).collect(Collectors.toList());
        System.out.println("El numero de libros con mas de 500 paginas es de: " + numPagMenos300.size());
        System.out.println();

        System.out.println("Titulos de los libros de mas de 500 paginas:");
        libros.stream().filter(libro -> libro.numPaginas() > 500).map(libro -> libro.titulo()).forEach(titulo -> System.out.println("- " + titulo));
        System.out.println();

        System.out.println("Titulos de los 3 libros con mas paginas: ");
        libros.stream().sorted((l1, l2) -> Integer.compare(l2.numPaginas(), l1.numPaginas())).limit(3).map(libro -> libro.titulo()).forEach(titulo -> System.out.println("- " + titulo));
        System.out.println();

        System.out.println("Total de la suma de las paginas de todos los libros: ");
        System.out.println(libros.stream().mapToInt(np -> np.numPaginas()).sum());
        System.out.println();

        System.out.println("Todos los libros que superen el promedio en cuanto a numero de paginas se refiere: ");
        int media = 0;
        libros.stream().mapToInt(l -> l.numPaginas() + media).forEach(System.out::println);
        System.out.println();

        System.out.println("Los autores de todos los libros:");
        libros.stream().map(l -> l.autor()).distinct().forEach(System.out::println);
        System.out.println();

        System.out.println("Libro con el numero de paginas mayor:");
        libros.stream().sorted((l1, l2) -> Integer.compare(l2.numPaginas(), l1.numPaginas())).limit(1).map(libro -> libro.titulo()).forEach(titulo -> System.out.println("- " + titulo));
        System.out.println();

        List<String> titulosLibros = libros.stream().map(l -> l.titulo()).collect(Collectors.toList());
    }
}
