public class ShapeFactory {
    public static Shape create(String kind) {
        return switch (kind.toLowerCase()) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            default -> throw new IllegalArgumentException("Unknown shape: " + kind);
        };
    }
}