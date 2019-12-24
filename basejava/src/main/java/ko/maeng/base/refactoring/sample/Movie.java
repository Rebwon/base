package ko.maeng.base.refactoring.sample;

public class Movie {
    // 상수로 정의한 대여물 분류 부분을 상속관계를 통한 하위 클래스로 분류한다.
    public static final int CHILDRENS = 2;
    public static final int REGUlAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private Price _price;

    public Movie(String _title, int priceCode) {
        this._title = _title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return _title;
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGUlAR:
                _price = new RegularPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("가격 코드가 잘못되었습니다.");
        }
    }

    // getCharge 메소드를 Movie 클래스로 옮긴 이유는
    // 비디오 종류가 나중에 추가되어도 그로 인해 미치는 영향을
    // 최소화하기 위해서이다.
    double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }

    int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }
}
