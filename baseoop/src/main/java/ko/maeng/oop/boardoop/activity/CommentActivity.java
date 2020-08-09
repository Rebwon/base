package ko.maeng.oop.boardoop.activity;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.comment.Comment;
import lombok.Getter;

@Getter
public class CommentActivity extends Activity {
	private Comment comment;

	public CommentActivity(Account account, Comment comment) {
		super(account, 5);
		this.comment = comment;
	}
}
