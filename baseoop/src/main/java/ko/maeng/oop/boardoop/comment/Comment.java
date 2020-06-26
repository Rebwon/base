package ko.maeng.oop.boardoop.comment;


import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.common.Content;
import ko.maeng.oop.boardoop.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString @EqualsAndHashCode
public class Comment {
	private Id id;
	private Account replyee;
	private Content content;

	public Comment(Account replyee, Content content) {
		this.id = new Id();
		this.replyee = replyee;
		this.content = content;
	}

	public static Comment create(Account replyee, Content content) {
		return new Comment(replyee, content);
	}
}
