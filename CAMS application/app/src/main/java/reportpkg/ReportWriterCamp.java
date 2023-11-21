package reportpkg;

public class ReportWriterCamp {
    private static final ReportWriterCamp reportWriterCamp = new ReportWriterCamp();

    private ReportWriterCamp() {
    }

    public void writeCampReport(ReportFilterCamp filter, String filename) {
        if (filter == null || filter.getID() == null) {
            throw new RuntimeException("Cannot write report");
        }
        filter.write(filename);

    }

    public static ReportWriterCamp getInstance() {
        return reportWriterCamp;

    }

}
