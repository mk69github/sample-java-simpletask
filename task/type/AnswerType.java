package task.type;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AnswerType {
    A("a"),
    B("b"),
    OTHER("z");

    public String value;

    AnswerType(final String value) {
        this.value = value;
    }

    private String getValue() {
        return this.value;
    }

    public static AnswerType of(final String value) {
        return Stream.of(AnswerType.values())
                .filter(answerType -> answerType.getValue().equals(value))
                .findFirst()
                .orElse(AnswerType.OTHER);
    }

    public static String getValuesStr() {
        return Stream.of(AnswerType.values())
                .filter(answerType -> !answerType.equals(AnswerType.OTHER))
                .map(AnswerType::getValue)
                .collect(Collectors.joining(","));
    }
}
