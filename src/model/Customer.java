package model;

public class Customer {
  private String name;
  private boolean isWholesale;

  public Customer(String name, boolean isWholesale) {
    this.name = name;
    this.isWholesale = isWholesale;
  }

  public String getName() {
    return name;
  }

  public boolean isWholesale() {
    return isWholesale;
  }
}
