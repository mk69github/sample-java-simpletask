package task.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void info(String message) {
        print("INFO", message);
    }

    public static void error(String message) {
        print("ERROR", message);
    }

    private static void print(String level, String message) {
        final ProcessHandle handle = ProcessHandle.current();
        final long pid = handle.pid();
        final LocalDateTime currentTime = LocalDateTime.now();
        final String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.printf("%s [pid:%s] [%s] %s%n", formattedTime, pid, level, message);
    }
}
