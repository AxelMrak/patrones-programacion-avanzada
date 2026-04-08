package billing;

import billing.FiscalDocument;
import billing.InvoiceA;
import billing.InvoiceB;

public class FiscalDocumentFactory {

  public FiscalDocument createFiscalDocument(String documentType) {

    String cleanedDocumentType = documentType.trim().toUpperCase();

    switch (cleanedDocumentType) {
      case "A":
        return new InvoiceA();
      case "B":
        return new InvoiceB();
      default:
        throw new IllegalArgumentException(
            "Tipo de documento fiscal no reconocido: " + documentType
                + ". Por favor, ingrese 'A' para factura A o 'B' para factura B.");
    }

  }

}
