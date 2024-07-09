package servicio;

import modelos.*;

import java.util.HashSet;
import java.util.Set;

public class Stock {
    Set<MateriaPrima> materiaPrimas;
    Set<Componente> componentes;
    Set<ComponenteCompuesto> componentesCompuestos;
    Set<SubComponenteCompuesto> subComponentesCompuestos;
    Set<ProductoFinal> productosFinales;

    public Stock() {
        materiaPrimas = new HashSet<>();
        componentes = new HashSet<>();
        componentesCompuestos = new HashSet<>();
        subComponentesCompuestos = new HashSet<>();
        productosFinales = new HashSet<>();
    }

    public void stockTotal() {
        System.out.println("STOCK TOTAL");
        this.materiaPrimas.forEach(mp -> {
            System.out.println("MATERIA PRIMA: " + mp.getNombre() + " [" + mp.getStock() + "]");
        });
        this.componentes.forEach(c -> {
            System.out.println("COMPONENTE: " + c.getNombre() + " [" + c.getStock() + "]");
        });
        this.componentesCompuestos.forEach(cc -> {
            System.out.println("COMPONENTE COMPUESTO: " + cc.getNombre() + " [" + cc.getStock() + "]");
        });
        this.subComponentesCompuestos.forEach(scc -> {
            System.out.println("SUBCOMPONENTE COMPUESTO: " + scc.getNombre() + " [" + scc.getStock() + "]");
            ;
        });
        this.productosFinales.forEach(p -> {
            System.out.println("PRODUCTO FINAL: " + p.getNombre() + " [" + p.getStock() + "]");
        });
    }

    public void stockMateriasPrimas() {
        this.materiaPrimas.forEach(mp -> {
            System.out.println("MATERIA PRIMA: " + mp.getNombre() + " [" + mp.getStock() + "]");
        });
    }

    public void stockComponentes() {
        this.componentes.forEach(c -> {
            System.out.println("COMPONENTE: " + c.getNombre() + " [" + c.getStock() + "]");
        });
    }

    public void stockComponentesCompuestos() {
        this.componentesCompuestos.forEach(cc -> {
            System.out.println("COMPONENTE COMPUESTO: " + cc.getNombre() + " [" + cc.getStock() + "]");
        });
    }

    public void stockSubComponentesCompuestos() {
        this.subComponentesCompuestos.forEach(scc -> {
            System.out.println("SUBCOMPONENTE COMPUESTO: " + scc.getNombre() + " [" + scc.getStock() + "]");
        });
    }

    public void stockProductosFinales() {
        this.productosFinales.forEach(p -> {
            System.out.println("PRODUCTO FINAL: " + p.getNombre() + " [" + p.getStock() + "]");
        });
    }
}
