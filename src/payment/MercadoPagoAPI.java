package payment;

/**
 * Simulates integration with MercadoPago API for payment processing.
 * External LIB.
 */

public class MercadoPagoAPI {

  public void createPreference(double amount, String description) {
    System.out.println("Creando preferencia de pago en MercadoPago: $" + amount + " - " + description);
    System.out.println("Ojala la API de MercadoPago fuera asi de simple...");
  }

}
