package model;

public class Customer {
  private final String name;
  private final boolean isWholesale;

  public Customer(String name, boolean isWholesale) {

    boolean isNameEmptyOrNull = name == null || name.isBlank();

    if (isNameEmptyOrNull)
      throw new IllegalArgumentException("El nombre del cliente no puede estar vacio o ser nulo.");

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
