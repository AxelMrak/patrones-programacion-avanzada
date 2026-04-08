package billing;

import billing.FiscalDocument;

public class InvoiceB implements FiscalDocument {
  @Override
  public void generate(String customerName, double amount) {
    System.out.println("Generando factura B para " + customerName + " por un monto de $" + amount
        + " al ser factura de tipo B, el IVA no aplica como credito fiscal.");
  }
}
