package model;

import java.util.Objects;

import model.Customer;

public class Sale {
  private final Customer customer;
  private final double originalAmount;
  private double finalAmount;

  public Sale(Customer customer, double originalAmount) {
    this.customer = Objects.requireNonNull(customer, "El cliente no puede ser null.");

    boolean isOriginalAmountNegative = originalAmount < 0;

    if (isOriginalAmountNegative)
      throw new IllegalArgumentException("El monto no puede ser negativo.");

    this.originalAmount = originalAmount;
    this.finalAmount = originalAmount;
  }

  // GETTERS
  public Customer getCustomer() {
    return customer;
  }

  public double getOriginalAmount() {
    return originalAmount;
  }

  public double getFinalAmount() {
    return finalAmount;
  }

  // SETTERS
  public void setFinalAmount(double finalAmount) {

    boolean isFinalAmountNegative = finalAmount < 0;

    if (isFinalAmountNegative)
      throw new IllegalArgumentException("El monto final no puede ser negativo.");

    this.finalAmount = finalAmount;
  }

}
