package ko.maeng.base.java8;

public class Product {
    private int amount;
    private String name;

    public Product(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
