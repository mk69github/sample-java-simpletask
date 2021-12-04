package task.common.util;

import task.common.model.Option;
import task.common.type.OptionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArgumentUtils {

    public static List<Option> parseOptions(String[] args) throws IllegalArgumentException {
        final List<Option> options = new ArrayList<>();

        for (int index = 0; index < args.length; index = index + 2) {
            final String optionName = args[index];
            final OptionType optionType = OptionType.of(optionName.replaceFirst("^-",""));

            if (optionType.equals(OptionType.OTHER)) {
                throw new IllegalArgumentException(String.format("An incorrect option has been detected. value=%s", optionName));
            }

            final String optionValue = (index + 1 < args.length) ? args[index + 1] : null;
            options.add(new Option(optionType, Optional.ofNullable(optionValue)));
        }

        return options;
    }

    public static List<Option> getOption(List<Option> options, OptionType optionType) {
        return options.stream()
                .filter(option -> option.getType().equals(optionType))
                .collect(Collectors.toList());
    }
}
