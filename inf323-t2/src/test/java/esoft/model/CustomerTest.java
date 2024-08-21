package esoft.model;

import esoft.com.model.Address;
import esoft.com.model.Country;
import esoft.order.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer1;
    private Customer customer2;

    @Before
    public void setUp() throws Exception {
        Country country = new Country(1, "USA", "DOL", 1.0);
        Address address = new Address(1, "123 Main St", "", "Anytown", "CA", "12345", country, "", "", "");

        
        customer1 = new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address);
        customer2 = new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address);
    }

    @Test
    public void testGetters() {
        assertEquals(1, customer1.getId());
        assertEquals("jdoe", customer1.getUname());
        assertEquals("password", customer1.getPasswd());
        assertEquals("John", customer1.getFname());
        assertEquals("Doe", customer1.getLname());
        assertEquals("555-1234", customer1.getPhone());
        assertEquals("jdoe@example.com", customer1.getEmail());
        assertEquals(new Date(2022, 1, 1), customer1.getSince());
        assertEquals(new Date(2022, 2, 1), customer1.getLastVisit());
        assertNull(customer1.getLogin());
        assertNull(customer1.getExpiration());
        assertEquals(0.0, customer1.getDiscount(), 0.0001);
        assertEquals(0.0, customer1.getBalance(), 0.0001);
        assertEquals(0.0, customer1.getYtdPmt(), 0.0001);
        assertEquals(new Date(2000, 1, 1), customer1.getBirthdate());
        assertEquals("data", customer1.getData());
        Address address = customer1.getAddress();
                assertEquals(1, address.getId());
        assertEquals("123 Main St", address.getStreet1());
        assertEquals("", address.getStreet2());
        assertEquals("Anytown", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("12345", address.getZip());
        assertEquals("USA", address.getCountry().getName());
        assertEquals("", address.getLatitude());
        assertEquals("", address.getLongitude());
        assertEquals("", address.getBuildingNumber());
    }

    @Test
    public void testSetters() {
        assertNull(customer1.getLogin());
        customer1.setLogin(new Date(2023, 1, 1));
        assertEquals(new Date(2023, 1, 1), customer1.getLogin());

        assertNull(customer1.getExpiration());
        customer1.setExpiration(new Date(2024, 1, 1));
        assertEquals(new Date(2024, 1, 1), customer1.getExpiration());
    }

    
    @Test
    public void testEquals() {
        assertTrue(customer1.equals(customer2));
    }
}
