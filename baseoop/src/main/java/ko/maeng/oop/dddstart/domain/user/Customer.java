package ko.maeng.oop.dddstart.domain.user;

import ko.maeng.oop.dddstart.domain.object.IdentifyNo;

public class Customer {
    private IdentifyNo id;
    private String name;

    public Customer(String name) {
        this.id = new IdentifyNo();
        this.name = name;
    }

    public IdentifyNo getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
