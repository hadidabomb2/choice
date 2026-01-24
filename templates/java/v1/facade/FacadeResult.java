package facade;

public class FacadeResult {
    private final String greeting;
    private final String drawing;

    public FacadeResult(String greeting, String drawing) {
        this.greeting = greeting;
        this.drawing = drawing;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getDrawing() {
        return drawing;
    }
}
