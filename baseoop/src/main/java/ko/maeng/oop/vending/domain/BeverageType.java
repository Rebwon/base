package ko.maeng.oop.vending.domain;

public enum BeverageType {
    COLA("콜라"),
    SPRITE("사이다"),
    LEMONADE("레몬에이드"),
    WATER("물");

    private String name;

    BeverageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
