package servicio;

import modelos.Componente;
import modelos.ComponenteCompuesto;
import modelos.ProductoFinal;

import java.util.Set;

public class Fabrica {
    Stock stock;

    public Fabrica() {
        this.stock = new Stock();
    }

    public void cargarProductoFinal(ProductoFinal pr) {
        this.stock.productosFinales.add(pr);
    }

    public void reservarProductoFinal(ProductoFinal pf, int opcion) {
        if (opcion == 1) {
            if (stock.productosFinales.contains(pf)) {
                if (stockDisponibleProductoFinal(pf)) {
                    System.out.println("SE RESERVO (" + pf.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockProductosFinales();
                } else {
                    System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                    reservarElementos(pf);
                }
            }
        } else {
            if (stockDisponibleProductoFinal(pf)) {
                System.out.println("SE RESERVO (" + pf.getNombre() + ") EXITOSAMENTE");
                this.stock.stockProductosFinales();
            } else {
                System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                System.out.println("NO SE PUEDE RESERVAR, NI FABRICAR");
            }
        }
    }

    public void reservarElementos(ProductoFinal pf) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL PRODUCTO FINAL(" + pf.getNombre() + ")");
        Set<Componente> componentes = pf.getListaComponentes();
        Set<ComponenteCompuesto> componentesCompuestos = pf.getListaComponentesCompuestos();

       /* if (!componentes.isEmpty())
            componentes.forEach(c -> {
                if (reservarComponente(c)){
                    System.out.println("SE RESERVO (" + c.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockComponentes();
                }
            });
        if (!componentesCompuestos.isEmpty())
            componentesCompuestos.forEach(cc -> {
                if (reservarComponenteCompuesto(cc)){
                    System.out.println("SE RESERVO (" + cc.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockComponentesCompuestos();
                }
            });*/

        System.out.println("HOLA");
    }

  /*  public boolean reservarComponente(Componente c){
        if (this.stock.componentes.contains(c)){
           if (c.getStock() > 0){
               System.out.println("STOCK DISPONIBLE :)");
               this.stock.stockComponentes();
               int cantidad = c.getStock() - 1;
           }
        }
    }*/

    /*public boolean reservarComponenteCompuesto(ComponenteCompuesto cc){

    }*/

    public boolean stockDisponibleProductoFinal(ProductoFinal pf) {
        if (pf.getStock() > 0) {
            System.out.println("STOCK DISPONIBLE :)");
            this.stock.stockProductosFinales();
            int cantidad = pf.getStock() - 1;
            pf.setStock(cantidad);
            return true;
        } else
            return false;
    }

    public void mostrarStockTotal() {
        stock.stockTotal();
    }
}
