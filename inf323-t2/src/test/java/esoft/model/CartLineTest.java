package esoft.model;

import esoft.com.model.Address;
import esoft.com.model.Country;
import esoft.abs.model.Item;
import esoft.order.model.CartLine;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import esoft.abs.model.Product;

public class CartLineTest {

    private Item item = new Item(1,"Test Book"){};
    private Address address;
    private Product stockItem = new Product() {
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

    @Before
    public void setUp() {
        // Create an example item and address for the Product
        Country country = new Country(1, "France", "EUR", 1.0);
        address = new Address(1, "123 Main St", "", "Anytown", "CA", "12345", country, "", "", "");
    }

    @Test
    public void testGetQty() {

        CartLine cartLine = new CartLine(stockItem, 3);
        assertEquals(3, cartLine.getQty());
    }

    @Test
    public void testSetQty() {

        CartLine cartLine = new CartLine(stockItem, 3);
        cartLine.setQty(5);
        assertEquals(5, cartLine.getQty());
    }

    @Test
    public void testGetStock() {

        CartLine cartLine = new CartLine(stockItem, 3);
        assertEquals(stockItem, cartLine.getProduct());
    }

}
