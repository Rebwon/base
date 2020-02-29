package ko.maeng.base.java8.java8inaction.chap10;

import java.util.Optional;

public class OptionalUtils {
    public static Optional<Integer> stringToInt(String s) {
        try{
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
