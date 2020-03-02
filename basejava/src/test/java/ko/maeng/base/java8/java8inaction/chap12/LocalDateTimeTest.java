package ko.maeng.base.java8.java8inaction.chap12;

import org.junit.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeTest {
    @Test
    public void createDate() {
        LocalDate date = LocalDate.of(2020, 3, 2);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int length = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        assertThat(year).isEqualTo(2020);
        assertThat(month.getValue()).isEqualTo(3);
        assertThat(dayOfMonth).isEqualTo(2);
        assertThat(dayOfWeek).isEqualTo(DayOfWeek.MONDAY);
        assertThat(length).isEqualTo(31);
        assertThat(leapYear).isTrue();
    }

    @Test
    public void createTime() {
        LocalTime time = LocalTime.of(9, 3, 10);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        assertThat(hour).isEqualTo(9);
        assertThat(minute).isEqualTo(3);
        assertThat(second).isEqualTo(10);
    }

    @Test
    public void createDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 3, 2, 9, 6, 10);
        int year = dateTime.getYear();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();

        assertThat(year).isEqualTo(2020);
        assertThat(dayOfMonth).isEqualTo(2);
        assertThat(hour).isEqualTo(9);
    }
}
