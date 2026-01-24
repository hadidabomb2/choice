package templatemethod;

public class DetailedReportGenerator extends ReportGenerator {
    @Override
    protected String buildBody(String normalized) {
        return "Detailed report:\n- " + normalized.replace("\n", "\n- ");
    }

    @Override
    protected String format(String body) {
        return "[DETAIL]\n" + body;
    }
}
