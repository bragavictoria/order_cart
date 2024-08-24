package esoft.order.model;

import esoft.abs.model.Context;
import esoft.abs.model.ListQtyLine;
import esoft.com.model.Address;
import esoft.com.model.ShipType;

import java.io.Serializable;
import java.util.*;

import static esoft.com.util.Validator.validateNotNull;

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
        validateNotNull(shipDate, "ShipDate");
        this.shipDate = shipDate;
        this.status = status;
        this.billingAddress = Objects.requireNonNull(billingAddress, "O endereço de cobrança não pode ser nulo");
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "O endereço de entrega não pode ser nulo");
        this.shipType = Objects.requireNonNull(shipType, "O tipo de envio não pode ser nulo");
        this.cc = Objects.requireNonNull(cc, "A transação não pode ser nula");
        this.customer = Objects.requireNonNull(customer, "O cliente não pode ser nulo");
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Order lines não pode ser vazia");
        }
        this.lines = Collections.unmodifiableList(Objects.requireNonNull(lines, "O pedido não pode ser nulo"));
    }

    public Order(double subtotal, double tax, double total, int id, Date date, Date shipDate, Address billingAddress,
                 Address shippingAddress, CCTransaction ccTransaction, Customer customer, List<OrderLine> lines, ShipType shipType) {

        this.subtotal = validatePositive(calculateSubtotal(lines), "Subtotal");
        this.tax = validatePositive(calculateTax(lines), "Tax");
        this.total = validatePositive(calculateTotal(lines), "Total");
        this.id = id;
        this.date = new Date(Objects.requireNonNull(date, "A data não pode ser nula").getTime());
        this.shipDate = new Date(Objects.requireNonNull(shipDate, "A data de envio não pode ser nula").getTime());
        this.billingAddress = Objects.requireNonNull(shippingAddress, "O endereço de cobrança não pode ser nulo");
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "O endereço de entrega não pode ser nulo");
        this.cc = Objects.requireNonNull(ccTransaction, "A transação não pode ser nula");
        this.customer = Objects.requireNonNull(customer, "O cliente não pode ser nulo");
        this.lines = Collections.unmodifiableList(Objects.requireNonNull(lines, "Order lines não pode ser nula"));
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Order lines não pode ser vazia");
        }
        this.shipType = Objects.requireNonNull(shipType, "O tipo de envio não pode ser nulo");
    }

    private double calculateSubtotal(List<OrderLine> lines) {
        return lines.stream().mapToDouble(OrderLine::getPrice).sum();
    }

    private double calculateTax(List<OrderLine> lines) {
        return calculateSubtotal(lines) * 0.0825;
    }

    private double calculateTotal(List<OrderLine> lines) {
        return calculateSubtotal(lines) + calculateTax(lines);
    }

    public List<OrderLine> convertCartLines(List<CartLine> cartLines) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (CartLine cartLine : cartLines) {
            OrderLine orderLine = new OrderLine(cartLine.getProduct(), cartLine.getQty(), cartLine.getPrice(), "");
            orderLines.add(orderLine);
        }
        return orderLines;
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
    public OrderState getStatus() {
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

    private double validatePositive(double value, String attributeName) {
        if (value <= 0) {
            throw new IllegalArgumentException(attributeName + " precisa ser positivo");
        }
        return value;
    }
}