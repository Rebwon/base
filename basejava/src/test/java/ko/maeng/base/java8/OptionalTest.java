package ko.maeng.base.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {
    private List<String> lang;

    @Before
    public void setup(){
        lang = Arrays.asList("Java", "Kotlin", "Python", "JavaScript", "Go", "Swift");
    }

    @Test
    public void 생성() {
        Optional<Object> empty = Optional.empty();
        assertThat(empty).isEmpty();
    }

    @Test
    public void orElse() {
        Optional<String> string = Optional.ofNullable(null);
        String other = string.orElse("other");
        assertThat(other).isEqualTo("other");
    }

    @Test
    public void null체크() {
        List<String> strings = Optional.ofNullable(lang).orElseGet(() -> new ArrayList<>());
    }
}
