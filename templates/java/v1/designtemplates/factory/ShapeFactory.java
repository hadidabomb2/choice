package designtemplates.factory;

public class ShapeFactory {
    private static final String SHAPE_CIRCLE = "circle";
    private static final String SHAPE_SQUARE = "square";
    private static final String UNKNOWN_SHAPE_PREFIX = "Unknown shape: ";

    public static Shape create(String kind) {
        String normalizedKind = kind.toLowerCase();
        return switch (normalizedKind) {
            case SHAPE_CIRCLE -> new Circle();
            case SHAPE_SQUARE -> new Square();
            default -> throw new IllegalArgumentException(UNKNOWN_SHAPE_PREFIX + kind);
        };
    }
}