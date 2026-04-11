package service;

import billing.FiscalDocument;
import billing.FiscalDocumentFactory;
import discount.DiscountStrategy;
import model.Sale;
import payment.PaymentProcessor;

import java.util.Objects;

import billing.DocumentType;

public class SaleService {
  private final FiscalDocumentFactory fiscalDocumentFactory;

  public SaleService(FiscalDocumentFactory fiscalDocumentFactory) {
    this.fiscalDocumentFactory = Objects.requireNonNull(fiscalDocumentFactory,
        "El FiscalDocumentFactory no puede ser nulo");
  }

  public void checkout(Sale sale, DiscountStrategy discountStrategy, PaymentProcessor paymentProcessor,
      DocumentType documentType) {

    // GUARDS
    Objects.requireNonNull(sale, "La venta no puede ser nula");
    Objects.requireNonNull(discountStrategy, "La estrategia de descuento no puede ser nula");
    Objects.requireNonNull(paymentProcessor, "El procesador de pagos no puede ser nulo ");
    Objects.requireNonNull(documentType, "El tipo de documento no puede ser nulo");

    String customerName = sale.getCustomer().getName();
    double originalAmount = sale.getOriginalAmount();
    FiscalDocument fiscalDocument = this.fiscalDocumentFactory.createFiscalDocument(documentType);
    double finalAmount = discountStrategy.applyDiscount(originalAmount);

    sale.setFinalAmount(finalAmount);
    paymentProcessor.processPayment(finalAmount);

    fiscalDocument.generate(customerName, finalAmount);

    String successMessage = generateSuccessMessage(documentType, customerName, finalAmount);
    System.out.println(successMessage);
  }

  private String generateSuccessMessage(DocumentType documentType, String customerName, double amount) {
    return "Venta realizada con éxito. Documento: " + documentType + ", Cliente: " + customerName + ", Monto: $"
        + amount;
  }
}
