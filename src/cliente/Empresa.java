package cliente;

import modelos.*;
import servicio.Fabrica;

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
            fabrica.reservarProductoFinal(pf, 1);
        }

        public void agregarStockMateriaPrima(MateriaPrima mp, int cantidad) {
            fabrica.agregarMateriaPrima(mp, cantidad);
        }

        public void agregarStockComponente(Componente c, int cantidad) {
            fabrica.agregarComponente(c, cantidad);
        }

        public void agregarStockComponenteCompuesto(ComponenteCompuesto cc, int cantidad) {
            fabrica.agregarComponenteCompuesto(cc, cantidad);
        }

        public void agregarStockSubComponenteCompuesto(SubComponenteCompuesto scc, int cantidad) {
            fabrica.agregarSubComponenteCompuesto(scc, cantidad);
        }

        public void consultarEstadoTotal() {
            fabrica.consultarEstado();
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
            fabrica.reservarProductoFinal(pf, 2);
        }

        public void mostrarStockTotal() {
            fabrica.mostrarStockTotal();
        }
    }

}
