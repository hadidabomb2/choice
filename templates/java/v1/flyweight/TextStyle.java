package flyweight;

public final class TextStyle {
    private final String fontFamily;
    private final int fontSize;
    private final String colorHex;
    private final boolean bold;
    private final boolean italic;

    public TextStyle(String fontFamily, int fontSize, String colorHex, boolean bold, boolean italic) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.colorHex = colorHex;
        this.bold = bold;
        this.italic = italic;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getColorHex() {
        return colorHex;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }
}
