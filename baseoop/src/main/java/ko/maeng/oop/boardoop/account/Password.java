package ko.maeng.oop.boardoop.account;

import static ko.maeng.oop.boardoop.common.StringUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Password {
	private final String password;

	public Password(String password) {
		this.password = validate(password);
	}
}
