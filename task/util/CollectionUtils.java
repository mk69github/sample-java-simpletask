package task.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    public static List<String> createRemovedList(final String element, final List<String> list) {
        final List<String> results = new ArrayList<>(list);
        results.remove(element);
        return results;
    }

    public static List<String> createAddedHeadList(final String element, final List<String> list) {
        final List<String> results = new ArrayList<>();
        results.add(element);
        results.addAll(list);
        return results;
    }
}
