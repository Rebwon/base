package ko.maeng.base.refactoring.sample;

public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie _movie, int _daysRented) {
        this._movie = _movie;
        this._daysRented = _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    // 메소드 이동 기법을 통해 Customer에 있던 amountFor 메소드를 옮겼다.
    // Rental 클래스에 맞게 메소드명을 변경하고, 매개변수를 삭제 했다.
    // 타 객체의 속성(Movie.대여물)을 switch문의 인자로 하는 것은 나쁜 방법이다.
    // switch문의 인자로는 타 객체 데이터를 사용하지 않고 자신의 데이터를 사용해야 한다.
    // swtich문을 그대로 Movie 클래스로 옮겼고, movie 클래스에서 메소드를 호출하게 했다.
    double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    // 최신물을 이틀 이상 대여하면 2포인트를 지급하고 그 외엔 1포인트 지급하는 코드를
    // 빼내 getFrequentRenterPoints 메소드를 만들고 Rental 클래스에 옮겼다.
    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
//        Customer 클래스에 statement 메소드 안에 있던 적립 포인트 계산 부분.
//        frequentRenterPoints ++;
//        if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
//                each.getDaysRented() > 1) frequentRenterPoints ++;
    }
}
