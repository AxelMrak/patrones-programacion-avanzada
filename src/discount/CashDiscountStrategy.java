public class CashDiscountStrategy implements DiscountStrategy {
  private final double DISCOUNT_RATE = 0.10;

  @Override
  public double applyDiscount(double price) {
    return price * (1 - DISCOUNT_RATE);
  }

}
