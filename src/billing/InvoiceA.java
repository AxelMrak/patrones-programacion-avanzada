package billing;

import billing.FiscalDocument;

public class InvoiceA implements FiscalDocument {
  @Override
  public void generate(String customerName, double amount) {
    System.out.println("Generando factura A para " + customerName + " por un monto de $" + amount);
    System.out.println("Al ser factura de tipo A, el IVA se aplica como credito fiscal. Siendo el monto total a pagar $"
        + (amount * 1.21));
  }
}
