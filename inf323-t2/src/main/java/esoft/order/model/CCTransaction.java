package esoft.order.model;

/* 
 * CCTransaction.java - Class holds data for a single credit card
 *                      transaction.
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import esoft.com.model.Country;
import java.util.Date;

/**
 * <pre>
 * This is the implementation for a class called CCTransaction that holds data
 * for a single credit card transaction. The class has the following properties:
 * 
 * type - a String representing the type of credit card used (e.g. Visa,
 * Mastercard)
 * 
 * num - a long representing the credit card number
 * 
 * name - a String representing the name on the credit card
 * 
 * expire - a Date representing the expiration date of the credit card
 * 
 * authId - a String representing the authorization ID of the transaction
 * 
 * amount - a double representing the amount of the transaction
 * 
 * date - a Date representing the date of the transaction
 * 
 * country - a Country object representing the country where the transaction was
 * made
 * 
 * The class has a constructor that takes in all of the above properties and
 * initializes the corresponding instance variables. The class also has getters
 * for each of the properties.
 * </pre>
 * <img src="./doc-files/CCTransaction.png" alt="CCTransaction">
 */
public class CCTransaction  {

    private static final long serialVersionUID = 5470177450411822726L;

    private final CreditCard type;
    private final long num;
    private final String name;
    private final Date expire;
    private final String authId;
    private final double amount;
    private final Date date;
    private final Country country;

    
    public CCTransaction(CreditCard type, long num, String name, Date expire,
            String authId, double amount, Date date, Country country) {
        this.type = type;
        this.num = num;
        this.name = name;
        this.expire = expire;
        this.authId = authId;
        this.amount = amount;
        this.date = date;
        this.country = country;
    }

    /**
     *
     * @return
     */
    public CreditCard getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public long getNum() {
        return num;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Date getExpire() {
        return expire;
    }

    /**
     *
     * @return
     */
    public String getAuthId() {
        return authId;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public Country getCountry() {
        return country;
    }

}
