package esoft.model;

import esoft.abs.model.Item;
import esoft.abs.model.Product;
import esoft.com.model.Address;
import esoft.com.model.Country;
import esoft.com.model.ShipType;
import esoft.order.model.*;
import org.junit.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class OrderTest {

    @Test
    public void testConstructor() {

        Country country = new Country(1, "Brasil", "BRL", 2.1);
        Address address = new Address(1, "Avenida Brigadeiro Faria Lima", "", "São Paulo",
                "SP", "01451-000", country,
                "-23.574691", "-46.688377", "2013");

        Item item = new Item(1, "Test CartTest");
        Product product = new Product() {
            @Override
            public int getId() {
                return 0;
            }

            @Override
            public double getCost() {
                return 5.0;
            }

            @Override
            public Item getItem() {
                return item;
            }

            @Override
            public int getQuantityOnHand() {
                return 10;
            }

            @Override
            public int getIdStock() {
                return 0;
            }
        };

        Cart cart = new Cart(1, Date.from(Instant.now()));
        cart.changeLine(product, 2);
        Order order = new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                OrderState.NEW, address, address, ShipType.FEDEX, new CCTransaction(CreditCard.AMEX, 1234, "Amex Card",
                Date.from(Instant.now()), "1040", 100.00, Date.from(Instant.now()), country), new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address), Collections.singletonList(new OrderLine(product, 2, 0.1, "Test Comments")),
                cart);
    }
}
