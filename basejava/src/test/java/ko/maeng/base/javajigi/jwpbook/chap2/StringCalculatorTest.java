package ko.maeng.base.javajigi.jwpbook.chap2;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    private StringCalculator cal;

    @Before
    public void setup(){
        cal = new StringCalculator();
    }

    @Test
    public void add_null_또는_empty() throws Exception {
        assertThat(cal.add("")).isEqualTo(0);
        assertThat(cal.add(null)).isEqualTo(0);
    }

    @Test
    public void add_숫자하나() throws Exception {
        assertThat(cal.add("1")).isEqualTo(1);
    }

    @Test
    public void add_쉼표구분자() throws Exception {
        assertThat(cal.add("1,3")).isEqualTo(4);
    }

    @Test
    public void add_쉼표_콜론_구분자() throws Exception {
        assertThat(cal.add("1,3:2")).isEqualTo(6);
    }

    @Test
    public void add_커스텀구분자() throws Exception {
        assertThat(cal.add("//;\n1;2;3")).isEqualTo(6);
    }

    @Test(expected = RuntimeException.class)
    public void add_음수를_입력() throws Exception {
        cal.add("-2;2");
    }
}