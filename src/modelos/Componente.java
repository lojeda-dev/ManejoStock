package modelos;

import java.util.Objects;

public class Componente {
    private String nombre;
    private int cantElementosConstruccion;
    private int stock;
    private boolean estado;

    public Componente(String nombre, int cantElementosConstruccion, int stock, boolean estado) {
        this.nombre = nombre;
        this.cantElementosConstruccion = cantElementosConstruccion;
        this.stock = stock;
        this.estado = estado;
    }

    public Componente(String nombre, int cantElementosConstruccion) {
        this.nombre = nombre;
        this.cantElementosConstruccion = cantElementosConstruccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantElementosConstruccion() {
        return cantElementosConstruccion;
    }

    public void setCantElementosConstruccion(int cantElementosConstruccion) {
        this.cantElementosConstruccion = cantElementosConstruccion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public static class Builder implements IBuilder {
        private String nombre;
        private int cantElementosConstruccion;
        private int stock;
        private boolean estado;

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

        @Override
        public Componente build() {
            return new Componente(nombre, cantElementosConstruccion, stock, estado);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Componente that = (Componente) o;
        return cantElementosConstruccion == that.cantElementosConstruccion && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, cantElementosConstruccion);
    }
}

