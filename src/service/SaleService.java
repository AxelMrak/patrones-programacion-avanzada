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

    string CUSTOMER_NAME = sale.getCustomer().getName();
    double ORIGINAL_AMOUNT = sale.getOriginalAmount();
    FiscalDocument FISCAL_DOCUMENT = this.fiscalDocumentFactory.createFiscalDocument(documentType);
    double AMOUNT_WITH_DISCOUNT_APPLIED = discountStrategy.applyDiscount(ORIGINAL_AMOUNT);

    sale.setFinalAmount(AMOUNT_WITH_DISCOUNT_APPLIED);
    paymentProcessor.processPayment(AMOUNT_WITH_DISCOUNT_APPLIED);

    double saleFinalAmount = sale.getFinalAmount();
    FISCAL_DOCUMENT.generate(CUSTOMER_NAME, saleFinalAmount);

    String successMessage = generateSuccessMessage(documentType, CUSTOMER_NAME, saleFinalAmount);
    System.out.println(successMessage);
  }

  private String generateSuccessMessage(DocumentType documentType, String customerName, double amount) {
    return "Venta realizada con éxito. Documento: " + documentType + ", Cliente: " + customerName + ", Monto: $"
        + amount;
  }
}
