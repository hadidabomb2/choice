package prototype;

import java.util.ArrayList;
import java.util.List;

public class Document implements Prototype<Document> {
    private String title;
    private final List<String> paragraphs;

    public Document(String title, List<String> paragraphs) {
        this.title = title;
        this.paragraphs = new ArrayList<>(paragraphs);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addParagraph(String paragraph) {
        this.paragraphs.add(paragraph);
    }

    public String summary() {
        return "Document{title='" + title + "', paragraphs=" + paragraphs.size() + "}";
    }

    @Override
    public Document clonePrototype() {
        return new Document(title, new ArrayList<>(paragraphs));
    }
}
