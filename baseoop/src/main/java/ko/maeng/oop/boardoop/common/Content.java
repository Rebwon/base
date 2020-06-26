package ko.maeng.oop.boardoop.common;

import static ko.maeng.oop.boardoop.common.StringUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Content {
	private final String content;

	public Content(String content) {
		this.content = validate(content);
	}
}
