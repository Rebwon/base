package ko.maeng.oop.boardoop.activity;

public class CommentScoreCondition extends ScoreCondition {
	@Override
	protected Score isSatisfied() {
		return new Score(5);
	}
}
