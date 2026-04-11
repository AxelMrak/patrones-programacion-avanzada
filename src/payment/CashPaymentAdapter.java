package payment;

import java.util.Objects;

public class CashPaymentAdapter implements PaymentProcessor {
  private final CashRegisterPayment cashRegisterPayment;

  public CashPaymentAdapter(CashRegisterPayment cashRegisterPayment) {
    this.cashRegisterPayment = Objects.requireNonNull(cashRegisterPayment, "CashRegisterPayment no puede ser null.");
  }

  @Override
  public void processPayment(double amount) {
    boolean isAmountNegative = amount < 0;
    if (isAmountNegative)
      throw new IllegalArgumentException("El monto no puede ser negativo.");
    cashRegisterPayment.registerPayment(amount);
  }
}
