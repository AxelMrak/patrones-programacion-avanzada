package billing;

public interface FiscalDocument {
  public void generate(String customerName, double amount);
}
