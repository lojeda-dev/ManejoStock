# Proyecto de Simulación para Prueba Técnica: Manejo de Stock

Este proyecto simula un sistema de gestión de stock para una empresa que maneja productos finales, componentes y materias primas. La empresa debe reservar productos finales, gestionar la disponibilidad de componentes (fabricándolos si es necesario) y comprar materias primas.

## Objetivos del Proyecto

### Primera Parte
1. Reservar un producto final del stock.
2. En caso de que el producto final no exista en el stock, reservar y/o fabricar todos los componentes necesarios.
3. Clarificar qué componentes se reservaron y cuáles se fabricaron.

### Segunda Parte
- No construir ni reservar nada si no hay stock del producto final o de alguno de sus componentes necesarios.

## Estructura del Proyecto

### Capas del Proyecto

1. **Capa de Servicio:**
   - **Stock:** Almacena los diferentes tipos de elementos (productos finales, componentes, materias primas).
   - **Fabrica:** Gestiona toda la lógica relacionada con el manejo del stock.

2. **Capa de Modelos:**
   - Contiene los objetos que representan los componentes del sistema:
     - `Componente`
     - `ComponenteCompuesto`
     - `MateriaPrima`
     - `ProductoFinal`
     - `SubComponenteCompuesto`
   - También incluye la interfaz `IBuilder` con el método `build`, implementado por los objetos mencionados anteriormente.

3. **Capa Cliente:**
   - **Empresa:** Contiene las llamadas a los casos de uso definidos en el enunciado.
   - Implementa métodos para realizar las acciones necesarias, como reservar productos finales y gestionar componentes.

## Mejoras Pendientes

- Implementación de excepciones para manejar errores de manera adecuada.
- Factorización y desacoplamiento de la lógica de la clase `Fabrica` para eliminar redundancias y mejorar la mantenibilidad del código.
- Exploración y utilización de genéricos para generalizar y reutilizar la lógica común entre diferentes tipos de datos.

## Detalles Técnicos

- **Lenguaje:** Java 17
- **IDE:** IntelliJ IDEA
