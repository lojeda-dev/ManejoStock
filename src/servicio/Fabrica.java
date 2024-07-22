package servicio;

import modelos.*;

import java.util.IllegalFormatCodePointException;
import java.util.Set;

public class Fabrica {
    Stock stock;

    public Fabrica() {
        this.stock = new Stock();
    }

    /**
     * Añade un producto final al stock y actualiza el estado del stock.
     *
     * @param pf El producto final que se va a añadir.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Añade el producto final a la lista de productos finales en el stock.</li>
     *   <li>Muestra un mensaje indicando que el producto final ha sido añadido, incluyendo su nombre.</li>
     *   <li>Actualiza el stock de productos finales.</li>
     *   <li>Consulta y muestra el estado total del stock, incluyendo las reservas de los diferentes elementos.</li>
     * </ul>
     */
    public void cargarProductoFinal(ProductoFinal pf) {
        stock.productosFinales.add(pf);
        System.out.println("SE AGREGO EL PRODUCTO FINAL(" + pf.getNombre() + ")");
        stock.stockProductosFinales();
        stock.consultarEstadoTotal();
    }

    /**
     * Reserva un producto final basándose en la opción proporcionada.
     *
     * @param pf El producto final que se va a reservar.
     * @param opcion La opción para la reserva del producto final.
     *               <ul>
     *                 <li>Si la opción es 1, intenta reservar o fabricar el producto final si no hay stock.</li>
     *                 <li>Si la opción es 2, simplemente verifica y reserva el stock disponible.</li>
     *                 <li>Cualquier otra opción se considera inválida y muestra un mensaje de opción inválida.</li>
     *               </ul>
     *
     * <p>Este método realiza las siguientes acciones según la opción proporcionada:</p>
     * <ul>
     *   <li>Si la opción es 1:
     *     <ul>
     *       <li>Verifica si el producto final existe en el stock.</li>
     *       <li>Si el producto existe, intenta reservarlo. Si no hay stock disponible, intenta fabricar los componentes necesarios.</li>
     *       <li>Si el producto no existe en el stock, intenta fabricar los componentes necesarios para su fabricación.</li>
     *     </ul>
     *   </li>
     *   <li>Si la opción es 2:
     *     <ul>
     *       <li>Verifica si hay stock disponible para el producto final.</li>
     *       <li>Si hay stock disponible, lo reserva. Si no hay stock, muestra un mensaje indicando que no se puede reservar ni fabricar.</li>
     *     </ul>
     *   </li>
     *   <li>Si la opción no es ni 1 ni 2:
     *     <ul>
     *       <li>Muestra un mensaje indicando que la opción es inválida.</li>
     *     </ul>
     *   </li>
     * </ul>
     */

    public void reservarProductoFinal(ProductoFinal pf, int opcion) {
        if (opcion == 1) {
            if (stock.productosFinales.contains(pf)) {
                if (stockDisponibleProductoFinal(pf)) {
                    stock.stockProductosFinales();
                    pf.setEstado(false);
                } else {
                    System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                    System.out.println();
                    reservarElementos(pf);
                }
            } else {
                System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
                System.out.println();
                reservarElementos(pf);
            }
        } else if (opcion == 2){
            if (stockDisponibleProductoFinal(pf)) {
                stock.stockProductosFinales();
            } else {
                System.out.println("EL PRODUCTO FINAL(" + pf.getNombre() + ") NO POSEE STOCK");
                System.out.println("NO SE PUEDE RESERVAR, NI FABRICAR");
            }
        } else
            System.out.println("OPCION INVALIDA");
    }

    /**
     * Verifica si hay stock disponible de un producto final y, en caso afirmativo,
     * reserva una unidad del mismo y actualiza su estado.
     *
     * @param pf El producto final que se va a verificar y reservar.
     * @return {@code true} si se pudo reservar una unidad del producto final, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si el stock del producto final es mayor que 0.</li>
     *   <li>Si hay stock disponible, disminuye el stock del producto final en una unidad.</li>
     *   <li>Actualiza el estado del producto final a reservado (true).</li>
     *   <li>Muestra un mensaje indicando que el producto final ha sido reservado exitosamente, incluyendo su nombre.</li>
     *   <li>Actualiza y muestra el estado total del stock.</li>
     *   <li>Retorna {@code true} si la reserva fue exitosa.</li>
     *   <li>Retorna {@code false} si no hay stock disponible.</li>
     * </ul>
     */
    public boolean stockDisponibleProductoFinal(ProductoFinal pf) {
        if (pf.getStock() > 0) {
            int cantidad = pf.getStock() - 1;
            pf.setStock(cantidad);
            pf.setEstado(true);
            System.out.println("SE RESERVO (" + pf.getNombre() + ") EXITOSAMENTE");
            stock.consultarEstadoTotal();
            return true;
        } else
            return false;
    }

