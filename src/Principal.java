import cliente.Empresa;
import modelos.*;

public class Principal {

    public static void main(String[] args) {
        ProductoFinal productoFinal = new ProductoFinal.Builder()
                .setNombre("CHEVROLET")
                .setCantElementosConstruccion(2)
                .setStock(1)
                .setListaComponentes(new Componente("VOLANTE", 1))
                .setListaComponentesCompuestos(new ComponenteCompuesto.Builder()
                        .setNombre("PUERTA")
                        .setCantElementosConstruccion(4)
                        .setListaComponentes(new Componente("VIDRIO", 1))
                        .setListaSubComponentesCompuestos(new SubComponenteCompuesto.Builder()
                                .setNombre("CHAPA")
                                .setCantElementosConstruccion(4)
                                .setListaMateriasPrimas(new MateriaPrima("ALUMINIO", 2))
                                .setListaMateriasPrimas(new MateriaPrima("ZINC", 2))
                                .setListaMateriasPrimas(new MateriaPrima("TUNGSTENO", 1))
                                .build())
                        .build())
                .build();

        ProductoFinal pf = new ProductoFinal.Builder()
                .setNombre("FORD")
                .build();


        /*Empresa.PrimerParte pp = new Empresa.PrimerParte();
        pp.cargarProductoFinal(productoFinal);
        pp.mostrarStockTotal();*/

        Empresa.SegundaParte sp = new Empresa.SegundaParte();
        sp.cargarProductoFinal(productoFinal);
        sp.reservar(productoFinal);
        sp.reservar(productoFinal);
        /*sp.reservar(pf);*/

    }
}
