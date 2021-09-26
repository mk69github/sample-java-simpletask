package task;

import task.answer.Permutation;
import task.answer.DomainSearch;
import task.type.AnswerType;
import task.util.Logger;

import java.util.Arrays;

public class Main {

    public static void main(final String[] args) {
        Logger.info(String.format("Enter the simple task. args=[%s]", String.join(",", args)));

        if (args.length < 1) {
            Logger.error(String.format("Please specify the answer type in the first argument. type=[%s]", AnswerType.getValuesStr()));
            System.exit(1);
        }

        switch (AnswerType.of(args[0])) {
            case A:
                Logger.info("Output the answer of task A.");
                final Permutation permutation = new Permutation();
                permutation.run(Arrays.copyOfRange(args, 1, args.length));
                break;
            case B:
                Logger.info("Output the answer of task B.");
                final DomainSearch search = new DomainSearch();
                search.run(Arrays.copyOfRange(args, 1, args.length));
                break;
            default:
                Logger.error(String.format("Please specify the answer type in the first argument. type=[%s]", AnswerType.getValuesStr()));
                System.exit(1);
        }

        Logger.info("Exit the simple task.");
        System.exit(0);
    }
}
