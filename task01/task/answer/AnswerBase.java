package task.answer;

import task.type.AnswerType;
import task.util.Logger;

public abstract class AnswerBase {

    protected abstract boolean validate(final String[] args);

    protected abstract void process(final String[] args);

    public void run(final String[] args) {
        if (!validate(args)) {
            Logger.error("Argument is invalid.");
            System.exit(1);
        }

        process(args);
    }
}
