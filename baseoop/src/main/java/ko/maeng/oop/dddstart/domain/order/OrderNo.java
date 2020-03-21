package ko.maeng.oop.dddstart.domain.order;

import java.util.Objects;
import java.util.UUID;

public class OrderNo {
    private UUID id;

    public OrderNo() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNo orderNo = (OrderNo) o;
        return Objects.equals(id, orderNo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderNo{" +
                "id=" + id +
                '}';
    }
}
