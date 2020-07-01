package ko.maeng.oop.boardoop.board;

import ko.maeng.oop.boardoop.common.Id;
import ko.maeng.oop.boardoop.post.Post;
import ko.maeng.oop.boardoop.post.Posts;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Board {
	private Id id;
	private Category category;
	private Posts posts;

	public Board(Category category) {
		this.id = new Id();
		this.category = category;
		this.posts = new Posts();
	}

	public void write(Post post) {
		this.posts.add(post);
	}

	public Post findPost(Id id) {
		return posts.fineOne(id);
	}

	public boolean hasEmptyPosts() {
		return posts.isEmpty();
	}
}
