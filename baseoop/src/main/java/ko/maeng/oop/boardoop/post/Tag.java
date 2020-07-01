package ko.maeng.oop.boardoop.post;

import ko.maeng.oop.boardoop.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Tag {
	private Id id;
	private String name;

	public Tag(String name) {
		this.id = new Id();
		this.name = name;
	}
}
