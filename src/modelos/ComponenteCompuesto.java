package modelos;

import java.util.HashSet;
import java.util.Set;

public class ComponenteCompuesto extends Componente {
    private Set<Componente> listaComponentes;
    private Set<SubComponenteCompuesto> listaSubComponentesCompuestos;

    public ComponenteCompuesto(String nombre, int cantElementosConstruccion, int stock, boolean estado, Set<Componente> listaComponentes, Set<SubComponenteCompuesto> listaSubComponentesCompuestos) {
        super(nombre, cantElementosConstruccion, stock, estado);
        this.listaComponentes = listaComponentes;
        this.listaSubComponentesCompuestos = listaSubComponentesCompuestos;
    }

    public Set<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public Set<SubComponenteCompuesto> getListaSubComponentesCompuestos() {
        return listaSubComponentesCompuestos;
    }


    public static class Builder implements IBuilder {
        private String nombre;
        private int cantElementosConstruccion;
        private int stock;
        private boolean estado;
        private Set<Componente> listaComponentes;
        private Set<SubComponenteCompuesto> listaSubComponentesCompuestos;

        public Builder() {
            this.listaComponentes = new HashSet<>();
            this.listaSubComponentesCompuestos = new HashSet<>();
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

        public Builder setEstado(boolean estado) {
            this.estado = estado;
            return this;
        }

        public Builder setListaComponentes(Componente componente) {
            this.listaComponentes.add(componente);
            return this;
        }

        public Builder setListaSubComponentesCompuestos(SubComponenteCompuesto subComponenteCompuesto) {
            this.listaSubComponentesCompuestos.add(subComponenteCompuesto);
            return this;
        }

        @Override
        public ComponenteCompuesto build() {
            return new ComponenteCompuesto(nombre, cantElementosConstruccion, stock, estado, listaComponentes, listaSubComponentesCompuestos);
        }
    }
}
