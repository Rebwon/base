package ko.maeng.oop.boardoop.post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ko.maeng.oop.boardoop.account.Account;
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
	private Category category;
	private Set<Tag> tags = new HashSet<>();
	private List<Account> favorited = new ArrayList<>();

	public Post(Title title, Content content, Account writer, Category category) {
		this.id = new Id();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.category = category;
	}

	public static Post create(Title title, Content content, Account account, Category category) {
		return new Post(title, content, account, category);
	}

	public boolean isSameWriter(Account account) {
		return this.writer.equals(account);
	}

	public int favoritesCount() {
		return favorited.size();
	}
}
