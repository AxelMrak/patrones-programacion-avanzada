package model;

import model.Customer;

public class Sale {
  private Customer customer;
  private double originalAmount;
  private double finalAmount;

  public Sale(Customer customer, double originalAmount) {
    this.customer = customer;

    if (originalAmount < 0)
      throw new IllegalArgumentException("Original amount cannot be negative.");
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
