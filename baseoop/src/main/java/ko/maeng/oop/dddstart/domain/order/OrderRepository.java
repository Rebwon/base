package ko.maeng.oop.dddstart.domain.order;

import ko.maeng.oop.dddstart.domain.object.IdentifyNo;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
	private final Map<UUID, Order> data = new ConcurrentHashMap<>();

	public Order save(Order order) {
		data.putIfAbsent(Objects.requireNonNull(order.getId().getId()), order);
		return order;
	}

	public Optional<Order> findById(IdentifyNo id) {
		return data.values().stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
	}
}
