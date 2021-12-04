package task.common.util;

import java.util.Map;
import java.util.stream.Collectors;

public class JsonUtils {

    public static String toJson(Map<String, String> elements) {
        final String content = elements.entrySet().stream()
                .map(element -> String.format("\"%s\": \"%s\",", element.getKey(), element.getValue()))
                .collect(Collectors.joining())
                .replaceFirst(".$","");

        return String.format("{%s}", content);
    }
}

