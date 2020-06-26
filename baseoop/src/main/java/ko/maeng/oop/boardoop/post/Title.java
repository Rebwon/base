package ko.maeng.oop.boardoop.post;

import static ko.maeng.oop.boardoop.common.StringUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Title {
	private final String title;

	public Title(String title) {
		this.title = validate(title);
	}
}
