package ko.maeng.oop.boardoop.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import ko.maeng.oop.boardoop.common.Id;

public class AccountRepositoryImpl implements AccountRepository {
	private final Map<Id, Account> data = new ConcurrentHashMap<>();

	@Override
	public void save(Account account) {
		data.putIfAbsent(Objects.requireNonNull(account.getId()), account);
	}

	@Override
	public Optional<Account> findById(Id id) {
		return data.values()
			.stream()
			.filter(it -> Objects.equals(id, it.getId()))
			.findFirst();
	}

	@Override
	public List<Account> findAll() {
		return new ArrayList<>(data.values());
	}

	@Override
	public void deleteById(Id id) {
		data.remove(id);
	}
}
