package esoft.order.model;

/* 
 * Customer.java - stores the important information for a single customer. 
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import esoft.com.model.Address;
import java.util.Date;
import java.util.Objects;

/**
 * <pre>
 * This code defines a Customer class that stores information about a customer,
 * including their ID, username, password, name, phone number, email address,
 * account creation date, last visit date, login date, expiration date,
 * discount, balance, year-to-date payment, birthdate, additional data, and
 * address.
 * 
 * The class has several constructors, including one that takes all of the above
 * information as parameters. It also has methods for setting and getting the
 * login and expiration dates, as well as getting various pieces of information
 * about the customer, such as their name, ID, phone number, email address, and
 * address.
 * 
 * The class also has a method for logging an order for the customer and storing
 * the most recent order. Finally, the class overrides the hashCode method for
 * use in hashing algorithms.
 * </pre>
 * <img src="./doc-files/Customer.png" alt="Customer">
 */
public class Customer {

    private static final long serialVersionUID = -7297414189618511748L;

    private final int id;
    private final String uname;
    private final String passwd;
    private final String fname;
    private final String lname;
    private final String phone;
    private final String email;
    private final Date since;
    private final Date lastVisit;
    private Date login;
    private Date expiration;
    private final double discount;
    private final double balance;
    private final double ytdPmt;
    private final Date birthdate;
    private final String data;
    private final Address address;
    

    /**
     * The Customer class has a single constructor that takes 17 parameters
     * 
     * The constructor sets each parameter to the corresponding instance
     * variable of the class. It also initializes mostRecentOrder to null.
     * 
     * Let's break down what each parameter represents:
     * 
     *
     * @param id : The unique identifier for the customer.
     * @param uname : The username for the customer's account.
     * @param passwd : The password for the customer's account.
     * @param fname : The first name of the customer.
     * @param lname : The last name of the customer.
     * @param phone : The phone number for the customer.
     * @param email : The email address for the customer.
     * @param since : The date the customer's account was created.
     * @param lastVisit : The date of the customer's last visit.
     * @param login : The date of the customer's last login.
     * @param expiration : The date the customer's account expires.
     * @param discount : The customer's discount percentage.
     * @param balance : The customer's account balance.
     * @param ytdPmt : The customer's year-to-date payments.
     * @param birthdate : The customer's birthdate.
     * @param data : Additional data associated with the customer.
     * @param address : The customer's address.
     */
    public Customer(int id, String uname, String passwd, String fname,
            String lname, String phone, String email, Date since,
            Date lastVisit, Date login, Date expiration, double discount,
            double balance, double ytdPmt, Date birthdate, String data,
            Address address) {
        this.id = id;
        this.uname = uname;
        this.passwd = passwd;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.since = since;
        this.lastVisit = lastVisit;
        this.login = login;
        this.expiration = expiration;
        this.discount = discount;
        this.balance = balance;
        this.ytdPmt = ytdPmt;
        this.birthdate = birthdate;
        this.data = data;
        this.address = address;
       
    }

    /**
     *
     * @param login
     */
    public void setLogin(Date login) {
        this.login = login;
    }

    /**
     *
     * @param expiration
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     *
     * @return
     */
    public double getDiscount() {
        return discount;
    }

    /**
     *
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getUname() {
        return uname;
    }

   

    /**
     *
     * @return
     */
    public Date getSince() {
        return since;
    }

    /**
     *
     * @return
     */
    public Date getLastVisit() {
        return lastVisit;
    }

    /**
     *
     * @return
     */
    public Date getLogin() {
        return login;
    }

    /**
     *
     * @return
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public double getYtdPmt() {
        return ytdPmt;
    }

    /**
     *
     * @return
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @return
     */
    public String getData() {
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }

    /**
     * This is an equals method that overrides the default implementation
     * provided by the Object class. The method takes an object obj as an
     * argument and returns a boolean value indicating whether the current
     * Customer object is equal to the passed object.
     * 
     * The method first performs a series of checks to quickly determine whether
     * the two objects are identical or not. If the two objects have the same
     * memory reference, they are considered equal and the method returns true.
     * If the passed object is null, the two objects are not equal and the
     * method returns false. If the classes of the two objects are not the same,
     * they are not equal and the method returns false.
     * 
     * If none of these checks return true, the method proceeds to compare the
     * individual fields of the two objects. If any of the fields are different,
     * the two objects are not equal and the method returns false.
     * 
     * Finally, if all of the fields of the two objects are the same, the method
     * returns true, indicating that the two objects are equal.
     * 
     * Note that this equals method compares all of the fields of the Customer
     * object, including non-primitive fields like uname, passwd, address, and
     * mostRecentOrder. This means that two Customer objects will only be
     * considered equal if all of their fields have the same values.
     *
     * <pre>
     * if (this == obj) {
     * return true;
     * }
     * if (obj == null) {
     * return false;
     * }
     * if (getClass() != obj.getClass()) {
     * return false;
     * }
     * final Customer other = (Customer) obj;
     * if (this.id != other.id) {
     * return false;
     * }
     * if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
     * return false;
     * }
     * if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
     * return false;
     * }
     * if (Double.doubleToLongBits(this.ytdPmt) != Double.doubleToLongBits(other.ytdPmt)) {
     * return false;
     * }
     * if (!Objects.equals(this.uname, other.uname)) {
     * return false;
     * }
     * if (!Objects.equals(this.passwd, other.passwd)) {
     * return false;
     * }
     * if (!Objects.equals(this.fname, other.fname)) {
     * return false;
     * }
     * if (!Objects.equals(this.lname, other.lname)) {
     * return false;
     * }
     * if (!Objects.equals(this.phone, other.phone)) {
     * return false;
     * }
     * if (!Objects.equals(this.email, other.email)) {
     * return false;
     * }
     * if (!Objects.equals(this.data, other.data)) {
     * return false;
     * }
     * if (!Objects.equals(this.since, other.since)) {
     * return false;
     * }
     * if (!Objects.equals(this.lastVisit, other.lastVisit)) {
     * return false;
     * }
     * if (!Objects.equals(this.login, other.login)) {
     * return false;
     * }
     * if (!Objects.equals(this.expiration, other.expiration)) {
     * return false;
     * }
     * if (!Objects.equals(this.birthdate, other.birthdate)) {
     * return false;
     * }
     * if (!Objects.equals(this.address, other.address)) {
     * return false;
     * }
     * if (!Objects.equals(this.mostRecentOrder, other.mostRecentOrder)) {
     * return false;
     * }
     * return true;
     * </pre>
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (Double.doubleToLongBits(this.ytdPmt) != Double.doubleToLongBits(other.ytdPmt)) {
            return false;
        }
        if (!Objects.equals(this.uname, other.uname)) {
            return false;
        }
        if (!Objects.equals(this.passwd, other.passwd)) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.since, other.since)) {
            return false;
        }
        if (!Objects.equals(this.lastVisit, other.lastVisit)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.expiration, other.expiration)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", uname=" + uname + ", passwd=" + passwd + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone + ", email=" + email + ", since=" + since + ", lastVisit=" + lastVisit + ", login=" + login + ", expiration=" + expiration + ", discount=" + discount + ", balance=" + balance + ", ytdPmt=" + ytdPmt + ", birthdate=" + birthdate + ", data=" + data + ", address=" + address + '}';
    }
    
    

}
