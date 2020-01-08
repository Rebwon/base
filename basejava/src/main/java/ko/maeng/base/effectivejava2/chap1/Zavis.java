package ko.maeng.base.effectivejava2.chap1;

public enum Zavis {
    // Enum 싱글턴
    INSTANCE;

    public void test() {
        System.out.println("Test!");
    }
}
