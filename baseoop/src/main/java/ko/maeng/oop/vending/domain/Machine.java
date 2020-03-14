package ko.maeng.oop.vending.domain;

public class Machine {
    private BeverageList beverages = new BeverageList();
    private int price;

    public int getPrice() {
        return price;
    }

    public BeverageList getBeverages() {
        return beverages;
    }

    public void supply(int price) {
        this.price = price;
    }

    public void supply(BeverageList beverages) {
        this.beverages = beverages;
    }

    public Beverage findOne(Beverage beverage) {
        return beverages.get(beverage);
    }

    public void increase(Beverage beverage) {
        beverages.increase(beverage);
    }

    public void decrease(Beverage beverage) {
        beverages.decrease(beverage);
    }
}
