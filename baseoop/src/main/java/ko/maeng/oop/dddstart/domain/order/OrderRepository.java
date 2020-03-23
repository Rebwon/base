package ko.maeng.oop.dddstart.domain.order;

import ko.maeng.oop.dddstart.domain.object.IdentifyNo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public Order save(Order order){
        if(order == null) {
            throw new IllegalArgumentException("No Order");
        }
        orders.add(order);
        return order;
    }

    public Optional<Order> findById(IdentifyNo id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public void delete(IdentifyNo id) {
        orders.removeIf(order -> order.getId().equals(id));
    }
}
