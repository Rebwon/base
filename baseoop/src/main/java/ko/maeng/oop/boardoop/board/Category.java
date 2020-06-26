package ko.maeng.oop.boardoop.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Category {
	private final String name;

	public Category(String name) {
		this.name = name;
	}
}
