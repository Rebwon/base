package ko.maeng.base.java8.java8inaction.chap12;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationTest {
    @Test
    public void duration() {
        LocalDateTime dateTime1 = LocalDateTime.of(2020, 3, 2, 9, 10);
        LocalDateTime dateTime2 = LocalDateTime.of(2020, 3, 2, 12, 13);
        Duration between = Duration.between(dateTime1, dateTime2);

        assertThat(between.toHours()).isEqualTo(3);
    }
}
