package esoft.order.model;

/*
 * OrderLine.java - Class contains the data pertinent to a single
 *                  item in a single order.
 * 
 *
 ************************************************************************/
import esoft.abs.model.QtyLine;
import esoft.abs.model.Product;


/**
 * <pre>
 * This is a Java class called OrderLine that represents a single item in a
 * single order. It contains the following instance variables:
 * 
 * book: a reference to a Book object that represents the book being ordered.
 * 
 * qty: an integer that represents the quantity of the book being ordered.
 * 
 * discount: a double that represents the discount applied to the book.
 * 
 * comments: a string that contains any comments about the order line.
 * 
 * The constructor of the class initializes these instance variables with the
 * values passed as arguments.
 * 
 *
 * The class also contains getter methods for each of the instance variables,
 * allowing other classes to access and retrieve the information contained in an
 * OrderLine object.
 * 
 * Additionally, the class implements the Serializable interface, which means
 * that objects of this class can be serialized and deserialized, allowing them
 * to be stored and retrieved from disk or sent over a network.
 * </pre>
 * 
 * <img src="./doc-files/OrderLine.png" alt="OrderLine">
 */
public class OrderLine  implements QtyLine{

    private static final long serialVersionUID = -5063511252485472431L;

    private final Product stockItem;
    private final int qty;
    private final double discount;
    private final String comments;

    /**
     *
     * @param stockItem     
     * @param qty
     * @param discount
     * @param comments
     */
    public OrderLine(Product stockItem, int qty, double discount, String comments) {
        this.stockItem = stockItem;
        this.qty = qty;
        this.discount = discount;
        this.comments = comments;
    }

    

    /**
     *
     * @return
     */
    //@Override
    @Override
    public int getQty() {
        return qty;
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
    public String getComments() {
        return comments;
    }

    //@Override
    @Override
    public Product getProduct() {
        return stockItem;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "stockItem=" + stockItem + ", qty=" + qty + ", discount=" + discount + ", comments=" + comments + '}';
    }

   
    public double getPrice() {
        return stockItem.getCost() * qty;
    }
    
    
}
