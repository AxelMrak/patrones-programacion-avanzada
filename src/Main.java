import model.Customer;
import model.Sale;
import billing.DocumentType;
import billing.FiscalDocumentFactory;
import discount.CashDiscountStrategy;
import discount.DiscountStrategy;
import discount.WholesaleDiscountStrategy;
import payment.CashPaymentAdapter;
import payment.CashRegisterPayment;
import payment.MercadoPagoAPI;
import payment.MercadoPagoAdapter;
import service.SaleService;

class Main {
  public static void main(String[] args) {

    FiscalDocumentFactory fiscalDocumentFactory = new FiscalDocumentFactory();
    SaleService saleService = new SaleService(fiscalDocumentFactory);

    runWholesaleScenario(saleService);
    runIndividualScenario(saleService);
  }

  private static void runWholesaleScenario(SaleService saleService) {
    // Wholesale Scenario
    System.out.println("--- Escenario Mayorista --- \n");

    Customer customer = new Customer("Empresa Mendoza", true);
    Sale sale = new Sale(customer, 1000.00);

    DiscountStrategy discount = new WholesaleDiscountStrategy();

    MercadoPagoAPI mercadoPagoAPI = new MercadoPagoAPI();
    MercadoPagoAdapter payment = new MercadoPagoAdapter(mercadoPagoAPI, "Venta Mayorista");

    saleService.checkout(sale, discount, payment, DocumentType.INVOICE_A);

  }

  private static void runIndividualScenario(SaleService saleService) {
    // Individual Scenario
    System.out.println("\n--- Escenario Individual --- \n");

    Customer customer = new Customer("Juan Perez", false);
    Sale sale = new Sale(customer, 200.00);

    DiscountStrategy discount = new CashDiscountStrategy();

    CashRegisterPayment cashRegisterPayment = new CashRegisterPayment();
    CashPaymentAdapter payment = new CashPaymentAdapter(cashRegisterPayment);

    saleService.checkout(sale, discount, payment, DocumentType.INVOICE_B);
  }
}
