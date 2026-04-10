package payment;

import payment.PaymentProcessor;
import payment.MercadoPagoAPI;

public class MercadoPagoAdapter implements PaymentProcessor {
  private MercadoPagoAPI mercadoPagoAPI;

  public MercadoPagoAdapter(MercadoPagoAPI mercadoPagoAPI) {
    this.mercadoPagoAPI = mercadoPagoAPI;
  }

  @Override
  public void processPayment(double amount) {
    String description = "Producto TEST #91218";
    mercadoPagoAPI.createPreference(amount, description);
  }
}
