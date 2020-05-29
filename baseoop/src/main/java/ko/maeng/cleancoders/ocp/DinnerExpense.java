package ko.maeng.cleancoders.ocp;

public class DinnerExpense extends Expense {
	public DinnerExpense(int amount) {
		super(amount);
	}

	@Override
	boolean isMeal() {
		return true;
	}

	@Override
	boolean isOverage() {
		return amount > 5000;
	}
}
