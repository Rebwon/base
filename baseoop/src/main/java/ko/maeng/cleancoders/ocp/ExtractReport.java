package ko.maeng.cleancoders.ocp;

import static ko.maeng.cleancoders.ocp.Expense.Type.*;

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
		if (isMeal(expense))
			mealExpenses += expense.amount;
		total += expense.amount;
	}

	boolean isMeal(Expense expense) {
		return expense.type == BREAKFAST || expense.type == DINNER;
	}

	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
}