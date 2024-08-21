package esoft.model;

import esoft.com.model.Country;
import esoft.order.model.CCTransaction;
import esoft.order.model.CreditCard;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;



public class CCTransactionTest {

    @Test
    public void testCCTransaction() {
        CreditCard type = CreditCard.VISA;
        long num = 1234567890123456L;
        String name = "John Doe";
        Date expire = new Date(0);
        String authId = "12345";
        double amount = 100.0;
        Date date = new Date(0);
        Country country = new Country(1, "France", "EUR", 1.0);

        CCTransaction transaction = new CCTransaction(type, num, name, expire, authId, amount, date, country);

        assertNotNull(transaction);
        assertEquals(type, transaction.getType());
        assertEquals(num, transaction.getNum());
        assertEquals(name, transaction.getName());
        assertEquals(expire, transaction.getExpire());
        assertEquals(authId, transaction.getAuthId());
        assertEquals(amount, transaction.getAmount(), 0.1);
        assertEquals(date, transaction.getDate());
        assertEquals(country, transaction.getCountry());
    }

}
