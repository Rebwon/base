package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieTest {

	private Movie movie;

	@BeforeEach
	void setUp() {
		movie = new Movie();
	}

	@Test
	@DisplayName("한번도 rate되지 않으면 average는 0")
	void should_return_0_when_no_ratings() {
		assertThat(movie.getAverage()).isEqualTo(0);
	}

	@Test
	@DisplayName("한번만 rate되면 average는 rate된 값")
	void should_return_1_when_1_was_rated() {
		movie.rate(1);
		assertThat(movie.getAverage()).isEqualTo(1);
	}

	@Test
	@DisplayName("두번만 rate되면 average는 rate된 값의 합의 평균값")
	void should_return_2_when_1_and_3_were_rated() {
		movie.rate(1);
		movie.rate(3);
		assertThat(movie.getAverage()).isEqualTo(2);
	}
}