package discount;

public class WholesaleDiscountStrategy implements DiscountStrategy {
  private static final double DISCOUNT_RATE = 0.20;

  @Override
  public double applyDiscount(double price) {
    boolean isPriceNegative = price < 0;
    if (isPriceNegative)
      throw new IllegalArgumentException("El precio no puede ser negativo.");


    return price * (1 - DISCOUNT_RATE);
  }
}
