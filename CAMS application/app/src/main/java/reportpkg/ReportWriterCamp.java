package reportpkg;

public class ReportWriterCamp {
    private static final ReportWriterCamp reportWriterCamp = new ReportWriterCamp();

    private ReportWriterCamp() {
    }

    public void writeCampReport(ReportFilterCamp filter) {
        if (filter == null) {
            throw new InvalidParameterException("Cannot write report");
        }
        filter.write();

    }

    public static ReportWriterCamp getInstance() {
        return reportWriterCamp;

    }

}
