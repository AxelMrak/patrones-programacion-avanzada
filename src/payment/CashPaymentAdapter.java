package payment;

public class CashPaymentAdapter implements PaymentProcessor {
  private CashRegisterPayment cashRegisterPayment;

  public CashPaymentAdapter(CashRegisterPayment cashRegisterPayment) {
    this.cashRegisterPayment = cashRegisterPayment;
  }

  @Override
  public void processPayment(double amount) {
    cashRegisterPayment.registerPayment(amount);
  }
}
