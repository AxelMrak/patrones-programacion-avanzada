package service;

import billing.FiscalDocument;
import billing.FiscalDocumentFactory;
import discount.DiscountStrategy;
import model.Sale;
import payment.PaymentProcessor;

public class SaleService {
  private FiscalDocumentFactory fiscalDocumentFactory;

  public SaleService(FiscalDocumentFactory fiscalDocumentFactory) {
    this.fiscalDocumentFactory = fiscalDocumentFactory;
  }

  public void checkout(Sale sale, DiscountStrategy discountStrategy, PaymentProcessor paymentProcessor,
      String documentType) {

    double finalAmount = discountStrategy.applyDiscount(sale.getOriginalAmount());

    sale.setFinalAmount(finalAmount);

    paymentProcessor.processPayment(finalAmount);

    FiscalDocument fiscalDocument = fiscalDocumentFactory.createFiscalDocument(documentType);
    String customerName = sale.getCustomer().getName();
    double saleFinalAmount = sale.getFinalAmount();
    fiscalDocument.generate(customerName, saleFinalAmount);

    String successMessage = generateSuccessMessage(documentType, customerName, saleFinalAmount);
    System.out.println(successMessage);
  }

  private String generateSuccessMessage(String documentType, String customerName, double amount) {
    return "Venta realizada con éxito. Documento: " + documentType + ", Cliente: " + customerName + ", Monto: $" + amount;
  }
}
