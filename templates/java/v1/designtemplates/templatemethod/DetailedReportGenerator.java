package designtemplates.templatemethod;

public class DetailedReportGenerator extends ReportGenerator {
    private static final String DETAIL_PREFIX = "Detailed report:\n- ";
    private static final String DETAIL_HEADER = "[DETAIL]\n";
    private static final String BULLET_PREFIX = "\n- ";

    @Override
    protected String buildBody(String normalized) {
        return DETAIL_PREFIX + normalized.replace("\n", BULLET_PREFIX);
    }

    @Override
    protected String format(String body) {
        return DETAIL_HEADER + body;
    }
}
