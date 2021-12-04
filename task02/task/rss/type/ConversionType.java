package task.rss.type;

import task.rss.model.Rss;

import java.util.function.Function;
import java.util.stream.Stream;

public enum ConversionType {
    CUT("cut", (rss) -> {
        rss.setTitle(rss.getTitle().substring(0, 10));
        rss.setDescription(rss.getDescription().substring(0, 30));

        return rss;
    }),
    CONVERT("convert", (rss) -> {
        rss.setDescription(rss.getDescription().replace("りんご", "Apple"));

        return rss;
    }),
    OTHER("other", null);

    private String name;

    private Function<Rss, Rss> function;

    ConversionType(String name, Function<Rss, Rss> function) {
        this.setName(name);
        this.setFunction(function);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFunction(Function<Rss, Rss> function) {
        this.function = function;
    }

    public static ConversionType of(String name) {
        return Stream.of(ConversionType.values())
                .filter(optionType -> optionType.getName().equals(name))
                .findFirst()
                .orElse(ConversionType.OTHER);
    }

    public Rss process(Rss rss) {
        return this.function.apply(rss);
    }
}
