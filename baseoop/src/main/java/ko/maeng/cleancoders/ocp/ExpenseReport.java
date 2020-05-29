package ko.maeng.cleancoders.ocp;

import static ko.maeng.cleancoders.ocp.Expense.Type.*;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private List<Expense> expenses = new ArrayList<>();

    public void printReport(ReportPrinter printer) {
        int total = 0;
        int mealExpenses = 0;

        printHeader(printer);

        for (Expense expense : expenses) {
            if (expense.type == BREAKFAST || expense.type == DINNER)
                mealExpenses += expense.amount;

            String name = "TILT";
            switch (expense.type) {
                case DINNER:
                    name = "Dinner";
                    break;
                case BREAKFAST:
                    name = "Breakfast";
                    break;
                case CAR_RENTAL:
                    name = "Car Rental";
                    break;
            }
            int amount = expense.amount;
            printer.print(String.format("%s\t%s\t$%.02f\n",
                    ((expense.type == DINNER && expense.amount > 5000)
                            || (expense.type == BREAKFAST && expense.amount > 1000)) ? "X" : " ",
                    name, penniesToDollars(amount)));

            total += expense.amount;
        }

        printTotals(printer, total, mealExpenses);
    }

    private void printTotals(ReportPrinter printer, int total, int mealExpenses) {
        printer.print(String.format("\nMeal expenses $%.02f", penniesToDollars(mealExpenses)));
        printer.print(String.format("\nTotal $%.02f", penniesToDollars(total)));
    }

    private void printHeader(ReportPrinter printer) {
        printer.print("Expenses " + getDate() + "\n");
    }

    private double penniesToDollars(int amount) {
        return amount / 100.0;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    private String getDate() {
        return "9/12/2002";
    }
}
