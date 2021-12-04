package task.common.model;

public class Text {

    private String[] contents;

    public Text(String... contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return String.join(System.getProperty("line.separator"), this.contents);
    }
}
