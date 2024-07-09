package servicio;

import modelos.*;

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
                } else {
                    System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                    reservarElementos(pf);
                }
            } else {
                System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
                reservarElementos(pf);
            }
        } else {
            if (stockDisponibleProductoFinal(pf)) {
                System.out.println("SE RESERVO (" + pf.getNombre() + ") EXITOSAMENTE");
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

        if (!componentes.isEmpty()) {
            componentes.forEach(c -> {
                if (reservarComponente(c)) {
                    System.out.println("SE RESERVO (" + c.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!componentesCompuestos.isEmpty()) {
            componentesCompuestos.forEach(cc -> {
                if (reservarComponenteCompuesto(cc)) {
                    System.out.println("SE RESERVO (" + cc.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockComponentesCompuestos();
                } else
                    fabricarComponenteCompuesto(cc);
            });
        } else
            System.out.println("LISTA DE COMPONENTES COMPUESTOS VACIA");

    }

    public boolean reservarComponente(Componente c) {
        if (this.stock.componentes.contains(c)) {
            if (c.getStock() >= c.getCantElementosConstruccion()) {
                System.out.println("STOCK DISPONIBLE :)");
                this.stock.stockComponentes();
                int cantidad = c.getStock() - c.getCantElementosConstruccion();
                c.setEstado(true);
                c.setStock(cantidad);
                return true;
            } else {
                System.out.println("EL COMPONENTE(" + c.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockComponentes();
                return false;
            }
        } else {
            System.out.println("EL COMPONENTE(" + c.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            this.stock.stockComponentes();
            return false;
        }
    }

    public void fabricarComponente(Componente c) {
        System.out.println("A CONTINUACION SE FABRICARA LA CANTIDAD DE [" + c.getCantElementosConstruccion() + "]");
        c.setStock(c.getCantElementosConstruccion());
        this.stock.componentes.add(c);
        this.stock.stockComponentes();
    }

    public boolean reservarComponenteCompuesto(ComponenteCompuesto cc) {
        if (this.stock.componentesCompuestos.contains(cc)) {
            if (cc.getStock() >= cc.getCantElementosConstruccion()) {
                System.out.println("STOCK DISPONIBLE :)");
                this.stock.stockComponentesCompuestos();
                int cantidad = cc.getStock() - cc.getCantElementosConstruccion();
                cc.setEstado(true);
                cc.setStock(cantidad);
                return true;
            } else {
                System.out.println("EL COMPONENTE COMPUESTO(" + cc.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockComponentesCompuestos();
                return false;
            }
        } else {
            System.out.println("EL COMPONENTE COMPUESTO(" + cc.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            this.stock.stockComponentesCompuestos();
            return false;
        }
    }

    public void fabricarComponenteCompuesto(ComponenteCompuesto cc) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL COMPONENTE COMPUESTO(" + cc.getNombre() + ")");

        Set<Componente> componentes = cc.getListaComponentes();
        Set<SubComponenteCompuesto> subComponenteCompuestos = cc.getListaSubComponentesCompuestos();

        if (!componentes.isEmpty()) {
            componentes.forEach(c -> {
                if (reservarComponente(c)) {
                    System.out.println("SE RESERVO (" + c.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!subComponenteCompuestos.isEmpty()) {
            subComponenteCompuestos.forEach(scc -> {
                if (reservarSubComponenteComponente(scc)) {
                    System.out.println("SE RESERVO (" + scc.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockSubComponentesCompuestos();
                } else
                    fabricarSubComponenteCompuesto(scc);
            });
        } else
            System.out.println("LISTA DE SUBCOMPONENTES COMPUESTOS VACIA");
    }

    public boolean reservarSubComponenteComponente(SubComponenteCompuesto scc) {
        if (this.stock.subComponentesCompuestos.contains(scc)) {
            if (scc.getStock() >= scc.getCantElementosConstruccion()) {
                System.out.println("STOCK DISPONIBLE :)");
                this.stock.stockComponentesCompuestos();
                int cantidad = scc.getStock() - scc.getCantElementosConstruccion();
                scc.setEstado(true);
                scc.setStock(cantidad);
                return true;
            } else {
                System.out.println("EL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockComponentesCompuestos();
                return false;
            }
        } else {
            System.out.println("EL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            this.stock.stockComponentesCompuestos();
            return false;
        }
    }

    public void fabricarSubComponenteCompuesto(SubComponenteCompuesto scc) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
        Set<MateriaPrima> materiaPrimas = scc.getListaMateriasPrimas();

        if (!materiaPrimas.isEmpty()) {
            materiaPrimas.forEach(mp -> {
                if (reservarMateriaPrima(mp)) {
                    System.out.println("SE RESERVO (" + mp.getNombre() + ") EXITOSAMENTE");
                    this.stock.stockSubComponentesCompuestos();
                } else
                    comprarMateriaPrima(mp, scc.getCantElementosConstruccion());
            });
        }
    }

    public boolean reservarMateriaPrima(MateriaPrima mp) {
        if (this.stock.subComponentesCompuestos.contains(mp)) {
            if (mp.getStock() >= mp.getCantElementosConstruccion()) {
                System.out.println("STOCK DISPONIBLE :)");
                this.stock.stockMateriasPrimas();
                int cantidad = mp.getStock() - mp.getCantElementosConstruccion();
                mp.setEstado(true);
                mp.setStock(cantidad);
                return true;
            } else {
                System.out.println("LA MATERIA PRIMA(" + mp.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockMateriasPrimas();
                return false;
            }
        } else {
            System.out.println("LA MATERIA PRIMA(" + mp.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            this.stock.stockMateriasPrimas();
            return false;
        }
    }

    public void comprarMateriaPrima(MateriaPrima mp, int cantidad) {
        System.out.println("A CONTINUACION SE COMPRARA LA MATERIA PRIMA(" + mp.getNombre() + ")");
        System.out.println("CANTIDAD: [" + mp.getCantElementosConstruccion() * cantidad + "]");
        mp.setStock(mp.getCantElementosConstruccion()*cantidad);
        this.stock.materiaPrimas.add(mp);

    }

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
