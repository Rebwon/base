package ko.maeng.base.ej2e.chap1;

public enum Zavis {
    // Enum 싱글턴
    INSTANCE;

    public void test() {
        System.out.println("Test!");
    }
}
