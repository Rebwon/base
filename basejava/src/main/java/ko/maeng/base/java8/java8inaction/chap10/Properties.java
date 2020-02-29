package ko.maeng.base.java8.java8inaction.chap10;

import java.util.Optional;

public class Properties {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getValue())
                .flatMap(OptionalUtils::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
