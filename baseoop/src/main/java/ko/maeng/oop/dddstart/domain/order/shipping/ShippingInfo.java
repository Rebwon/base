package ko.maeng.oop.dddstart.domain.order.shipping;

public class ShippingInfo {
    private Address address;
    private Receiver receiver;

    public ShippingInfo(Address address, Receiver receiver) {
        this.address = address;
        this.receiver = receiver;
    }

    public Address getAddress() {
        return address;
    }

    public Receiver getReceiver() {
        return receiver;
    }
}