    /**
     * Reserva los elementos necesarios para la construcción de un producto final.
     *
     * @param pf El producto final para el cual se van a reservar los elementos.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Muestra un mensaje indicando que se evaluará el stock de los elementos necesarios para construir el producto final.</li>
     *   <li>Verifica y reserva los componentes simples del producto final. Si un componente no está disponible, se intenta fabricar.</li>
     *   <li>Verifica y reserva los componentes compuestos del producto final. Si un componente compuesto no está disponible, se intenta fabricar.</li>
     *   <li>Si ambos tipos de componentes están presentes y se pueden reservar o fabricar, se actualiza el stock del producto final y se muestra un mensaje de éxito.</li>
     *   <li>Si ocurre un error en el proceso de fabricación del producto final, se muestra un mensaje de error.</li>
     * </ul>
     *
     * <p>Este método depende de los siguientes métodos auxiliares:</p>
     * <ul>
     *   <li>{@link #reservarComponente(Componente)}: Reserva un componente simple.</li>
     *   <li>{@link #fabricarComponente(Componente)}: Fabrica un componente simple.</li>
     *   <li>{@link #reservarComponenteCompuesto(ComponenteCompuesto)}: Reserva un componente compuesto.</li>
     *   <li>{@link #fabricarComponenteCompuesto(ComponenteCompuesto)}: Fabrica un componente compuesto.</li>
     *   <li>{@link #validarFabricacionProductoFinal(ProductoFinal)}: Valida la fabricación del producto final.</li>
     *   <li>{@link #reservarProductoFinal(ProductoFinal, int)}: Reserva la cantidad especificada de un producto final.</li>
     * </ul>
     */

    public void reservarElementos(ProductoFinal pf) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL PRODUCTO FINAL(" + pf.getNombre() + ")");
        System.out.println();

