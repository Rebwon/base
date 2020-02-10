package ko.maeng.designpattern.study.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    @DisplayName("전략패턴을 사용한 문자열 검증")
    @Test
    void valid(){
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        assertThat(b1).isFalse();
        assertThat(b2).isTrue();
    }

    @DisplayName("람다와 전략패턴을 사용한 문자열 검증")
    @Test
    void lambdaValid(){
        Validator numericValidator =
                new Validator((String s) -> s.matches("\\d+"));
        boolean b1 = numericValidator.validate("aaa");
        Validator lowerCaseValidator =
                new Validator((String s) -> s.matches("[a-z]+"));
        boolean b2 = lowerCaseValidator.validate("bbbb");

        assertThat(b1).isFalse();
        assertThat(b2).isTrue();
    }
}