package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GameTest {

	private Game game;

	@BeforeEach
	void setUp() {
		game = new Game();
	}

	@Test
	void canRoll() {
		game.roll(0);
	}

	private void rollMany(int rolls, int pins) {
		for (int i = 0; i < rolls; i++) {
			game.roll(pins);
		}
	}

	@Test
	public void gutterGame() {
		rollMany(20, 0);
		assertThat(game.score()).isEqualTo(0);
	}

	@Test
	public void allOnes() {
		rollMany(20, 1);
		assertThat(game.score()).isEqualTo(20);
	}

	private void rollSpare() {
		game.roll(5);
		game.roll(5);
	}

	@Test
	public void oneSpare() {
		rollSpare();
		game.roll(3);
		rollMany(17, 0);
		assertThat(game.score()).isEqualTo(16);
	}

	private void rollStrike() {
		game.roll(10);
	}

	@Test
	public void oneStrike() {
		rollStrike();
		game.roll(5);
		game.roll(3);
		rollMany(16, 0);
		assertThat(game.score()).isEqualTo(26);
	}

	@Test
	public void perfectGame() {
		rollMany(12, 10);
		assertThat(game.score()).isEqualTo(300);
	}
}
