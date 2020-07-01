package ko.maeng.oop.boardoop.post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ko.maeng.oop.boardoop.common.Id;

public class Posts {
	private final List<Post> posts;

	public Posts() {
		this.posts = new ArrayList<>();
	}

	public Post fineOne(Id postId) {
		return this.posts.stream()
			.filter(p -> p.getId().equals(postId))
			.findFirst()
			.orElseThrow(PostNotFoundException::new);
	}

	public List<Post> findAll() {
		return this.posts.stream()
			.collect(Collectors.toUnmodifiableList());
	}

	public void add(Post post) {
		this.posts.add(post);
	}

	public boolean isEmpty() {
		return posts.isEmpty();
	}
}
