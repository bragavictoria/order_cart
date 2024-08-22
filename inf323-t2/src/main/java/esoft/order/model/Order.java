package esoft.order.model;

import esoft.abs.model.Context;
import esoft.abs.model.ListQtyLine;
import esoft.abs.model.State;
import esoft.com.model.Address;
import esoft.com.model.ShipType;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static esoft.com.util.Validator.validatePositive;

public class Order implements Context<OrderState>, ListQtyLine<OrderLine>, Serializable {
    private final double subtotal;
    private final double tax;
    private final double total;
    private final int id;
    private final Date date;
    private final Date shipDate;
    private static final long serialVersionUID = 1L;

    private OrderState status;
    private final Address billingAddress;
    private final Address shippingAddress;
    private final ShipType shipType;
    private final CCTransaction cc;
    private final Customer customer;
    private final List<OrderLine> lines;

    public Order(double subtotal, double tax, double total, int id, Date date, Date shipDate, OrderState status,
                 Address billingAddress, Address shippingAddress, ShipType shipType, CCTransaction cc, Customer
                         customer, List<OrderLine> lines, Cart cart) {
        validatePositive(cart.subTotal(0), "Subtotal");
        this.subtotal = cart.subTotal(0);
        validatePositive(cart.tax(0), "Tax");
        this.tax = cart.tax(0);
        validatePositive(cart.total(0), "Total");
        this.total = cart.total(0);
        this.id = id;
        this.date = Objects.requireNonNull(date, "A data não pode ser nula");
        this.shipDate = shipDate;
        this.status = status;
        this.billingAddress = Objects.requireNonNull(billingAddress, "O endereço de cobrança não pode ser nulo");
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "O endereço de entrega não pode ser nulo");
        this.shipType = Objects.requireNonNull(shipType, "O tipo de cidade não pode ser nulo");
        this.cc = Objects.requireNonNull(cc, "A transação não pode ser nula");
        this.customer = Objects.requireNonNull(customer, "O cliente não pode ser nulo");
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Order lines não pode ser vazia");
        }
        this.lines = Collections.unmodifiableList(Objects.requireNonNull(lines, "O pedido não pode ser nulo"));
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Date getShipDate() {
        return new Date(shipDate.getTime());
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public CCTransaction getCc() {
        return cc;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public State getStatus() {
        return status;
    }

    @Override
    public void setStatus(OrderState orderState) {
        this.status = orderState;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<OrderLine> getLines() {
        return lines;
    }
}
