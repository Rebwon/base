package ko.maeng.base.javajigi.optional;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionTest {
    @Test
    public void of(){
        assertThat(Expression.PLUS == Expression.of("+")).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void notValidExpression() {
        Expression.of("&");
    }
}