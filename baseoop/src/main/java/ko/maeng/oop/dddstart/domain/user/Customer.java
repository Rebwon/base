package ko.maeng.oop.dddstart.domain.user;

public class Customer {
    private UserNo id;
    private String name;

    public Customer(UserNo id, String name) {
        this.id = id;
        this.name = name;
    }
}
