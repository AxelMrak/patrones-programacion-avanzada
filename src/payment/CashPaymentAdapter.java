package payment;

import payment.PaymentProcessor;
import payment.CashRegisterPayment;

class CashRegisterPayment implements PaymentProcessor {
  private CashRegisterPayment cashRegisterPayment;

  public CashRegisterPayment(CashRegisterPayment cashRegisterPayment) {
    this.cashRegisterPayment = cashRegisterPayment;
  }

  @Override
  public void processPayment(double amount) {
    cashRegisterPayment.registerPayment(amount);
  }
}
