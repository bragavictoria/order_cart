package esoft.model;

import esoft.abs.model.Item;
import esoft.abs.model.Product;
import esoft.order.model.Cart;
import esoft.order.model.OrderLine;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class OrderLineTest {

    private Cart cart;
    Item item = new Item(1, "") ;
    private Product item1 = new Product() {
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
    private Product item2 = new Product() {
        @Override
        public int getId() {
            return 1;
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
        cart = new Cart(1, new Date(0));
    }

    // Test the constructor
    @Test
    public void testConstructor() {

        OrderLine line = new OrderLine(item1, 2, 0.1, "Test Comments");
        assertEquals(item1, line.getProduct());
        assertEquals(2, line.getQty());
        assertEquals(0.1, line.getDiscount(), 0.0001);
        assertEquals("Test Comments", line.getComments());
    }

}
