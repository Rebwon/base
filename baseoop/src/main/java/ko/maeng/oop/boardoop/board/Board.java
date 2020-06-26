package ko.maeng.oop.boardoop.board;

import java.util.ArrayList;
import java.util.List;

import ko.maeng.oop.boardoop.common.Id;
import ko.maeng.oop.boardoop.post.Post;
import ko.maeng.oop.boardoop.post.PostNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Board {
	private Id id;
	private Category category;
	private List<Post> posts = new ArrayList<>();

	public Board(Category category) {
		this.id = new Id();
		this.category = category;
	}

	public void write(Post post) {
		this.posts.add(post);
	}

	public Post findPost(Id id) {
		return this.posts.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.orElseThrow(PostNotFoundException::new);
	}
}
