package esoft.order.model;

import esoft.abs.model.State;


/**
 * <img src="./doc-files/OrderState.png" alt="OrderState">
 * @author 
 */
public enum OrderState implements State{
    
    
    // initial
    NEW("","Order") ,
    ALLOCATE_EXCEPTION("","Order") ,
    PENDING_ACKNOWLEDGMENT_PAYMENT("","Order"),
    PAID_OUT("","Order"),
    PAYMENT_EXCEPTION("","Order"),
    SHIPPED("","Order"),
    SHIP_EXCEPTION("","Order"),
    CANCELLED("","Order"),
    DELIVERED("","Order"),
    DELIVERY_EXCEPTION("","Order");

    
    private final String info;
    private final String groupState;

    private OrderState(String info, String groupState) {
        this.info = info;
        this.groupState = groupState;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public String getGroupState() {
        return groupState;
    }
    
 }
