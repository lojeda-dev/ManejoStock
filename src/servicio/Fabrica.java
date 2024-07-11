package servicio;

import modelos.*;

import java.util.IllegalFormatCodePointException;
import java.util.Set;

public class Fabrica {
    Stock stock;

    public Fabrica() {
        this.stock = new Stock();
    }

    public void cargarProductoFinal(ProductoFinal pr) {
        this.stock.productosFinales.add(pr);
        System.out.println("SE AGREGO EL PRODUCTO FINAL(" + pr.getNombre() + ")");
        this.stock.stockProductosFinales();
    }

    public void reservarProductoFinal(ProductoFinal pf, int opcion) {
        if (opcion == 1) {
            if (stock.productosFinales.contains(pf)) {
                if (stockDisponibleProductoFinal(pf)) {
                    this.stock.stockProductosFinales();
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
                this.stock.stockProductosFinales();
            } else {
                System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                System.out.println("NO SE PUEDE RESERVAR, NI FABRICAR");
            }
        }
    }

    public boolean stockDisponibleProductoFinal(ProductoFinal pf) {
        if (pf.getStock() > 0) {
            int cantidad = pf.getStock() - 1;
            pf.setStock(cantidad);
            System.out.println("SE RESERVO (" + pf.getNombre() + ") EXITOSAMENTE");
            return true;
        } else
            return false;
    }

    public void reservarElementos(ProductoFinal pf) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL PRODUCTO FINAL(" + pf.getNombre() + ")");
        Set<Componente> componentes = pf.getListaComponentes();
        Set<ComponenteCompuesto> componentesCompuestos = pf.getListaComponentesCompuestos();

        if (!componentes.isEmpty()) {
            componentes.forEach(c -> {
                if (reservarComponente(c)) {
                    this.stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!componentesCompuestos.isEmpty()) {
            componentesCompuestos.forEach(cc -> {
                if (reservarComponenteCompuesto(cc)) {
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
                int cantidad = c.getStock() - c.getCantElementosConstruccion();
                c.setEstado(true);
                c.setStock(cantidad);
                System.out.println("SE RESERVO (" + c.getNombre() + ") EXITOSAMENTE");
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
        System.out.println("A CONTINUACION SE FABRICARA EL COMPONENTE(" + c.getNombre() + ")");
        c.setStock(c.getCantElementosConstruccion());
        this.stock.componentes.add(c);
        this.stock.stockComponentes();
        reservarComponente(c);
    }

    public boolean reservarComponenteCompuesto(ComponenteCompuesto cc) {
        if (this.stock.componentesCompuestos.contains(cc)) {
            if (cc.getStock() >= cc.getCantElementosConstruccion()) {
                int cantidad = cc.getStock() - cc.getCantElementosConstruccion();
                cc.setEstado(true);
                cc.setStock(cantidad);
                System.out.println("SE RESERVO (" + cc.getNombre() + ") EXITOSAMENTE");
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
                    this.stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!subComponenteCompuestos.isEmpty()) {
            subComponenteCompuestos.forEach(scc -> {
                if (reservarSubComponenteComponente(scc)) {
                    this.stock.stockSubComponentesCompuestos();
                } else
                    fabricarSubComponenteCompuesto(scc);
            });
        } else
            System.out.println("LISTA DE SUBCOMPONENTES COMPUESTOS VACIA");


       /* int total = 0;
        if (!componentes.isEmpty()) {
            for (Componente componente : componentes) {
                if (reservarComponente(componente))
                    total++;
            }
        }
        if (!subComponenteCompuestos.isEmpty()) {
            for (SubComponenteCompuesto scc : subComponenteCompuestos) {
                if (reservarSubComponenteComponente(scc))
                    total++;
            }
        }

        if (total == (componentes.size() + subComponenteCompuestos.size())){
            System.out.println("ACTUALMENTE SE CONSTRUYERON Y RESERVARON LAS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL" +
                    "COMPONENTE COMPUESTO(" + cc.getNombre() + ")");
        }*/
    }

    public boolean reservarSubComponenteComponente(SubComponenteCompuesto scc) {
        if (this.stock.subComponentesCompuestos.contains(scc)) {
            if (scc.getStock() >= scc.getCantElementosConstruccion()) {
                int cantidad = scc.getStock() - scc.getCantElementosConstruccion();
                scc.setEstado(true);
                scc.setStock(cantidad);
                System.out.println("SE RESERVO (" + scc.getNombre() + ") EXITOSAMENTE");
                return true;
            } else {
                System.out.println("EL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockSubComponentesCompuestos();
                return false;
            }
        } else {
            System.out.println("EL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            this.stock.stockSubComponentesCompuestos();
            return false;
        }
    }

    public void fabricarSubComponenteCompuesto(SubComponenteCompuesto scc) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
        Set<MateriaPrima> materiasPrimas = scc.getListaMateriasPrimas();

        if (!materiasPrimas.isEmpty()) {
            materiasPrimas.forEach(mp -> {
                if (reservarMateriaPrima(mp, scc.getCantElementosConstruccion())) {
                    this.stock.stockMateriasPrimas();
                } else {
                    comprarMateriaPrima(mp, scc.getCantElementosConstruccion());
                }
            });
        } else
            System.out.println("LISTA DE MATERIAS PRIMAS VACIA");

        if (!materiasPrimas.isEmpty()) {
            if (validarFabricacionSubComponenteCompuesto(materiasPrimas)) {
                System.out.println("ACTUALMENTE SE RESERVARON LAS MATERIAS PRIMAS NECESARIAS PARA LA CONSTRUCCION DEL" +
                        " SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
                scc.setStock(scc.getCantElementosConstruccion());
                scc.setEstado(true);
                this.stock.subComponentesCompuestos.add(scc);
                this.stock.stockSubComponentesCompuestos();
            } else
                System.out.println("OCURRIO UN ERROR! validarFabricacionSubComponenteCompuesto");
        }

    }

    public boolean validarFabricacionSubComponenteCompuesto(Set<MateriaPrima> materiasPrimas) {
        consultarEstado();
        int total = 0;

        if (!materiasPrimas.isEmpty()) {
            for (MateriaPrima materiaPrima : materiasPrimas) {
                if (materiaPrima.isEstado() == true) {
                    total++;
                    materiaPrima.setEstado(false);
                }
            }
        }

        consultarEstado();
        if (total == materiasPrimas.size()) {
           return true;
        } else
            return false;
    }

    public boolean reservarMateriaPrima(MateriaPrima mp, int cantidad) {
        if (this.stock.materiaPrimas.contains(mp)) {
            if (mp.getStock() >= mp.getCantElementosConstruccion() * cantidad) {
                int valorStock = mp.getStock() - (mp.getCantElementosConstruccion() * cantidad);
                mp.setEstado(true);
                mp.setStock(valorStock);
                System.out.println("SE RESERVO (" + mp.getNombre() + ") EXITOSAMENTE");
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
        mp.setStock(mp.getCantElementosConstruccion() * cantidad);
        this.stock.materiaPrimas.add(mp);
        this.stock.stockMateriasPrimas();
        reservarMateriaPrima(mp, cantidad);
    }

    public void agregarMateriaPrima(MateriaPrima mp, int cantidad) {
        mp.setStock(cantidad);
        this.stock.materiaPrimas.add(mp);
        System.out.println("SE AGREGO LA MATERIA PRIMA(" + mp.getNombre() + ")");
        this.stock.stockMateriasPrimas();
    }

    public void agregarComponente(Componente c, int cantidad) {
        c.setStock(cantidad);
        this.stock.componentes.add(c);
        System.out.println("SE AGREGO EL COMPONENTE(" + c.getNombre() + ")");
        this.stock.stockComponentes();
    }

    public void agregarComponenteCompuesto(ComponenteCompuesto cc, int cantidad) {
        cc.setStock(cantidad);
        this.stock.componentesCompuestos.add(cc);
        System.out.println("SE AGREGO EL COMPONENTE COMPUESTO(" + cc.getNombre() + ")");
        this.stock.stockComponentesCompuestos();
    }

    public void agregarSubComponenteCompuesto(SubComponenteCompuesto scc, int cantidad) {
        scc.setStock(cantidad);
        this.stock.subComponentesCompuestos.add(scc);
        System.out.println("SE AGREGO EL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
        this.stock.stockSubComponentesCompuestos();
    }

    public void consultarEstado() {
        System.out.println("ESTADOS DEL STOCK:");
        this.stock.materiaPrimas.forEach(mp -> {
            System.out.println("MATERIA PRIMA(" + mp.getNombre() + ") | ESTADO: " + mp.isEstado());
        });
        this.stock.componentes.forEach(c -> {
            System.out.println("COMPONENTE(" + c.getNombre() + ") | ESTADO: " + c.isEstado());
        });
        this.stock.componentesCompuestos.forEach(cc -> {
            System.out.println("COMPONENTE COMPUESTO(" + cc.getNombre() + ") | ESTADO: " + cc.isEstado());
        });
        this.stock.subComponentesCompuestos.forEach(scc -> {
            System.out.println("SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ") | ESTADO: " + scc.isEstado());
            ;
        });
    }

    public void mostrarStockTotal() {
        stock.stockTotal();
    }
}
