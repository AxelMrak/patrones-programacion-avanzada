package billing;

import billing.DocumentType;
import billing.FiscalDocument;
import billing.InvoiceA;
import billing.InvoiceB;

public class FiscalDocumentFactory {

  public FiscalDocument createFiscalDocument(DocumentType documentType) {
    boolean isDocumentTypeNull = documentType == null;
    if (isDocumentTypeNull) {
      throw new IllegalArgumentException(
          "El tipo de documento fiscal no puede ser nulo o vacío.");
    }

    switch (documentType) {
      case INVOICE_A:
        return new InvoiceA();
      case INVOICE_B:
        return new InvoiceB();
      default:
        throw new IllegalArgumentException("Tipo de documento fiscal no reconocido");
    }
  }
}
