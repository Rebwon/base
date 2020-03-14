package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;
import ko.maeng.oop.vending.domain.exception.SameBeverageException;

import java.util.*;

public class BeverageList {
    private final Map<Integer, Beverage> beverages = new HashMap<>();

    public void addAll(Map<Integer, Beverage> saveBeverages) {
        beverages.putAll(saveBeverages);
    }

    public Beverage get(Beverage beverage) {
        return beverages.stream()
                .filter(beverage::equals)
                .findAny()
                .orElseThrow(() -> new BeverageNotFoundException("해당 음료가 없습니다."));
    }

    public boolean hasBeverage(int beverageId) {
        return beverages.containsKey(beverageId);
    }

    public void increase(Beverage beverage) {
        if(hasBeverage(beverage)) {
            throw new SameBeverageException("중복된 품목입니다.");
        }
        beverages.add(beverage);
    }

    public void decrease(Beverage beverage) {
        if(hasBeverage(beverage)) {
            beverages.remove(beverage);
        }
        throw new BeverageNotFoundException("해당 음료가 없습니다.");
    }

    public int getSize() {
        return beverages.size();
    }

    @Override
    public String toString() {
        return "BeverageList{" +
                "beverages=" + beverages +
                '}';
    }
}
