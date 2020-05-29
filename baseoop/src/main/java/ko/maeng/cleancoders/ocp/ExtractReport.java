package ko.maeng.cleancoders.ocp;

import java.util.ArrayList;
import java.util.List;

public class ExtractReport {
	List<Expense> expenses = new ArrayList<>();
	int total;
	int mealExpenses;

	public ExtractReport() {
	}

	void totalUpExpenses() {
		for (Expense expense : expenses)
			addTotals(expense);
	}

	void addTotals(Expense expense) {
		if (expense.isMeal())
			mealExpenses += expense.amount;
		total += expense.amount;
	}

	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
}