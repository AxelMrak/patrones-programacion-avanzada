package model;

import model.Customer;

public class Sale {
  private Customer customer;
  private double originalAmount;
  private double finalAmount;

  public Sale(Customer customer, double originalAmount) {
    this.customer = customer;

    if (originalAmount < 0)
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
    this.finalAmount = finalAmount;
  }

}
