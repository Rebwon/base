package ko.maeng.oop.boardoop.comment;


import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.common.Content;
import ko.maeng.oop.boardoop.common.Id;
import ko.maeng.oop.boardoop.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString @EqualsAndHashCode
public class Comment {
	private Id id;
	private Post post;
	private Account replyee;
	private Content content;

	public Comment(Account replyee, Content content, Post post) {
		this.id = new Id();
		this.replyee = replyee;
		this.content = content;
		this.post = post;
	}

	public static Comment create(Account replyee, Content content, Post post) {
		return new Comment(replyee, content, post);
	}
}
