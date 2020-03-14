package ko.maeng.oop.vending.domain;

import java.util.List;

public class BeverageList {
    private final List<Beverage> beverages;

    public BeverageList(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public Beverage get(Beverage beverage) {
        return beverages.stream()
                .filter(beverage::equals)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 음료가 없습니다."));
    }
}
