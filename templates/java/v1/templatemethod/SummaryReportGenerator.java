package templatemethod;

public class SummaryReportGenerator extends ReportGenerator {
    @Override
    protected String buildBody(String normalized) {
        return "Summary report: " + normalized;
    }
}
