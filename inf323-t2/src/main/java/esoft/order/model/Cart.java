package esoft.order.model;

/*
 * Cart.java - Class stores the necessary components of a shopping cart.
 * 
 ************************************************************************
 *
 *
 ************************************************************************/
import esoft.abs.model.ListQtyLine;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import esoft.abs.model.Product;


/**
 * <pre>
 * This code defines the Cart class, which represents a shopping cart in an
 * online bookstore. The Cart class has the following attributes:
 *
 * id: an integer that represents the unique identifier of the cart.
 *
 * time: a Date object that represents the time when the cart was created.
 *
 * linesByBookId: a HashMap that maps a book's ID to a CartLine object, which
 * represents a line in the cart containing a book and the quantity of that
 * book.
 *
 * aggregateCost: a double that represents the total cost of all the books in
 * the cart.
 *
 * aggregateQuantity: an integer that represents the total number of books in
 * the cart.
 *
 * The Cart class has the following methods:
 *
 * Cart(int id, Date time): a constructor that initializes the id, time, and
 * linesByBookId attributes and calls the clear() method to initialize the
 * aggregateCost and aggregateQuantity attributes.
 *
 * getId(): a method that returns the id attribute.
 *
 * getTime(): a method that returns the time attribute.
 *
 * setTime(Date time): a method that sets the time attribute to the specified
 * Date.
 *
 * clear(): a method that clears the linesByBookId attribute and sets the
 * aggregateCost and aggregateQuantity attributes to 0.
 *
 * getLines(): a method that returns a Collection of all the CartLine objects in
 * the linesByBookId attribute.
 *
 * increaseLine(Book book, int quantity): a method that increases the quantity
 * of a book in the cart by the specified quantity or adds the book to the cart
 * if it is not already in it.
 *
 * changeLine(Book book, int quantity): a method that changes the quantity of a
 * book in the cart to the specified quantity.
 *
 * subTotal(double discount): a method that calculates the subtotal cost of all
 * the books in the cart after applying the specified discount.
 *
 * tax(double discount): a method that calculates the tax amount on the subtotal
 * cost of all the books in the cart after applying the specified discount.
 *
 * shipCost(): a method that calculates the shipping cost of the cart based on
 * the total number of books in it.
 *
 * total(double discount): a method that calculates the total cost of the cart
 * after applying the specified discount, tax, and shipping cost.
 *
 * </pre>
 *
 * <img src="./doc-files/Cart.png" alt="Cart">
 */
public class Cart  implements ListQtyLine<CartLine>{

    private static final long serialVersionUID = -4194553499937996531L;

    private final int id;
    private Date time;
    private HashMap<Integer, CartLine> linesByProductId;

    public Cart(int id, Date time) {
        this.id = id;
        this.time = time;
        clear();
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
    public Date getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     *
     *
     */
    public void clear() {
        linesByProductId = new HashMap<>();
    }

    private double aggregateCost() {
        return linesByProductId.values().stream().mapToDouble(l -> l.getPrice()).sum();
    }

    private double aggregateQuantity() {
        return linesByProductId.values().stream().mapToInt(l -> l.getQty()).sum();
    }

    /**
     *
     * @return
     */
    @Override
    public List<CartLine> getLines() {
        return new ArrayList<>(linesByProductId.values());
    }

    /**
     *
     * @param product
     * @param quantity
     */
    public void changeLine(Product product, int quantity) {
        changeLine(product, quantity, 0.0);
    }

    public void changeLine(Product product, int quantity, double shipment) {
        CartLine line = linesByProductId.get(product.getId());
        if (line == null) {
            line = new CartLine(product, 0);
            linesByProductId.put(product.getId(), line);
        }
        line.setQty(quantity);
        line.setShipment(shipment);
        if (quantity == 0) {
            linesByProductId.remove(product.getId());
        }
    }



    /**
     *
     * The subTotal method calculates the subtotal cost of all the items in the
     * shopping cart, taking into account the given discount percentage.
     * <pre>
     * return aggregateCost * ((100 - discount) * 0.01);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double subTotal(double discount) {
        return aggregateCost() * ((100 - discount) * 0.01);
    }

    /**
     *
     * The tax method calculates the sales tax amount based on the subtotal cost
     * and the given discount percentage. The tax rate is fixed at 8.25%.
     * <pre>
     * return subTotal(discount) * 0.0825;
     * </pre>
     *
     * @param discount
     * @return
     */
    public double tax(double discount) {
        return subTotal(discount) * 0.0825;
    }

    public double shipCost() {
        return linesByProductId.values().stream()
                .filter(line -> line.getShipment() != 0.0)
                .mapToDouble(line -> line.getShipment())
                .sum();
    }

    /**
     *
     * The total method calculates the total cost of the shopping cart,
     * including the subtotal, shipping cost, and sales tax, taking into account
     * the given discount percentage.
     * <pre>
     * return subTotal(discount) + shipCost() + tax(discount);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double total(double discount) {
        return subTotal(discount) + shipCost() + tax(discount);
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", time=" + time + ", linesByProductId=" + linesByProductId + ", aggregateCost=" + aggregateCost() + ", aggregateQuantity=" + aggregateQuantity() + '}';
    }

}
