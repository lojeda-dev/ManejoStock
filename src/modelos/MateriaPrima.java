package modelos;

public class MateriaPrima extends Componente {

    public MateriaPrima(String nombre, int cantElementosConstruccion, int stock, boolean estado) {
        super(nombre, cantElementosConstruccion, stock, estado);
    }

    public MateriaPrima(String nombre, int cantElementosConstruccion) {
        super(nombre, cantElementosConstruccion);
    }
}
