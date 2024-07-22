import cliente.Empresa;
import modelos.*;

public class Principal {

    public static void main(String[] args) {

        //CREACION DE LOS OBJETOS NECESARIOS
        MateriaPrima aluminio = new MateriaPrima("ALUMINIO", 2);
        MateriaPrima tungsteno = new MateriaPrima("TUNGSTENO", 1);
        MateriaPrima zinc = new MateriaPrima("ZINC", 2);

        Componente motor = new Componente("MOTOR", 1);
        Componente volante = new Componente("VOLANTE", 1);
        Componente carroceria = new Componente("CARROCERIA", 1);
        Componente asiento = new Componente("ASIENTO", 4);
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
                .setListaComponentes(vidrio)
                .setListaSubComponentesCompuestos(chapa)
                .build();

        ProductoFinal productoFinal = new ProductoFinal.Builder()
                .setNombre("CHEVROLET")
                .setCantElementosConstruccion(5)
                .setStock(1)
                .setListaComponentes(motor)
                .setListaComponentes(carroceria)
                .setListaComponentes(asiento)
                .setListaComponentes(volante)
                .setListaComponentesCompuestos(puerta)
                .build();

        //PRIMER CASO DE USO
        Empresa.PrimerParte pp = new Empresa.PrimerParte();

        //PRIMER PRUEBA CON STOCK SUFICIENTE
        /*pp.agregarStockMateriaPrima(aluminio, 30);
        pp.agregarStockMateriaPrima(zinc, 30);
        pp.agregarStockMateriaPrima(tungsteno, 30);
        pp.agregarStockComponente(motor,30);
        pp.agregarStockComponente(volante,30);
        pp.agregarStockComponente(carroceria,30);
        pp.agregarStockComponente(asiento,30);
        pp.agregarStockComponente(vidrio,30);

        pp.agregarStockComponenteCompuesto(puerta,30);

        pp.agregarStockSubComponenteCompuesto(chapa,30);

        pp.cargarProductoFinal(productoFinal);
        pp.reservar(productoFinal);
        pp.reservar(productoFinal);*/


        //SEGUNDA PRUEBA CON STOCK INSUFICIENTE
        /*pp.agregarStockMateriaPrima(aluminio, 1);
        pp.agregarStockMateriaPrima(zinc, 1);
        pp.agregarStockMateriaPrima(tungsteno, 1);
        pp.agregarStockComponente(motor,1);
        pp.agregarStockComponente(volante,1);
        pp.agregarStockComponente(carroceria,1);
        pp.agregarStockComponente(asiento,1);
        pp.agregarStockComponente(vidrio,1);

        pp.agregarStockComponenteCompuesto(puerta,1);

        pp.agregarStockSubComponenteCompuesto(chapa,1);

        pp.cargarProductoFinal(productoFinal);
        pp.reservar(productoFinal);
        pp.reservar(productoFinal);*/


        //TERCER PRUEBA SIN STOCK DE LOS ELEMENTOS
        /*pp.cargarProductoFinal(productoFinal);
        pp.reservar(productoFinal);
        pp.reservar(productoFinal);*/


        //SEGUNDO CASO DE USO
        /*Empresa.SegundaParte sp = new Empresa.SegundaParte();
        sp.cargarProductoFinal(productoFinal);
        sp.reservar(productoFinal);
        sp.reservar(productoFinal);
        sp.reservar(productoFinal);*/
    }
}
