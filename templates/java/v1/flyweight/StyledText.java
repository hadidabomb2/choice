package flyweight;

public class StyledText {
    private final String text;
    private final TextStyle style;

    public StyledText(String text, TextStyle style) {
        this.text = text;
        this.style = style;
    }

    public String render() {
        return "[" + style.getFontFamily() + "," + style.getFontSize() + "," + style.getColorHex()
                + ",bold=" + style.isBold() + ",italic=" + style.isItalic() + "] " + text;
    }
}
