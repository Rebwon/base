package ko.maeng.oop.vending.domain;

public class MachineManager {
    private int price;
    private BeverageList beverages;

    public MachineManager(int price) {
        this.price = price;
    }

    public void supplyMoney(Machine machine) {
        machine.supply(this.price);
    }

    public void supplyBeverages(Machine machine) {
        machine.supply(this.beverages);
    }

    public void addBeverages(BeverageList beverages) {
        this.beverages = beverages;
    }
}
