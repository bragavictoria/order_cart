package esoft.order.model;

/* 
 * CartLine.java - Class stores the necessary data for a single item in
 *                 a single shopping cart.
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import esoft.abs.model.QtyLine;
import java.util.Objects;
import esoft.abs.model.Product;



/**
 * <pre>
 * The CartLine class represents a line item in a shopping cart. 
 * It has two properties: qty and stock. qty is an integer representing the 
 * quantity of the item, while stock is an instance of the Product class 
 * representing the item being added to the cart.
 *
 * The constructor of CartLine takes in two parameters: qty and stock. 
 * qty represents the quantity of the item being added to the cart, while stock 
 * represents the stock of the item being added to the cart.
 *
 * The setQty method allows the quantity of the item in the cart to be updated. 
 * The getQty method returns the quantity of the item in the cart. 
 * The getStock method returns the Product object representing the item being 
 * added to the cart.
 *
 * The class implements the Serializable interface, indicating that it can be 
 * serialized (converted into a byte stream) and deserialized (converted back 
 * from a byte stream). The serialVersionUID field is used to provide version 
 * control for serialized instances of the class.
 * </pre>
 * <img src="./doc-files/CartLine.png" alt="CartLine">
 */
public class CartLine implements QtyLine {

    private static final long serialVersionUID = 7390646727961714957L;

    private int qty;
    private final Product stockItem;
    private double shipment;

    /**
     *
     * @param qty
     * @param stockItem
     */
    public CartLine(Product stockItem, int qty) {
        this.qty = qty;
        this.stockItem = stockItem;
    }

    public CartLine(Product stockItem, int qty, double shipment) {
        this(stockItem, qty);
        this.shipment = shipment;
    }
    
    

    /**
     *
     * @param qty
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     *
     * @return
     */
    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public Product getProduct() {
        return stockItem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public double getShipment() {
        return shipment;
    }

    public void setShipment(double shipment) {
        this.shipment = shipment;
    }
    
    

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
        final CartLine other = (CartLine) obj;
        if (this.qty != other.qty) {
            return false;
        }
        return Objects.equals(this.stockItem, other.stockItem);
    }

    @Override
    public String toString() {
        return "CartLine{" + "qty=" + qty + ", stockItem=" + stockItem + '}';
    }
    
    public double getPrice() {
        return stockItem.getCost() * qty;
    }
    

}
