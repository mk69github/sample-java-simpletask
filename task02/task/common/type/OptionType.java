package task.common.type;

import java.util.stream.Stream;

public enum OptionType {
    HELP("h"),
    INPUT("i"),
    CONVERSION("c"),
    OUTPUT("o"),
    OTHER("");

    private String name;

    OptionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static OptionType of(String name) {
        return Stream.of(OptionType.values())
                .filter(optionType -> optionType.getName().equals(name))
                .findFirst()
                .orElse(OptionType.OTHER);
    }
}
