package modelos;

public class MateriaPrima extends Componente {

    public MateriaPrima(String nombre, int cantElementosConstruccion, int stock) {
        super(nombre, cantElementosConstruccion, stock);
    }

    public MateriaPrima(String nombre, int cantElementosConstruccion) {
        super(nombre, cantElementosConstruccion);
    }
}
