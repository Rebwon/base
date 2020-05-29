package ko.maeng.cleancoders.ocp;

import static ko.maeng.cleancoders.ocp.Expense.Type.*;

public class Expense {
    public enum Type {DINNER, BREAKFAST, CAR_RENTAL}

    public Type type;
    public int amount;

    public Expense(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    boolean isMeal() {
        return type == BREAKFAST || type == DINNER;
    }

    boolean isOverage() {
        return (type == DINNER && amount > 5000)
            || (type == BREAKFAST && amount > 1000);
    }
}
