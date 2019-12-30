package ko.maeng.base.javajigi.jwpbook.chap2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private Calculator cal;

    // Before을 통해 중복되는 Cal 인스턴스 생성을 한번에 해결.
    // After을 통해 후처리 작업을 처리.

    @Before
    public void setup(){
        cal = new Calculator();
        System.out.println("Before");
    }

    @Test
    public void add() {
        assertThat(cal.add(4, 3)).isEqualTo(7);
        System.out.println("add");
    }

    @Test
    public void sub() {
        assertThat(cal.sub(4, 3)).isEqualTo(1);
        System.out.println("sub");
    }

    @Test
    public void mul() {
        assertThat(cal.mul(4, 3)).isEqualTo(12);
        System.out.println("mul");
    }


    @Test
    public void div() {
        assertThat(cal.div(4, 2)).isEqualTo(2);
        System.out.println("div");
    }

    @After
    public void after() {
        System.out.println("after");
    }
}