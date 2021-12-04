package task.rss.model;

import org.w3c.dom.Element;
import task.common.util.JsonUtils;

import java.util.LinkedHashMap;

public class Rss {

    private String title;

    private String description;

    public Rss(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Rss(Element element) {
        this.title = getNodeValue(element, "title");
        this.description = getNodeValue(element, "description");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String getNodeValue(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(new LinkedHashMap<>() {{
            put("title", title);
            put("description", description);
        }});
    }
}
