package cliente;

import servicio.Fabrica;
import modelos.ProductoFinal;

public class Empresa {
    public static class PrimerParte {
        private Fabrica fabrica;

        public PrimerParte() {
            this.fabrica = new Fabrica();
        }

        public void cargarProductoFinal(ProductoFinal pf) {
            fabrica.cargarProductoFinal(pf);
        }

        public void reservar(ProductoFinal pf) {
            fabrica.reservarProductoFinal(pf,1);
        }


        public void mostrarStockTotal() {
            fabrica.mostrarStockTotal();
        }
    }

    public static class SegundaParte {
        Fabrica fabrica;

        public SegundaParte() {
            this.fabrica = new Fabrica();
        }

        public void cargarProductoFinal(ProductoFinal pf) {
            fabrica.cargarProductoFinal(pf);
        }

        public void reservar(ProductoFinal pf) {
            fabrica.reservarProductoFinal(pf,2);
        }

        public void mostrarStockTotal() {
            fabrica.mostrarStockTotal();
        }
    }

}
