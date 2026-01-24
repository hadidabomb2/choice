package templatemethod;

public class SummaryReportGenerator extends ReportGenerator {
    private static final String SUMMARY_PREFIX = "Summary report: ";

    @Override
    protected String buildBody(String normalized) {
        return SUMMARY_PREFIX + normalized;
    }
}
