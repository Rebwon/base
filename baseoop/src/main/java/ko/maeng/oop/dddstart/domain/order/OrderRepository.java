package ko.maeng.oop.dddstart.domain.order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public Order save(Order order){
        if(order == null) {
            throw new IllegalArgumentException("No Order");
        }
        orders.add(order);
        return order;
    }
}
