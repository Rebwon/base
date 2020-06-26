package ko.maeng.oop.boardoop.account;

import ko.maeng.oop.boardoop.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Account {
	private Id id;
	private Email email;
	private Password password;
	private Name name;

	private Account(Email email, Password password, Name name) {
		this.id = new Id();
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public static Account register(Email email, Password password, Name name) {
		return new Account(email, password, name);
	}
}
