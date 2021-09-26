package task.answer;

import task.util.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class DomainSearch extends AnswerBase {

    private List<EndMatchObject> inputDomains;

    public DomainSearch() {
        final Predicate<InputStream> isAvailable = (input) -> {
            try {
                return (input.available() > 0);
            } catch (IOException e) {
                return false;
            }
        };

        final InputStream systemIn = System.in;
        inputDomains = new ArrayList<>();
        if (isAvailable.test(systemIn)) {
            final Scanner inputScan = new Scanner(systemIn);
            while (inputScan.hasNextLine()) {
                final String line = inputScan.nextLine();
                inputDomains.add(new EndMatchObject(line));
            }
        }
    }

    @Override
    protected boolean validate(final String[] args) {
         if (inputDomains.isEmpty()) {
             Logger.error("Please specify the file that lists the domain names with standard input.");
             return false;
         }

        if (args.length < 1) {
            Logger.error("Please specify one or more domain names in the argument.");
            return false;
        }

        return true;
    }

    @Override
    protected void process(final String[] args) {
        Logger.info(String.format("Search for the domain of the argument that matches the end of the domain list of the standard input. arg=%s", String.join(",", args)));

        final List<String> domains = Arrays.asList(args);
        domains.forEach(domain -> {
            final int matchIndex = inputDomains.indexOf(new EndMatchObject(domain));
            if (matchIndex >= 0) {
                final EndMatchObject matchedObject = inputDomains.get(matchIndex);
                Logger.info(String.format("Prints a list of standard input that matches the end of the argument domain. result=%s", String.join(":", domain, matchedObject.getValue())));
            } else {
                Logger.info(String.format("No match was found at the end of the argument domain from the standard input list. result=%s:NONE", domain));
            }
        });
    }

    private class EndMatchObject {

        private String value;

        private EndMatchObject(final String value) {
            this.value = value;
        }

        private String getValue() {
            return this.value;
        }

        @Override
        public boolean equals(final Object object) {
            final EndMatchObject endMatchObject = (EndMatchObject) object;
            return this.value.endsWith(endMatchObject.getValue());
        }
    }
}
