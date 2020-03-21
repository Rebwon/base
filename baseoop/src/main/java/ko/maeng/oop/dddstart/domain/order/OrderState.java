package ko.maeng.oop.dddstart.domain.order;

public enum OrderState {
    PAYMENT_WAITING("결제 대기중"),
    PREPARING("상품 준비중"),
    SHIPPED("출고"),
    DELIVERING("배송중"),
    DELIVERY_COMPLETED("배송 완료"),
    CANCELED("취소");

    private String name;

    OrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
