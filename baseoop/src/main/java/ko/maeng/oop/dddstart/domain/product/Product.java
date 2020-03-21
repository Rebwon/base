package ko.maeng.oop.dddstart.domain.product;

import ko.maeng.oop.dddstart.domain.object.Money;

public class Product {
    private String name;
    private Money price;
    private String detail;

    public Product(String name, Money price, String detail) {
        this.name = name;
        this.price = price;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                '}';
    }
}
