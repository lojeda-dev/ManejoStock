package modelos;

import java.util.Objects;

public class Componente {
    private String nombre;
    private int cantElementosConstruccion;
    private int stock;

    public Componente(String nombre, int cantElementosConstruccion, int stock) {
        this.nombre = nombre;
        this.cantElementosConstruccion = cantElementosConstruccion;
        this.stock = stock;
    }

    public Componente(String nombre, int cantElementosConstruccion) {
        this.nombre = nombre;
        this.cantElementosConstruccion = cantElementosConstruccion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantElementosConstruccion() {
        return cantElementosConstruccion;
    }

    public int getStock() {
        return stock;
    }

}

