package ko.maeng.oop.dddstart.domain.object;

import java.util.Objects;
import java.util.UUID;

public class IdentifyNo {
    private UUID id;

    public IdentifyNo() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifyNo that = (IdentifyNo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IdentifyNo{" +
                "id=" + id +
                '}';
    }
}
