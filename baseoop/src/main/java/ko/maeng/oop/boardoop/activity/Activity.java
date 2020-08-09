package ko.maeng.oop.boardoop.activity;

import ko.maeng.oop.boardoop.account.Account;
import ko.maeng.oop.boardoop.common.Id;
import lombok.Getter;

@Getter
public abstract class Activity {
	private Id id;
	private Account account;
	private int score = 0;

	public Activity(Account account, int score) {
		this.id = new Id();
		this.account = account;
		this.score = score;
	}
}
