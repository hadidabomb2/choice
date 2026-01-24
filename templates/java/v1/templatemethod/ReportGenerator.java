package templatemethod;

public abstract class ReportGenerator {
    public final String generate(String input) {
        String normalized = normalize(input);
        String body = buildBody(normalized);
        return format(body);
    }

    protected String normalize(String input) {
        return input == null ? "" : input.trim();
    }

    protected abstract String buildBody(String normalized);

    protected String format(String body) {
        return body;
    }
}
