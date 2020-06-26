package ko.maeng.oop.boardoop.account;

import static ko.maeng.oop.boardoop.common.StringUtils.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Email {
	private final String email;

	public Email(String email) {
		this.email = validate(email);
	}
}