        if (!pf.getListaComponentes().isEmpty()) {
            pf.getListaComponentes().forEach(c -> {
                if (reservarComponente(c)) {
                    stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!pf.getListaComponentesCompuestos().isEmpty()) {
            pf.getListaComponentesCompuestos().forEach(cc -> {
                if (reservarComponenteCompuesto(cc)) {
                    stock.stockComponentesCompuestos();
                } else
                    fabricarComponenteCompuesto(cc);
            });
        } else
            System.out.println("LISTA DE COMPONENTES COMPUESTOS VACIA");

        if (validarFabricacionProductoFinal(pf) == true) {
            System.out.println("ACTUALMENTE SE RESERVARON LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL" +
                    " PRODUCTO FINAL(" + pf.getNombre() + ")");
            pf.setStock(1);
            stock.stockProductosFinales();
            reservarProductoFinal(pf, 1);
        } else
            System.out.println("OCURRIO UN ERROR AL FABRICAR EL PRODUCTO FINAL!");
    }

    /**
     * Valida si se pueden reservar todos los componentes necesarios para la fabricación de un producto final.
     *
     * @param pf El producto final para el cual se va a validar la fabricación.
     * @return {@code true} si todos los componentes necesarios están reservados y disponibles para la fabricación, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Inicializa un contador para el total de componentes reservados.</li>
     *   <li>Verifica los componentes simples y, si están reservados (estado es {@code true}), incrementa el contador y cambia su estado a no reservado (estado es {@code false}).</li>
     *   <li>Verifica los componentes compuestos y, si están reservados (estado es {@code true}), incrementa el contador y cambia su estado a no reservado (estado es {@code false}).</li>
     *   <li>Comprueba si todos los componentes (simples y compuestos) necesarios para la fabricación están reservados.</li>
     *   <li>Retorna {@code true} si todos los componentes necesarios están reservados; de lo contrario, retorna {@code false}.</li>
     * </ul>
     */

    public boolean validarFabricacionProductoFinal(ProductoFinal pf) {
        int total = 0;

        boolean condicionComponentes = false;
        if (!pf.getListaComponentes().isEmpty()) {
            for (Componente c : pf.getListaComponentes()) {
                if (c.isEstado() == true) {
                    total++;
                    c.setEstado(false);
                }
            }
            condicionComponentes = true;
        }

        boolean condicionComponenteCompuesto = false;
        if (!pf.getListaComponentesCompuestos().isEmpty()) {
            for (ComponenteCompuesto cc : pf.getListaComponentesCompuestos()) {
                if (cc.isEstado() == true) {
                    total++;
                    cc.setEstado(false);
                }
            }
            condicionComponenteCompuesto = true;
        }

        boolean fabricar = false;
        if (condicionComponentes == true && condicionComponenteCompuesto == true) {
            if (total == pf.getListaComponentes().size() + pf.getListaComponentesCompuestos().size())
                fabricar = true;
        } else if (condicionComponentes == true && condicionComponenteCompuesto == false) {
            if (total == pf.getListaComponentes().size())
                fabricar = true;
        } else if (condicionComponentes == false && condicionComponenteCompuesto == true) {
            if (total == pf.getListaComponentesCompuestos().size())
                fabricar = true;
        }

        if (fabricar == true) {
            return true;
        } else
            return false;
    }

    /**
     * Reserva un componente verificando si hay suficiente stock disponible.
     *
     * @param c El componente que se va a reservar.
     * @return {@code true} si el componente se reservó exitosamente, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si el componente existe en el stock.</li>
     *   <li>Si el componente existe y su stock es suficiente para cubrir la cantidad necesaria para la construcción:
     *     <ul>
     *       <li>Disminuye el stock del componente por la cantidad necesaria para la construcción.</li>
     *       <li>Actualiza el estado del componente a reservado (estado es {@code true}).</li>
     *       <li>Muestra un mensaje indicando que el componente se reservó exitosamente.</li>
     *       <li>Actualiza y muestra el estado total del stock.</li>
     *       <li>Retorna {@code true}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el componente no tiene suficiente stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el componente no tiene el stock suficiente.</li>
     *       <li>Actualiza y muestra el stock de componentes.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el componente no existe en el stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el componente no existe en el stock.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     * </ul>
     */

    public boolean reservarComponente(Componente c) {
        if (this.stock.componentes.contains(c)) {
            if (c.getStock() >= c.getCantElementosConstruccion()) {
                int cantidad = c.getStock() - c.getCantElementosConstruccion();
                c.setEstado(true);
                c.setStock(cantidad);
                System.out.println("SE RESERVO (" + c.getNombre() + ") EXITOSAMENTE");
                this.stock.consultarEstadoTotal();
                return true;
            } else {
                System.out.println("EL COMPONENTE(" + c.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockComponentes();
                return false;
            }
        } else {
            System.out.println("EL COMPONENTE(" + c.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            return false;
        }
    }

    /**
     * Fabrica un componente y lo agrega al stock.
     *
     * @param c El componente que se va a fabricar.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Muestra un mensaje indicando que se va a fabricar el componente.</li>
     *   <li>Establece el stock del componente a la cantidad necesaria para su construcción.</li>
     *   <li>Agrega el componente al stock de componentes.</li>
     *   <li>Actualiza y muestra el estado del stock de componentes.</li>
     *   <li>Intenta reservar el componente fabricado llamando al método {@link #reservarComponente(Componente)}.</li>
     * </ul>
     */

    public void fabricarComponente(Componente c) {
        System.out.println("A CONTINUACION SE FABRICARA EL COMPONENTE(" + c.getNombre() + ")");
        c.setStock(c.getCantElementosConstruccion());
        this.stock.componentes.add(c);
        this.stock.stockComponentes();
        reservarComponente(c);
    }

    /**
     * Reserva un componente compuesto verificando si hay suficiente stock disponible.
     *
     * @param cc El componente compuesto que se va a reservar.
     * @return {@code true} si el componente compuesto se reservó exitosamente, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si el componente compuesto existe en el stock.</li>
     *   <li>Si el componente compuesto existe y su stock es suficiente para cubrir la cantidad necesaria para la construcción:
     *     <ul>
     *       <li>Disminuye el stock del componente compuesto por la cantidad necesaria para la construcción.</li>
     *       <li>Actualiza el estado del componente compuesto a reservado (estado es {@code true}).</li>
     *       <li>Muestra un mensaje indicando que el componente compuesto se reservó exitosamente.</li>
     *       <li>Actualiza y muestra el estado total del stock.</li>
     *       <li>Retorna {@code true}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el componente compuesto no tiene suficiente stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el componente compuesto no tiene el stock suficiente.</li>
     *       <li>Actualiza y muestra el stock de componentes compuestos.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el componente compuesto no existe en el stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el componente compuesto no existe en el stock.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     * </ul>
     */

    public boolean reservarComponenteCompuesto(ComponenteCompuesto cc) {
        if (this.stock.componentesCompuestos.contains(cc)) {
            if (cc.getStock() >= cc.getCantElementosConstruccion()) {
                int cantidad = cc.getStock() - cc.getCantElementosConstruccion();
                cc.setEstado(true);
                cc.setStock(cantidad);
                System.out.println("SE RESERVO (" + cc.getNombre() + ") EXITOSAMENTE");
                this.stock.consultarEstadoTotal();
                return true;
            } else {
                System.out.println("EL COMPONENTE COMPUESTO(" + cc.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockComponentesCompuestos();
                return false;
            }
        } else {
            System.out.println("EL COMPONENTE COMPUESTO(" + cc.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            return false;
        }
    }

    /**
     * Fabrica un componente compuesto evaluando el stock de los elementos necesarios.
     *
     * @param cc El componente compuesto que se desea fabricar.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si hay componentes necesarios en el stock para la construcción del componente compuesto.</li>
     *   <li>Para cada componente:
     *     <ul>
     *       <li>Si el componente puede ser reservado, actualiza el stock.</li>
     *       <li>Si el componente no puede ser reservado, lo fabrica.</li>
     *     </ul>
     *   </li>
     *   <li>Verifica si hay subcomponentes compuestos necesarios en el stock para la construcción del componente compuesto.</li>
     *   <li>Para cada subcomponente compuesto:
     *     <ul>
     *       <li>Si el subcomponente compuesto puede ser reservado, actualiza el stock.</li>
     *       <li>Si el subcomponente compuesto no puede ser reservado, lo fabrica.</li>
     *     </ul>
     *   </li>
     *   <li>Valida la fabricación del componente compuesto:</li>
     *   <ul>
     *     <li>Si la validación es exitosa:
     *       <ul>
     *         <li>Actualiza el stock del componente compuesto.</li>
     *         <li>Agrega el componente compuesto al stock.</li>
     *         <li>Actualiza el stock total de componentes compuestos.</li>
     *         <li>Reserva el componente compuesto.</li>
     *       </ul>
     *     </li>
     *     <li>Si la validación no es exitosa, muestra un mensaje de error.</li>
     *   </ul>
     * </ul>
     */

    public void fabricarComponenteCompuesto(ComponenteCompuesto cc) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL COMPONENTE COMPUESTO(" + cc.getNombre() + ")");
        System.out.println();

        if (!cc.getListaComponentes().isEmpty()) {
            cc.getListaComponentes().forEach(c -> {
                if (reservarComponente(c)) {
                    this.stock.stockComponentes();
                } else
                    fabricarComponente(c);
            });
        } else
            System.out.println("LISTA DE COMPONENTES VACIA");

        if (!cc.getListaSubComponentesCompuestos().isEmpty()) {
            cc.getListaSubComponentesCompuestos().forEach(scc -> {
                if (reservarSubComponenteComponente(scc)) {
                    this.stock.stockSubComponentesCompuestos();
                } else
                    fabricarSubComponenteCompuesto(scc);
            });
        } else
            System.out.println("LISTA DE SUBCOMPONENTES COMPUESTOS VACIA");

        if (validarFabricacionComponenteCompuesto(cc) == true) {
            System.out.println("ACTUALMENTE SE RESERVARON LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL " +
                    "COMPONENTE COMPUESTO(" + cc.getNombre() + ")");
            cc.setStock(cc.getCantElementosConstruccion());
            this.stock.componentesCompuestos.add(cc);
            this.stock.stockComponentesCompuestos();
            reservarComponenteCompuesto(cc);
        } else
            System.out.println("OCURRIO UN ERROR AL FABRICAR EL COMPONENTE COMPUESTO");
    }

    /**
     * Valida la fabricación de un componente compuesto verificando el estado de sus componentes y subcomponentes.
     *
     * @param cc El componente compuesto cuya fabricación se va a validar.
     * @return {@code true} si todos los componentes y subcomponentes necesarios están disponibles y en estado válido, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Inicializa una variable {@code total} para contar los componentes y subcomponentes válidos.</li>
     *   <li>Verifica si la lista de componentes no está vacía:</li>
     *   <ul>
     *     <li>Para cada componente:
     *       <ul>
     *         <li>Si el componente está en estado válido ({@code true}), incrementa {@code total} y cambia su estado a no válido ({@code false}).</li>
     *       </ul>
     *     </li>
     *     <li>Actualiza la variable {@code condicionComponentes} a {@code true}.</li>
     *   </ul>
     *   <li>Verifica si la lista de subcomponentes compuestos no está vacía:</li>
     *   <ul>
     *     <li>Para cada subcomponente compuesto:
     *       <ul>
     *         <li>Si el subcomponente compuesto está en estado válido ({@code true}), incrementa {@code total} y cambia su estado a no válido ({@code false}).</li>
     *       </ul>
     *     </li>
     *     <li>Actualiza la variable {@code condicionSubComponentes} a {@code true}.</li>
     *   </ul>
     *   <li>Determina si se puede fabricar el componente compuesto basado en las siguientes condiciones:</li>
     *   <ul>
     *     <li>Si ambas condiciones ({@code condicionComponentes} y {@code condicionSubComponentes}) son verdaderas, verifica si {@code total} es igual al tamaño combinado de las listas de componentes y subcomponentes compuestos.</li>
     *     <li>Si solo {@code condicionComponentes} es verdadera, verifica si {@code total} es igual al tamaño de la lista de componentes.</li>
     *     <li>Si solo {@code condicionSubComponentes} es verdadera, verifica si {@code total} es igual al tamaño de la lista de subcomponentes compuestos.</li>
     *   </ul>
     *   <li>Si alguna de las condiciones anteriores es verdadera, retorna {@code true}; de lo contrario, retorna {@code false}.</li>
     * </ul>
     */

    public boolean validarFabricacionComponenteCompuesto(ComponenteCompuesto cc) {
        int total = 0;

        boolean condicionComponentes = false;
        if (!cc.getListaComponentes().isEmpty()) {
            for (Componente c : cc.getListaComponentes()) {
                if (c.isEstado() == true) {
                    total++;
                    c.setEstado(false);
                }
            }
            condicionComponentes = true;
        }

        boolean condicionSubComponentes = false;
        if (!cc.getListaSubComponentesCompuestos().isEmpty()) {
            for (SubComponenteCompuesto scc : cc.getListaSubComponentesCompuestos()) {
                if (scc.isEstado() == true) {
                    total++;
                    scc.setEstado(false);
                }
            }
            condicionSubComponentes = true;
        }

        boolean fabricar = false;
        if (condicionComponentes == true && condicionSubComponentes == true) {
            if (total == cc.getListaComponentes().size() + cc.getListaSubComponentesCompuestos().size())
                fabricar = true;
        } else if (condicionComponentes == true && condicionSubComponentes == false) {
            if (total == cc.getListaComponentes().size())
                fabricar = true;
        } else if (condicionComponentes == false && condicionSubComponentes == true) {
            if (total == cc.getListaSubComponentesCompuestos().size())
                fabricar = true;
        }

        if (fabricar == true) {
            return true;
        } else
            return false;
    }

    /**
     * Reserva un subcomponente compuesto verificando si hay suficiente stock disponible.
     *
     * @param scc El subcomponente compuesto que se va a reservar.
     * @return {@code true} si el subcomponente compuesto se reservó exitosamente, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si el subcomponente compuesto existe en el stock.</li>
     *   <li>Si el subcomponente compuesto existe y su stock es suficiente para cubrir la cantidad necesaria para la construcción:
     *     <ul>
     *       <li>Disminuye el stock del subcomponente compuesto por la cantidad necesaria para la construcción.</li>
     *       <li>Actualiza el estado del subcomponente compuesto a reservado (estado es {@code true}).</li>
     *       <li>Muestra un mensaje indicando que el subcomponente compuesto se reservó exitosamente.</li>
     *       <li>Actualiza y muestra el estado total del stock.</li>
     *       <li>Retorna {@code true}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el subcomponente compuesto no tiene suficiente stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el subcomponente compuesto no tiene el stock suficiente.</li>
     *       <li>Actualiza y muestra el stock de subcomponentes compuestos.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     *   <li>Si el subcomponente compuesto no existe en el stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que el subcomponente compuesto no existe en el stock.</li>
     *       <li>Actualiza y muestra el stock de subcomponentes compuestos.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     * </ul>
     */

    public boolean reservarSubComponenteComponente(SubComponenteCompuesto scc) {
        if (this.stock.subComponentesCompuestos.contains(scc)) {
            if (scc.getStock() >= scc.getCantElementosConstruccion()) {
                int cantidad = scc.getStock() - scc.getCantElementosConstruccion();
                scc.setEstado(true);
                scc.setStock(cantidad);
                System.out.println("SE RESERVO (" + scc.getNombre() + ") EXITOSAMENTE");
                this.stock.consultarEstadoTotal();
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

    /**
     * Fabrica un subcomponente compuesto evaluando el stock de las materias primas necesarias.
     *
     * @param scc El subcomponente compuesto que se desea fabricar.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Imprime un mensaje indicando que se evaluará el stock de las materias primas necesarias para la construcción del subcomponente compuesto.</li>
     *   <li>Verifica si hay materias primas necesarias en el stock para la construcción del subcomponente compuesto.</li>
     *   <li>Para cada materia prima:
     *     <ul>
     *       <li>Si la materia prima puede ser reservada, actualiza el stock.</li>
     *       <li>Si la materia prima no puede ser reservada, la compra.</li>
     *     </ul>
     *   </li>
     *   <li>Si la lista de materias primas no está vacía, valida la fabricación del subcomponente compuesto:</li>
     *     <ul>
     *       <li>Si la validación es exitosa:
     *         <ul>
     *           <li>Imprime un mensaje indicando que se reservaron las materias primas necesarias.</li>
     *           <li>Actualiza el stock del subcomponente compuesto.</li>
     *           <li>Actualiza el estado del subcomponente compuesto a reservado (estado es {@code true}).</li>
     *           <li>Agrega el subcomponente compuesto al stock.</li>
     *           <li>Actualiza y muestra el stock total de subcomponentes compuestos.</li>
     *           <li>Reserva el subcomponente compuesto.</li>
     *         </ul>
     *       </li>
     *       <li>Si la validación no es exitosa, muestra un mensaje de error.</li>
     *     </ul>
     *   <li>Si la lista de materias primas está vacía, imprime un mensaje indicando que la lista está vacía.</li>
     * </ul>
     */

    public void fabricarSubComponenteCompuesto(SubComponenteCompuesto scc) {
        System.out.println("A CONTINUACION SE EVALUARA EL STOCK DE LOS ELEMENTOS NECESARIOS PARA LA CONSTRUCCION DEL SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
        System.out.println();

        if (!scc.getListaMateriasPrimas().isEmpty()) {
            scc.getListaMateriasPrimas().forEach(mp -> {
                if (reservarMateriaPrima(mp, scc.getCantElementosConstruccion())) {
                    this.stock.stockMateriasPrimas();
                } else {
                    comprarMateriaPrima(mp, scc.getCantElementosConstruccion());
                }
            });
        } else
            System.out.println("LISTA DE MATERIAS PRIMAS VACIA");

        if (!scc.getListaMateriasPrimas().isEmpty()) {
            if (validarFabricacionSubComponenteCompuesto(scc)) {
                System.out.println("ACTUALMENTE SE RESERVARON LAS MATERIAS PRIMAS NECESARIAS PARA LA CONSTRUCCION DEL" +
                        " SUBCOMPONENTE COMPUESTO(" + scc.getNombre() + ")");
                scc.setStock(scc.getCantElementosConstruccion());
                scc.setEstado(true);
                this.stock.subComponentesCompuestos.add(scc);
                this.stock.stockSubComponentesCompuestos();
                reservarSubComponenteComponente(scc);
            } else
                System.out.println("OCURRIO UN ERROR AL FABRICAR EL SUB COMPONENTE COMPUESTO");
        }
    }

    /**
     * Valida la fabricación de un subcomponente compuesto verificando el estado de sus materias primas.
     *
     * @param scc El subcomponente compuesto cuya fabricación se va a validar.
     * @return {@code true} si todas las materias primas necesarias están disponibles y en estado válido, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Inicializa una variable {@code total} para contar las materias primas válidas.</li>
     *   <li>Verifica si la lista de materias primas no está vacía.</li>
     *   <li>Para cada materia prima en la lista:
     *     <ul>
     *       <li>Si el estado de la materia prima es verdadero ({@code true}), incrementa el contador {@code total} y actualiza el estado de la materia prima a falso ({@code false}).</li>
     *     </ul>
     *   </li>
     *   <li>Compara el contador {@code total} con el tamaño de la lista de materias primas.</li>
     *   <li>Retorna {@code true} si todos los elementos en la lista de materias primas son válidos, {@code false} en caso contrario.</li>
     * </ul>
     */

    public boolean validarFabricacionSubComponenteCompuesto(SubComponenteCompuesto scc) {
        int total = 0;

        if (!scc.getListaMateriasPrimas().isEmpty()) {
            for (MateriaPrima mp : scc.getListaMateriasPrimas()) {
                if (mp.isEstado() == true) {
                    total++;
                    mp.setEstado(false);
                }
            }
        }

        if (total == scc.getListaMateriasPrimas().size()) {
            return true;
        } else
            return false;
    }

    /**
     * Reserva una materia prima verificando si hay suficiente stock disponible.
     *
     * @param mp La materia prima que se va a reservar.
     * @param cantidad La cantidad de elementos necesarios para la construcción.
     * @return {@code true} si la materia prima se reservó exitosamente, {@code false} en caso contrario.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Verifica si la materia prima existe en el stock.</li>
     *   <li>Si la materia prima existe y su stock es suficiente para cubrir la cantidad necesaria para la construcción:
     *     <ul>
     *       <li>Calcula el nuevo valor del stock restando la cantidad necesaria de elementos para la construcción.</li>
     *       <li>Actualiza el estado de la materia prima a reservado (estado es {@code true}).</li>
     *       <li>Actualiza el stock de la materia prima con el nuevo valor.</li>
     *       <li>Muestra un mensaje indicando que la materia prima se reservó exitosamente.</li>
     *       <li>Actualiza y muestra el estado total del stock.</li>
     *       <li>Retorna {@code true}.</li>
     *     </ul>
     *   </li>
     *   <li>Si la materia prima no tiene suficiente stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que la materia prima no tiene el stock suficiente.</li>
     *       <li>Actualiza y muestra el stock de materias primas.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     *   <li>Si la materia prima no existe en el stock:
     *     <ul>
     *       <li>Muestra un mensaje indicando que la materia prima no existe en el stock.</li>
     *       <li>Retorna {@code false}.</li>
     *     </ul>
     *   </li>
     * </ul>
     */

    public boolean reservarMateriaPrima(MateriaPrima mp, int cantidad) {
        if (this.stock.materiaPrimas.contains(mp)) {
            if (mp.getStock() >= mp.getCantElementosConstruccion() * cantidad) {
                int valorStock = mp.getStock() - (mp.getCantElementosConstruccion() * cantidad);
                mp.setEstado(true);
                mp.setStock(valorStock);
                System.out.println("SE RESERVO (" + mp.getNombre() + ") EXITOSAMENTE");
                this.stock.consultarEstadoTotal();
                return true;
            } else {
                System.out.println("LA MATERIA PRIMA(" + mp.getNombre() + ") NO POSEE EL STOCK SUFICIENTE");
                this.stock.stockMateriasPrimas();
                return false;
            }
        } else {
            System.out.println("LA MATERIA PRIMA(" + mp.getNombre() + ") NO EXISTE DENTRO DEL STOCK");
            return false;
        }
    }

    /**
     * Compra una cantidad específica de una materia prima y la reserva.
     *
     * @param mp La materia prima que se va a comprar.
     * @param cantidad La cantidad de elementos de materia prima que se van a comprar.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *   <li>Imprime un mensaje indicando que se va a proceder con la compra de la materia prima especificada.</li>
     *   <li>Calcula el nuevo stock de la materia prima multiplicando la cantidad de elementos necesarios para la construcción.</li>
     *   <li>Agrega la materia prima con el nuevo stock al inventario.</li>
     *   <li>Actualiza y muestra el stock total de materias primas.</li>
     *   <li>Reserva la materia prima recién comprada.</li>
     * </ul>
     */

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

    //Muestra el estado actual de cada uno de los elementos dentro del stock
    public void consultarEstado() {
        this.stock.consultarEstadoTotal();
    }

    public void mostrarStockTotal() {
        stock.stockTotal();
    }
}
