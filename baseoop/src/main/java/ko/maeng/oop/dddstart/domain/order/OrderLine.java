package ko.maeng.oop.dddstart.domain.order;

import ko.maeng.oop.dddstart.domain.object.Money;
import ko.maeng.oop.dddstart.domain.product.Product;

public class OrderLine {
    private Product product;
    private Money price;
    private Money amounts;
    private int quantity;

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.times(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public Money getPrice() {
        return price;
    }

    public Money getAmounts() {
        return amounts;
    }

    public int getQuantity() {
        return quantity;
    }
}
