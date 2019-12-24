package ko.maeng.base.refactoring.sample;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg){
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    // 대여료 계산 함수 부분을 수정했지만, thisAmount 변수의 불필요한 중복이 남아있었다.
    // thisAmount 변수는 each.getCharge 메소드의 결과를 저장하는데만 사용되고 전혀 사용되지 않았다.
    // 따라서 임시 변수를 메소드 호출로 전환 기법을 사용해서 thisAmount변수를 삭제했다.
    // 이후 적립 포인트 계산 부분도 메소드로 빼내야 한다.
    // 그래서 적립 포인트 계산 코드에 메소드 추출 기법을 다시 사용했다.
    // 임시 변수를 메소드 호출로 전환 기법을 사용해 남은 임시 변수 2개인
    // totalAmount와 frequentRenterPoints 변수를 삭제 했다.
    public String statement(){
        Enumeration rentals = _rentals.elements();
        String result = getName() + " 고객님의 대여 기록\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();

            // 이번에 대여하는 비디오 정보와 대여료를 출력
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }
        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(getTotalCharge()) + "\n";
        result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
        return result;
    }

    public String htmlStatement(){
        Enumeration rentals = _rentals.elements();
        String result = "<H1><EM>" + getName() + " 고객님의 대여 기록</EM></H1><P>\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }
        // 푸터 행 추가
        result += "<P>누적 대여료: <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "적립 포인트: <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM><P>";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    // 메소드 추출 기법을 통해 비디오 대여료 계산 부분을 추출함
    // 매개변수 이름인 each -> aRental로 변경
    // 대여료를 계산한 값을 담는 변수인 thisAmount -> result로 변경
    // amountFor 메소드를 보면 Rental 클래스의 정보를 이용하고, 정작 자신이 속한 Customer클래스의 정보는 이용하지 않는다.
    // 메소드는 대체로 자신이 사용하는 데이터(매개변수)와 같은 객체에 들어 있어야 한다.
    // 그렇기 때문에 Rental클래스로 메소드 이동 기법을 실시해야 한다.
//    public double amountFor(Rental aRental) {
//        double result = 0;
//        switch (aRental.getMovie().getPriceCode()) {
//            case Movie.REGUlAR:
//                result += 2;
//                if(aRental.getDaysRented() > 2)
//                    result += (aRental.getDaysRented() - 2) * 1.5;
//                break;
//            case Movie.NEW_RELEASE:
//                result += aRental.getDaysRented() * 3;
//                break;
//            case Movie.CHILDRENS:
//                result += 1.5;
//                if(aRental.getDaysRented() > 3)
//                    result += (aRental.getDaysRented() - 3) * 1.5;
//                break;
//        }
//        return result;
//    }
}
