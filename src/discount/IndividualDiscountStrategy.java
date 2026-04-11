package discount;

public class IndividualDiscountStrategy implements DiscountStrategy {
  private static final double DISCOUNT_RATE = 0.10;
  private static final double MAX_DISCOUNT_AMOUNT = 50.00;

  @Override
  public double applyDiscount(double price) {
    boolean isPriceNegative = price < 0;
    if (isPriceNegative) {
      throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    double rawDiscountAmount = price * DISCOUNT_RATE;
    double appliedDiscountAmount = Math.min(rawDiscountAmount, MAX_DISCOUNT_AMOUNT);

    return price - appliedDiscountAmount;
  }
}
