package ko.maeng.oop.boardoop.activity;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.post.Post;
import lombok.Getter;

@Getter
public class PostActivity extends Activity {
	private Post post;

	public PostActivity(Account account, Post post) {
		super(account, 10);
		this.post = post;
	}
}
