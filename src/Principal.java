import cliente.Empresa;
import modelos.*;

public class Principal {

    public static void main(String[] args) {
        MateriaPrima aluminio = new MateriaPrima("ALUMINIO", 2);
        MateriaPrima tungsteno = new MateriaPrima("TUNGSTENO", 1);
        MateriaPrima zinc = new MateriaPrima("ZINC", 2);
        MateriaPrima hierro = new MateriaPrima("HIERRO", 1);

        /*Componente motor = new Componente("MOTOR", 1);
        Componente volante = new Componente("VOLANTE", 1);
        Componente carroceria = new Componente("CARROCERIA", 1);
        Componente asiento = new Componente("ASIENTO", 4);*/
        Componente vidrio = new Componente("VIDRIO", 4);

        SubComponenteCompuesto chapa = new SubComponenteCompuesto.Builder()
                .setNombre("CHAPA")
                .setCantElementosConstruccion(4)
                .setListaMateriasPrimas(aluminio)
                .setListaMateriasPrimas(tungsteno)
                .setListaMateriasPrimas(zinc)
                .build();
        ComponenteCompuesto puerta = new ComponenteCompuesto.Builder()
                .setNombre("PUERTA")
                .setCantElementosConstruccion(4)
/*
                .setListaComponentes(vidrio)
*/
                .setListaSubComponentesCompuestos(chapa)
                .build();

        ProductoFinal productoFinal = new ProductoFinal.Builder()
                .setNombre("CHEVROLET")
                .setCantElementosConstruccion(5)
                .setStock(2)
                /*.setListaComponentes(motor)
                .setListaComponentes(carroceria)
                .setListaComponentes(asiento)
                .setListaComponentes(volante)*/
                .setListaComponentesCompuestos(puerta)
                .build();


        ProductoFinal pf = new ProductoFinal.Builder()
                .setNombre("FORD")
                .build();


        Empresa.PrimerParte pp = new Empresa.PrimerParte();

        pp.agregarStockMateriaPrima(aluminio, 10);
        pp.agregarStockMateriaPrima(zinc, 10);
        pp.agregarStockMateriaPrima(tungsteno, 10);
        pp.agregarStockMateriaPrima(hierro, 10);
/*
        pp.agregarStockComponente(motor,10);
        pp.agregarStockComponente(volante,10);
        pp.agregarStockComponente(carroceria,10);
        pp.agregarStockComponente(asiento,10);*/
        /*pp.agregarStockComponente(vidrio,10);*/

        /*pp.agregarStockComponenteCompuesto(puerta,10);*/

        /*pp.agregarStockSubComponenteCompuesto(chapa,10);*/

        pp.cargarProductoFinal(productoFinal);
        pp.reservar(productoFinal);
        pp.reservar(productoFinal);
        pp.reservar(productoFinal);
        System.out.println("FINAL");
        pp.mostrarStockTotal();

        pp.consultarEstadoTotal();



        /*Empresa.SegundaParte sp = new Empresa.SegundaParte();
        sp.cargarProductoFinal(productoFinal);
        sp.reservar(productoFinal);
        sp.reservar(productoFinal);
        sp.reservar(pf);*/

    }
}
