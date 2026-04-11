package billing;

public interface FiscalDocument {
  void generate(String customerName, double amount);
}
