package ko.maeng.oop.boardoop.activity;

public class PostScoreCondition extends ScoreCondition {
	@Override
	protected Score isSatisfied() {
		return new Score(10);
	}
}
