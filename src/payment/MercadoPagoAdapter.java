package payment;

import payment.PaymentProcessor;

import java.util.Objects;

import payment.MercadoPagoAPI;

public class MercadoPagoAdapter implements PaymentProcessor {
  private MercadoPagoAPI mercadoPagoAPI;
  private final String description;

  public MercadoPagoAdapter(MercadoPagoAPI mercadoPagoAPI, String description) {

    this.mercadoPagoAPI = Objects.requireNonNull(mercadoPagoAPI, "MercadoPagoAPI no puede ser null");

    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
    }
    this.description = description;
  }

  @Override
  public void processPayment(double amount) {
    boolean isAmountNegative = amount < 0;

    if (isAmountNegative)
      throw new IllegalArgumentException("El monto no puede ser negativo.");

    mercadoPagoAPI.createPreference(amount, description);
  }
}
