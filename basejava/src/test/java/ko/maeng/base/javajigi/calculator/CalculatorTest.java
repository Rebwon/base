package ko.maeng.base.javajigi.calculator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @Test
    public void input(){
        String value = "2 + 3 * 4 / 2";
        String[] values = value.split(" ");

        int first = Integer.parseInt(values[0]);
        String symbol = values[1];
        int second = Integer.parseInt(values[2]);

        assertThat(first).isEqualTo(2);
        assertThat(symbol).isEqualTo("+");
        assertThat(second).isEqualTo(3);

        symbol = values[3];
        first = Integer.parseInt(values[4]);
        second = Integer.parseInt(values[6]);

        assertThat(symbol).isEqualTo("*");
        assertThat(first).isEqualTo(4);
        assertThat(second).isEqualTo(2);
    }
}