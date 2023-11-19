package reportpkg;

public class ReportWriterCamp {

    public void writeCampReport(ReportFilterCamp filter) {
        if (filter == null) {
            throw new InvalidParameterException("Cannot write report");
        }
        filter.write();

    }

}
