package ko.maeng.oop.boardoop.account;

import static ko.maeng.oop.boardoop.common.StringUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Name {
	private final String name;

	public Name(String name) {
		this.name = validate(name);
	}
}
