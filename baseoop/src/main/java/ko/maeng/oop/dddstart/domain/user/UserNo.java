package ko.maeng.oop.dddstart.domain.user;

import java.util.Objects;
import java.util.UUID;

public class UserNo {
    private UUID id;

    public UserNo() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNo userNo = (UserNo) o;
        return Objects.equals(id, userNo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserNo{" +
                "id=" + id +
                '}';
    }
}
