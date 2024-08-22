/*
package esoft.order.model;

import esoft.com.model.Address;
import esoft.com.model.ShipType;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import static esoft.com.util.Validator.validatePositive;

public class Order  {
    private final double subtotal;
    private final double tax;
    private final double total;
    private final int id;
    private final Date date;
    private final Date shipDate;
    private static final long serialVersionUID = 1L;

    private OrderState status;
    private final Address billingAdress;
    private final Address shippingAddress;
    private final ShipType shipType;
    private final CCTransaction cc;
    private final Customer customer;
    private final List<OrderLine> lines;

    public Order(double subtotal, double tax, double total, int id, Date date, Date shipDate, OrderState status,
                 Address billingAdress, Address shippingAddress, ShipType shipeType, CCTransaction cc, Customer
                         customer, List<OrderLine> lines, Cart cart) {

        this.subtotal = validatePositive(cart.subTotal(0), "Subtotal");
        this.tax = validatePositive(cart.tax(0), "Tax");
        this.total = validatePositive(cart.total(0), "Total");
        this.id = id;
        this.date = Objects.requireNonNull(date, "A data não pode ser nula");
        this.shipDate = shipDate;
        this.status = status = OrderState.PENDING;
        this.billingAdress = Objects.requireNonNull(billingAdress; "O endereço de cobrança não pode ser nulo");
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "O endereço de entrega não pode ser nulo");
        this.shipType = Objects.requireNonNull(shipeType,"O tipo de cidade não pode ser nulo" );
        this.cc = Objects.requireNonNull(cc, "A transação não pode ser nula");
        this.customer = Objects.requireNonNull(customer, "O cliente não pode ser nulo");
        this.lines = Collections.unmodifiableList(Objects.requireNonNull(lines, "O pedido não pode ser nulo"));
        if (lines.isEmpty()){
            throw new IllegalArgumentException("Order lines não pode ser vazia");
        }
    }

    private List<OrderLine> convertCartLines(List <CartLine> cartLines){
        List<OrderLine> orderLines = new ArrayList<>();
        for (CartLine cartLine : cartLines) {
            OrderLine orderLine = new OrderLine (cartLine.getProduct(), cartLine.getQty(), cartLine.getPrice());
            orderLines.add(orderLine);
        }
        return orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Address getBillingAdress() {
        return billingAdress;
    }

    public Address getShippingAddress(){
        return shippingAddress;
    }

    public double getSubtotal(){
        return subtotal;
    }

    public double getTax(){
        return tax;
    }

    public double getTotal(){
        return total;
    }

    public int getId(){
        return id;
    }

    public Date getShipDate(){
        return new Date(shipDate.getTime());
    }

    public List<OrderLine> getLines(){
        return lines;
    }

    public CCTransaction getCcTransaction(){
        return ccTransaction;
    }

    public void setStatus (OrderState status)(){
        this.status = status;
    }
}
 */