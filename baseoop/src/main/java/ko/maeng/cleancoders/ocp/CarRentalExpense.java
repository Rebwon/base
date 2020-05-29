package ko.maeng.cleancoders.ocp;

public class CarRentalExpense extends Expense {
	public CarRentalExpense(int amount) {
		super(Type.CAR_RENTAL, amount);
	}

	@Override
	boolean isMeal() {
		return false;
	}

	@Override
	boolean isOverage() {
		return false;
	}
}
