package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProductoFinal extends Componente {
    private Set<Componente> listaComponentes;
    private Set<ComponenteCompuesto> listaComponentesCompuestos;

    public ProductoFinal(String nombre, int cantElementosConstruccion, int stock, Set<Componente> listaComponentes, Set<ComponenteCompuesto> listaComponentesCompuestos) {
        super(nombre, cantElementosConstruccion, stock);
        this.listaComponentes = listaComponentes;
        this.listaComponentesCompuestos = listaComponentesCompuestos;
    }

    public Set<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public Set<ComponenteCompuesto> getListaComponentesCompuestos() {
        return listaComponentesCompuestos;
    }

    public static class Builder implements IBuilder {
        private String nombre;
        private int cantElementosConstruccion;
        private int stock;
        private Set<Componente> listaComponentes;
        private Set<ComponenteCompuesto> listaComponentesCompuestos;

        public Builder() {
            this.listaComponentes = new HashSet<>();
            this.listaComponentesCompuestos = new HashSet<>();
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setCantElementosConstruccion(int cantElementosConstruccion) {
            this.cantElementosConstruccion = cantElementosConstruccion;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setListaComponentes(Componente componente) {
            this.listaComponentes.add(componente);
            return this;
        }

        public Builder setListaComponentesCompuestos(ComponenteCompuesto componenteCompuesto) {
            this.listaComponentesCompuestos.add(componenteCompuesto);
            return this;
        }

        @Override
        public ProductoFinal build() {
            return new ProductoFinal(nombre, cantElementosConstruccion, stock, listaComponentes, listaComponentesCompuestos);
        }
    }
}
