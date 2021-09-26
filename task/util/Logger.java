package task.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void info(final String message) {
        print("INFO", message);
    }

    public static void error(final String message) {
        print("ERROR", message);
    }

    private static void print(final String level, final String message) {
        final LocalDateTime currentTime = LocalDateTime.now();
        final String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(String.format("%s [%s] %s", formattedTime, level, message));
    }
}
