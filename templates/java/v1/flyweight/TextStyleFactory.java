package flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextStyleFactory {
    private final Map<String, TextStyle> cache = new ConcurrentHashMap<>();

    public TextStyle getStyle(String fontFamily, int fontSize, String colorHex, boolean bold, boolean italic) {
        String key = String.join("|",
                fontFamily,
                Integer.toString(fontSize),
                colorHex,
                Boolean.toString(bold),
                Boolean.toString(italic));

        return cache.computeIfAbsent(key,
                k -> new TextStyle(fontFamily, fontSize, colorHex, bold, italic));
    }

    public int cacheSize() {
        return cache.size();
    }
}
