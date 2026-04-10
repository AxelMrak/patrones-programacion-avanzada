import billing.FiscalDocumentFactory;
import model.Sale;
import payment.MercadoPagoAPI;
import payment.MercadoPagoAdapter;
import service.SaleService;

class Main {
  public static void main(String[] args) {

    FiscalDocumentFactory fiscalDocumentFactory = new FiscalDocumentFactory();
    SaleService saleService = new SaleService(fiscalDocumentFactory);

    // Wholesale Scenario
    //
    System.out.println("--- Escenario Mayorista --- \n");

    Customer wholesaleCustomer = new Customer("Empresa Mendoza", true);
    Sale wholesaleSale = new Sale(wholesaleCustomer, 1000.00);

    DiscountStrategy wholesaleDiscount = new WholesaleDiscountStrategy();

    MercadoPagoAPI mercadoPagoAPI = new MercadoPagoAPI();

    MercadoPagoAdapter mercadoPagoAdapter = new MercadoPagoAdapter(mercadoPagoAPI);



    saleService.checkout(wholesaleSale, wholesaleDiscount, mercadoPagoAdapter, "A");

    // Individual Scenario

    System.out.println("\n--- Escenario Individual --- \n");

    Customer individualCustomer = new Customer("Juan Perez", false);
    Sale individualSale = new Sale(individualCustomer, 200.00);

    DiscountStrategy cashDiscount = new CashDiscountStrategy();

    CashRegisterSystem cashRegisterSystem = new CashRegisterSystem();

    CashSystemAdapter cashSystemAdapter = new CashSystemAdapter(cashRegisterSystem);

    saleService = new SaleService(fiscalDocumentFactory);

    saleService.checkout(individualSale, cashDiscount, cashSystemAdapter, "B");
  }
}
