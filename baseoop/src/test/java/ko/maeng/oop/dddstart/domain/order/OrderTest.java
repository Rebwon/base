package ko.maeng.oop.dddstart.domain.order;

import ko.maeng.oop.dddstart.domain.object.IdentifyNo;
import ko.maeng.oop.dddstart.domain.object.Money;
import ko.maeng.oop.dddstart.domain.order.shipping.Address;
import ko.maeng.oop.dddstart.domain.order.shipping.Receiver;
import ko.maeng.oop.dddstart.domain.order.shipping.ShippingInfo;
import ko.maeng.oop.dddstart.domain.product.Product;
import ko.maeng.oop.dddstart.domain.user.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    private OrderRepository orderRepository;
    private Order order;
    private IdentifyNo id;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();

        List<OrderLine> orderLines = asList(
                new OrderLine(new Product("영양 보조 식품1", Money.wons(13000), ""), Money.wons(13000), 1),
                new OrderLine(new Product("장난감 셋트", Money.wons(15000), ""), Money.wons(15000), 2)
        );
        ShippingInfo shippingInfo = new ShippingInfo(
                new Address("서울특별시 강남구", "역삼동 월드메르츠타워", "112-1"),
                new Receiver("김철수", "010-1233-1233"));
        id = new IdentifyNo();
        order = new Order(id, orderLines, shippingInfo, OrderState.PAYMENT_WAITING, new Customer("김철수"));
    }

    @DisplayName("Order 에그리거트 저장")
    @Test
    void saveOrder() {
        Order save = orderRepository.save(order);
        Order dbOrder = orderRepository.findById(id).get();
        assertThat(save).isEqualTo(dbOrder);
    }
}