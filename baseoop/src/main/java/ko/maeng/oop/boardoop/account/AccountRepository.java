package ko.maeng.oop.boardoop.account;

import java.util.List;
import java.util.Optional;

import ko.maeng.oop.boardoop.common.Id;

public interface AccountRepository {
	void save(Account account);
	Optional<Account> findById(Id id);
	List<Account> findAll();
	void deleteById(Id id);
}
