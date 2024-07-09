package modelos;

import java.util.HashSet;
import java.util.Set;

public class SubComponenteCompuesto extends Componente {
    private Set<MateriaPrima> listaMateriasPrimas;

    public SubComponenteCompuesto(String nombre, int cantElementosConstruccion, int stock, boolean estado, Set<MateriaPrima> listaMateriasPrimas) {
        super(nombre, cantElementosConstruccion, stock, estado);
        this.listaMateriasPrimas = listaMateriasPrimas;
    }

    public Set<MateriaPrima> getListaMateriasPrimas() {
        return listaMateriasPrimas;
    }


    public static class Builder implements IBuilder {
        private String nombre;
        private int cantElementosConstruccion;
        private int stock;
        private boolean estado;
        private Set<MateriaPrima> listaMateriasPrimas;

        public Builder() {
            this.listaMateriasPrimas = new HashSet<>();
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

        public Builder setListaMateriasPrimas(MateriaPrima materiaPrima) {
            this.listaMateriasPrimas.add(materiaPrima);
            return this;
        }

        @Override
        public SubComponenteCompuesto build() {
            return new SubComponenteCompuesto(nombre, cantElementosConstruccion, stock, estado, listaMateriasPrimas);
        }
    }

}
