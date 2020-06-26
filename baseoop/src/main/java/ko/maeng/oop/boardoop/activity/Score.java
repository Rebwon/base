package ko.maeng.oop.boardoop.activity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Score {
	public static final Score ZERO = new Score(0);
	private final Integer score;

	Score(Integer score) {
		this.score = score;
	}

	public static Score valueOf(int score) {
		return new Score(score);
	}

	public Integer getScore() {
		return this.score;
	}
}
