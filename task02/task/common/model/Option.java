package task.common.model;

import task.common.type.OptionType;

import java.util.Optional;

public class Option {

    private OptionType type;

    private Optional<String> value;

    public Option(OptionType type, Optional<String> value) {
        this.type = type;
        this.value = value;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public Optional<String> getValue() {
        return value;
    }

    public void setValue(Optional<String> value) {
        this.value = value;
    }
}
