
package esoft.model;

import esoft.abs.model.Item;
import esoft.abs.model.Product;
import esoft.order.model.Cart;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class CartTest {

    private Cart cart;
    Item item = new Item(1, "Test CartTest");
    private Product product1 = new Product() {
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
    private Product product2 = new Product() {
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
    
    @Test
    public void testAddLine() {
        cart.changeLine(product1, 2);
        assertNotNull(cart.getLines());
        assertEquals(1, cart.getLines().size());
        assertEquals(10.00, cart.subTotal(0), 0.01);
        assertEquals(9.00, cart.subTotal(10), 0.01);
        assertEquals(0.7425, cart.tax(10), 0.01);
        assertEquals(0.00, cart.shipCost(), 0.01);
        cart.changeLine(product2, 2);
        assertEquals(19.485, cart.total(10), 0.01);
    }
    
    @Test
    public void testUpdateLine() {
        cart.changeLine(product1, 2);
        cart.changeLine(product2, 3);
        assertNotNull(cart.getLines());
        assertEquals(2, cart.getLines().size());
        assertEquals(25.00, cart.subTotal(0), 0.01);
        assertEquals(1.8562500000000002, cart.tax(10), 0.01);
        assertEquals(0.0, cart.shipCost(), 0.01);
        assertEquals(24.35625, cart.total(10), 0.01);
    }
    
    @Test
    public void testRemoveLine() {
        cart.changeLine(product1, 2);
        cart.changeLine(product2, 1);
        cart.changeLine(product1, 0);
        assertNotNull(cart.getLines());
        assertEquals(1, cart.getLines().size());
        assertEquals(5.00, cart.subTotal(0), 0.01);
        assertEquals(0.37125, cart.tax(10), 0.01);
        assertEquals(0.00, cart.shipCost(), 0.01);
        assertEquals(4.87125, cart.total(10), 0.01);
    }
}
