package ko.maeng.cleancoders.ocp;

import static ko.maeng.cleancoders.ocp.Expense.Type.*;

public class BreakfastExpense extends Expense {
	public BreakfastExpense(int amount) {
		super(Type.BREAKFAST, amount);
	}

	@Override
	boolean isMeal() {
		return type == BREAKFAST || type == DINNER;
	}

	@Override
	boolean isOverage() {
		return (type == DINNER && amount > 5000)
			|| (type == BREAKFAST && amount > 1000);
	}
}
