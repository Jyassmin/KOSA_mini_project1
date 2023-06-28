import java.io.IOException;

public class ShopManager{
    private CustomerManager customerManager;
    //private ProductManager productManager;
    ShopManager() throws IOException {
        customerManager = new CustomerManager();
    }
}
