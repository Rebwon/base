package ko.maeng.cleancoders.ocp;

public class ExpenseReporter {
    private final ExtractReport extractReport = new ExtractReport();
    private ReportPrinter printer;
    private String name;

    public ExpenseReporter() {
        extractReport.total = 0;
        extractReport.mealExpenses = 0;
    }

    public void printReport(ReportPrinter printer) {
        this.printer = printer;
        extractReport.totalUpExpenses();

        printExpensesAndTotals();
    }

    private void printExpensesAndTotals() {
        printHeader();
        printExpenses();
        printTotals();
    }

    private void printExpenses() {
        for(Expense expense : extractReport.expenses)
            printExpense(expense);
    }

    private void printExpense(Expense expense) {
        printer.print(String.format("%s\t%s\t$%.02f\n",
                expense.isOverage() ? "X" : " ",
            getName(expense), penniesToDollars(expense.amount)));
    }

    private String getName(Expense expense) {
        String name = "TILT";
        if(expense instanceof DinnerExpense)
        	name = "Dinner";
        else if(expense instanceof BreakfastExpense)
        	name = "Breakfast";
        else if(expense instanceof CarRentalExpense)
        	name = "Car Rental";
        return name;
    }

    private void printTotals() {
        printer.print(String.format("\nMeal expenses $%.02f", penniesToDollars(extractReport.mealExpenses)));
        printer.print(String.format("\nTotal $%.02f", penniesToDollars(extractReport.total)));
    }

    private void printHeader() {
        printer.print("Expenses " + getDate() + "\n");
    }

    private double penniesToDollars(int amount) {
        return amount / 100.0;
    }

    public void addExpense(Expense expense) {
        extractReport.addExpense(expense);
    }

    private String getDate() {
        return "9/12/2002";
    }
}
