package ko.maeng.oop.boardoop.activity;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.comment.Comment;
import ko.maeng.oop.boardoop.common.Id;
import ko.maeng.oop.boardoop.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Activity {
	private Id id;
	private Score score;
	private Account account;
	private Post post;
	private Comment comment;

	private Activity(Account account, Post post, Comment comment, ScoreCondition condition) {
		this.id = new Id();
		this.account = account;
		this.post = post;
		this.comment = comment;
		this.score = condition.isSatisfied();
	}

	public static Activity writePost(Account account, Post post, ScoreCondition condition) {
		return new Activity(account, post, null, condition);
	}

	public static Activity writeComment(Account account, Comment comment, ScoreCondition condition) {
		return new Activity(account, null, comment, condition);
	}

	public Integer getScore() {
		return this.score.getScore();
	}
}
