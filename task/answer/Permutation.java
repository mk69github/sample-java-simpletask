package task.answer;

import task.util.CollectionUtils;
import task.util.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Permutation extends AnswerBase {

    @Override
    protected boolean validate(final String[] args) {
        if (args.length != 1) {
            Logger.error("Please specify an ASCII printable character string as an argument.");
            return false;
        }

        if (args[0].matches("^.*[^\\p{ASCII}].*")) {
            Logger.error(String.format("Please specify only an ASCII printable character string as an argument. arg=%s", args[0]));
            return false;
        }

        return true;
    }

    @Override
    protected void process(final String[] args) {
        Logger.info(String.format("Generates and prints a permutation from the argument string. arg=%s", args[0]));

        final List<String> splittedArg = Arrays.asList(args[0].split(""));
        getPermutations(splittedArg.size(), splittedArg)
                .forEach(permutation -> Logger.info(String.format("Print the generated permutation. result=%s", String.join("", permutation))));
    }

    private Stream<List<String>> getPermutations(final int splitSize, final List<String> splittedValue) {
        return (splitSize <= 0)? Stream.of(new ArrayList<>()) : splittedValue.stream()
                .flatMap(splittedElement -> getPermutations(splitSize - 1, CollectionUtils.createRemovedList(splittedElement, splittedValue))
                        .map(permutation -> CollectionUtils.createAddedHeadList(splittedElement, permutation)))
                .distinct();
    }
}
