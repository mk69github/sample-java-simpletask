package task.common.type;

import java.util.stream.Stream;

public enum OutputType {
    STDOUT("stdout"),
    OTHER("");

    private String name;

    OutputType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static OutputType of(String name) {
        return Stream.of(OutputType.values())
                .filter(outputType -> outputType.getName().equals(name))
                .findFirst()
                .orElse(OutputType.OTHER);
    }
}
