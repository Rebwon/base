package ko.maeng.oop.boardoop.post;

import java.util.ArrayList;
import java.util.List;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.comment.Comment;
import ko.maeng.oop.boardoop.common.Content;
import ko.maeng.oop.boardoop.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode(of = "id")
public class Post {
	private Id id;
	private Title title;
	private Content content;
	private Account writer;
	private List<Comment> comments = new ArrayList<>();

	public Post(Title title, Content content, Account writer) {
		this.id = new Id();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public static Post create(Title title, Content content, Account account) {
		return new Post(title, content, account);
	}

	public void write(Comment comment) {
		this.comments.add(comment);
	}

	public boolean isSameWriter(Account account) {
		return this.writer.equals(account);
	}
}
