package discount;

public class WholesaleDiscountStrategy implements DiscountStrategy {
  private static final double DISCOUNT_RATE = 0.20;

  @Override
  public double applyDiscount(double price) {
    return price * (1 - DISCOUNT_RATE);
  }
}
