import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private int supplier;
    private int category;
    private double unitPrice;
    private int unitsInStock;

    public Product(int id, String name, int supplier, int category, double unitPrice, int unitsInStock) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void writeFile(String rutaFichero) {
        List<String> prodCSV = new ArrayList<>();
        Path rutaAlFichero = Path.of(rutaFichero);
        Product prod = buscarProducto(rutaFichero);
        long pos;

        if (prod == null) {
            prodCSV.add(Integer.toString(this.id));
            prodCSV.add(this.name);
            prodCSV.add(Integer.toString(this.supplier));
            prodCSV.add(Integer.toString(this.category));
            prodCSV.add(Double.toString(this.unitPrice));
            prodCSV.add(Integer.toString(this.unitsInStock));

            try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "rw")) {
                raf.seek(raf.length());
                raf.writeBytes("\n");
                raf.write(String.join(",", prodCSV).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            prodCSV.add(Integer.toString(prod.id));
            prodCSV.add(this.name);
            prodCSV.add(Integer.toString(this.supplier));
            prodCSV.add(Integer.toString(prod.category));
            prodCSV.add("");
            prodCSV.add(Double.toString(prod.unitPrice));
            prodCSV.add(Integer.toString(prod.unitsInStock));
            prodCSV.add("");
            prodCSV.add("");
            prodCSV.add("");

            try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "rw")) {
                pos = buscarPosicionProduct(rutaFichero);
                raf.seek(pos);
                raf.write(String.join(",", prodCSV).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private long buscarPosicionProduct(String rutaFichero) {
        String[] contCadena;
        String cadena;
        long pos = 0;
        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "r")) {
            do {
                pos = raf.getFilePointer();
                cadena =  raf.readLine();
                if (cadena != null) {
                    contCadena = cadena.split(",");
                    if (this.name.equals(contCadena[1])) {
                        return pos;
                    }
                }
            } while(cadena != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pos;
    }

    private Product buscarProducto(String rutaFichero) {
        String[] contCadena;
        String cadena;
        Path rutaAlFichero = Path.of(rutaFichero);

        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "r")) {
            do {
                cadena =  raf.readLine();
                if (cadena != null) {
                    contCadena = cadena.split(",");
                    if (this.name.equals(contCadena[1])) {
                        return new Product(Integer.parseInt(contCadena[0]), contCadena[1], Integer.parseInt(contCadena[2]), Integer.parseInt(contCadena[3]), Double.parseDouble(contCadena[5]), Integer.parseInt(contCadena[6]));
                    }
                }
            } while(cadena != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier=" + supplier +
                ", category=" + category +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                '}';
    }


    @Override
    public int compareTo(Product p) {
        if (this.getUnitsInStock() < p.getUnitsInStock())
            return -1;
        else if (this.getUnitsInStock() > p.getUnitsInStock())
            return 1;
        else
            return 0;
    }
}