package ko.maeng.cleancoders.ocp;

import static ko.maeng.cleancoders.ocp.Expense.Type.*;

public class CarRentalExpense extends Expense {
	public CarRentalExpense(int amount) {
		super(Type.CAR_RENTAL, amount);
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
