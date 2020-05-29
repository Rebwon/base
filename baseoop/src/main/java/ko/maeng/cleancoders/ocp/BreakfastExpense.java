package ko.maeng.cleancoders.ocp;

public class BreakfastExpense extends Expense {
	public BreakfastExpense(int amount) {
		super(amount);
	}

	@Override
	boolean isMeal() {
		return true;
	}

	@Override
	boolean isOverage() {
		return amount > 1000;
	}
}
