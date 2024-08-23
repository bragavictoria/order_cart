package esoft.model;

import esoft.abs.model.Item;
import esoft.abs.model.Product;
import esoft.com.exception.NullValueException;
import esoft.com.model.Address;
import esoft.com.model.Country;
import esoft.com.model.ShipType;
import esoft.order.model.*;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

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

        CCTransaction ccTransaction = new CCTransaction(CreditCard.AMEX, 1234, "Amex Card",
                Date.from(Instant.now()), "1040", 100.00, Date.from(Instant.now()), country);

        Customer customer = new Customer(1, "jdoe", "password", "John", "Doe",
                "555-1234", "jdoe@example.com", new Date(2022, 1, 1),
                new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address);

        OrderLine orderLine = new OrderLine(product, 2, 0.1, "Test Comments");

        Order order = new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                OrderState.NEW, address, address, ShipType.FEDEX, new CCTransaction(CreditCard.AMEX, 1234, "Amex Card",
                Date.from(Instant.now()), "1040", 100.00, Date.from(Instant.now()), country), new Customer(1, "jdoe",
                "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address), Collections.singletonList(new OrderLine(product, 2, 0.1,
                "Test Comments")),
                cart);

        assertNotNull(order);
        assertEquals(10.0, order.getSubtotal());
        assertEquals(2.0, order.getTax());
        assertEquals(12.0, order.getTotal());
        assertEquals(OrderState.NEW, order.getStatus());
        assertEquals(address, order.getBillingAddress());
        assertEquals(address, order.getShippingAddress());
        assertEquals(ShipType.FEDEX, order.getShipType());
        assertEquals(ccTransaction, order.getCc());
        assertEquals(customer, order.getCustomer());
        assertEquals(1, order.getLines().size());
        assertEquals(orderLine, order.getLines().get(0));
    }

    @Test
    public void testNegativeSubtotalThrowsException() {
        List<OrderLine> lines = createValidOrderLines();
        Address address = createValidAddress();
        Customer customer = createValidCustomer(address);
        CCTransaction ccTransaction = createValidCCTransaction();
        Cart cart = createValidCart();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Order(-10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                        OrderState.NEW, address, address, ShipType.FEDEX, ccTransaction, customer, lines, cart)
        );
        assertTrue(exception.getMessage().contains("Subtotal precisa ser positivo"));
    }


    @Test
    public void testNullBillingAddressThrowsException() {
        List<OrderLine> lines = createValidOrderLines();
        Address address = createValidAddress();
        Customer customer = createValidCustomer(address);
        CCTransaction ccTransaction = createValidCCTransaction();
        Cart cart = createValidCart();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                        OrderState.NEW, null, address, ShipType.FEDEX, ccTransaction, customer, lines, cart)
        );
        assertTrue(exception.getMessage().contains("O endereço de cobrança não pode ser nulo"));
    }

    @Test
    public void testImmutabilityOfOrderLines() {
        List<OrderLine> lines = createValidOrderLines();
        Address address = createValidAddress();
        Customer customer = createValidCustomer(address);
        CCTransaction ccTransaction = createValidCCTransaction();
        Cart cart = createValidCart();

        Order order = new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                OrderState.NEW, address, address, ShipType.FEDEX, ccTransaction, customer, lines, cart);

        Exception exception = assertThrows(UnsupportedOperationException.class, () ->
                order.getLines().add(new OrderLine(createValidProduct(), 2, 10.0, "Teste"))
        );
        assertNotNull(exception);
    }

    @Test
    public void testNullShipDateThrowsException() {
        List<OrderLine> lines = createValidOrderLines();
        Address address = createValidAddress();
        Customer customer = createValidCustomer(address);
        CCTransaction ccTransaction = createValidCCTransaction();
        Cart cart = createValidCart();

        Exception exception = assertThrows(NullPointerException.class, () ->
                new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), null,
                        OrderState.NEW, address, address, ShipType.FEDEX, ccTransaction, customer, lines, cart)
        );
        assertTrue(exception.getMessage().contains("A data de envio não pode ser nula"));
    }

    @Test
    public void testConvertCartLines() {
        CartLine cartLine = new CartLine(createValidProduct(), 2, 100.0);
        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);
        Cart cart = createValidCart();

        Order order = new Order(10.0, 2.0, 12.0, 1, Date.from(Instant.now()), Date.from(Instant.now()),
                OrderState.NEW, createValidAddress(), createValidAddress(),
                ShipType.FEDEX, createValidCCTransaction(), createValidCustomer(createValidAddress()),
                createValidOrderLines(), cart);

        List<OrderLine> orderLines = order.convertCartLines(cartLines);
        assertEquals(1, orderLines.size());
        assertEquals(cartLine.getProduct(), orderLines.get(0).getProduct());
        assertEquals(cartLine.getQty(), orderLines.get(0).getQty());
    }

    private List<OrderLine> createValidOrderLines() {
        Product product = createValidProduct();
        OrderLine orderLine = new OrderLine(product, 2, 100.0, "Teste");
        return Collections.singletonList(orderLine);
    }

    private Product createValidProduct() {
        return new Product() {
            @Override
            public int getId() {
                return 1;
            }

            @Override
            public double getCost() {
                return 100.0;
            }

            @Override
            public Item getItem() {
                return new Item(1, "Test Item");
            }

            @Override
            public int getQuantityOnHand() {
                return 10;
            }

            @Override
            public int getIdStock() {
                return 1;
            }
        };
    }

    private Address createValidAddress() {
        Country country = new Country(1, "Brasil", "BRL", 2.1);
        return new Address(1, "Avenida Brigadeiro Faria Lima", "", "São Paulo", "SP",
                "01451-000", country, "-23.574691", "-46.688377", "2013");
    }

    private Customer createValidCustomer(Address address) {
        return new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address);
    }

    private CCTransaction createValidCCTransaction() {
        return new CCTransaction(CreditCard.AMEX, 1234, "Amex Card", Date.from(Instant.now()),
                "1040", 100.00, Date.from(Instant.now()), new Country(1, "Brasil", "BRL", 2.1));
    }

    private Cart createValidCart(){
        return new Cart(9, new Date(2022, 8, 3));
    }
}