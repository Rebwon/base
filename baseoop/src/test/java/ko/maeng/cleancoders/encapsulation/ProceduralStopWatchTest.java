package ko.maeng.cleancoders.encapsulation;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProceduralStopWatchTest {
	private long expectedElapsedTime = 100l;

	@Test
	void should_return_elapsed_milli_seconds() {
		ProceduralStopWatch stopWatch = new ProceduralStopWatch();

		stopWatch.startTime = System.currentTimeMillis();

		doSomething();

		stopWatch.stopTime = System.currentTimeMillis();

		long elapsedTime = stopWatch.getElapsedTime();

		assertThat(elapsedTime).isGreaterThanOrEqualTo(expectedElapsedTime);
	}

	private void doSomething() {
		int sum = 0;
		for (int i = 0; i < 1000000; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}