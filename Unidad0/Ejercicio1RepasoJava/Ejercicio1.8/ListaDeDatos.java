import java.util.ArrayList;

public class ListaDeDatos {
    private ArrayList<String> listaDatos;

    public ListaDeDatos() {
        listaDatos = new ArrayList();
    }

    public void incluir(String texto) {
        listaDatos.add(texto);
    }

    public boolean contiene(String texto) {
        boolean contiene = true;

        for (String dato : listaDatos) {
            if (!texto.equals(dato)) {
                contiene = false;
            }
        }

        return contiene;
    }

    public void mostrarDatosOrdenados() {
        for (String dato : listaDatos) {
            System.out.println(dato);
        }
    }
}
