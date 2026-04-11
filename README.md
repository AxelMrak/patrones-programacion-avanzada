# Patrones de Diseño en Java

- Autor: Axel Sarmiento Mrak
- Legajo: 114759
- Fecha: 11-04-2026
- Asignatura: Programación Avanzada

Demostración de uso de patrones de diseño en Java. La consigna requiere del uso de, al menos, 3 patrones de diseño diferentes cada uno perteneciente a la categoria de Creacion, Estructura o de Comportamiento.

## Patrones de Diseño Utilizados

### 1. **Patrón de comportamiento: *Strategy***
Usamos este patrón para aplicar descuentos según el tipo de cliente.

Cada tipo de cliente utiliza una estrategia distinta para calcular el descuento final.

A nivel teórico, *Strategy* permite definir una familia de algoritmos, encapsular cada uno en una clase independiente y hacerlos intercambiables en tiempo de ejecución. En este proyecto, cada estrategia de descuento implementa una interfaz común, por lo que el sistema puede cambiar el comportamiento sin modificar la lógica central de venta.

- `./src/discount/DiscountStrategy.java` define la **interfaz *Strategy*** (contrato común).
- `./src/discount/IndividualDiscountStrategy.java` y `./src/discount/WholesaleDiscountStrategy.java` son las **Estrategias concretas**.
- `./src/service/SaleService.java` actúa como **Context**, porque utiliza la estrategia a través de una abstracción y no de una implementación concreta.
- `./src/Main.java` actúa como **Cliente**, ya que selecciona e inyecta la estrategia a utilizar.

> **Aclaración:** En una primera versión, la diferencia entre estrategias era solo el porcentaje. Luego se ajustó `IndividualDiscountStrategy` para agregar un tope de descuento, lo que hace más explícita la diferencia de comportamiento entre algoritmos. También se renombró `CashDiscountStrategy` a `IndividualDiscountStrategy` para reflejar mejor el dominio de esa estrategia.

**Ventajas de usar *Strategy* en este caso:**
- Separa la lógica de descuentos del flujo principal de ventas.
- Reduce el acoplamiento entre `SaleService` y los algoritmos concretos.
- Mejora legibilidad y mantenibilidad.
- Permite agregar/modificar estrategias con bajo impacto en el resto del sistema (**OCP** o principio de **Abierto-Cerrado**).
- Reduce el riesgo de concentrar demasiada lógica condicional en una sola clase (evitando una posible GOD class).

---

### 2. **Patrón estructural: *Adapter***

Usamos este patrón para integrar APIs o sistemas de pago distintos.

Como podemos trabajar con proveedores diferentes, cada uno con su propia lógica e interfaz, necesitamos un adaptador/intermediario que asegure la compatibilidad con nuestro sistema.

Por eso, *Adapter* encaja muy bien en este caso: permite usar objetos con interfaces diferentes bajo una interfaz común para el sistema.

- `./src/payment/PaymentProcessor.java` define la **interfaz común** para procesar pagos.
- `./src/payment/MercadoPagoAdapter.java` y `./src/payment/CashPaymentAdapter.java` son los **Adaptadores concretos**, que implementan la interfaz común y adaptan la lógica de cada sistema de pago.
- `./src/service/SaleService.java` actúa como **Cliente**, ya que procesa pagos usando la interfaz común sin depender de detalles de cada proveedor.

**Ventajas de usar *Adapter* en este caso:**

- Permite integrar sistemas de pago diferentes sin romper el flujo principal.
- Reduce el acoplamiento entre la lógica de venta y APIs externas.
- Facilita reemplazar o agregar proveedores de pago con cambios acotados.

---

### 3. **Patrón creacional: *Simple Factory***

Usamos este patrón para crear objetos de facturación.
Dependiendo del tipo de cliente/documento, se genera una factura diferente. La idea es encapsular la creación y delegarla a una clase especializada.

- `./src/billing/FiscalDocumentFactory.java` define la **fábrica** que crea instancias de `FiscalDocument` según el tipo de documento.
- `./src/billing/DocumentType.java` define el enum con los tipos de documentos.
- `./src/billing/FiscalDocument.java` define el **contrato abstracto** del documento fiscal.
- `./src/billing/InvoiceA.java` y `./src/billing/InvoiceB.java` son las **implementaciones concretas**.

> **Aclaración:** Esto no corresponde al Factory Method clásico de GoF, sino a una implementación más simple conocida como ***Simple Factory***. La creación se centraliza en una única clase con una decisión por tipo (`enum`), en lugar de usar una jerarquía de fábricas basada en polimorfismo.

**Ventajas del uso de *Simple Factory* en este caso:**

- Centraliza la lógica de creación de documentos.
- Evita tener `new InvoiceA()` / `new InvoiceB()` dispersos en varias clases.
- Reduce el acoplamiento del cliente con las clases concretas, ya que se trabaja contra `FiscalDocument`.
- Es una solución simple y adecuada para un escenario didáctico como este.

**Desventajas de *Simple Factory* en este caso:**
- Al agregar un nuevo tipo de factura, hay que modificar `FiscalDocumentFactory` y `DocumentType`. Por eso, no cumple OCP de forma estricta como sí lo haría un Factory Method GoF puro.
- Aun así, en este proyecto el impacto es bajo y manejable porque la cantidad de tipos es acotada.
