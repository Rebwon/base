package ko.maeng.oop.dddstart.domain.order;

import ko.maeng.oop.dddstart.domain.object.IdentifyNo;
import ko.maeng.oop.dddstart.domain.object.Money;
import ko.maeng.oop.dddstart.domain.order.shipping.ShippingInfo;
import ko.maeng.oop.dddstart.domain.user.Customer;

import java.util.List;

public class Order {
    private Customer customer;
    private IdentifyNo id;
    private Money totalAmounts;
    private List<OrderLine> orderLines;
    private OrderState state;
    private DeliveryStatus status;
    private ShippingInfo shippingInfo;

    public Order(IdentifyNo id, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state, Customer customer) {
        this.id = id;
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        setCustomer(customer);
        this.state = state;
    }

    public void changeShipped() {
        verifyNotYetShipped();
        this.state = OrderState.SHIPPED;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        this.shippingInfo = newShippingInfo;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IdentifyNo getId() {
        return id;
    }

    public Money getTotalAmounts() {
        return totalAmounts;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public OrderState getState() {
        return state;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    private void setCustomer(Customer customer) {
        if(customer == null) throw new IllegalArgumentException("no customer");
        this.customer = customer;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if(shippingInfo == null) {
            throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = Money.sum(orderLines, OrderLine::getAmounts);
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if(orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void verifyNotYetShipped() {
        if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalStateException("already shipped");
    }
}
