package reportpkg;

import java.util.*;

public class ReportManager {
    private static HashMap<String, ReportFilterCamp> reportFilterDict;
    private static final ReportManager reportManager = new ReportManager();

    private ReportManager() {
        reportFilterDict = new HashMap<String, ReportFilterCamp>();

        /* 
        reportFilterDict.put("ReportFilterCampAttendee", new ReportFilterCampAttendee());
        reportFilterDict.put("ReportFilterCampCCMember", new ReportFilterCampCCMember());
        reportFilterDict.put("ReportFilterCampEnquiry", new ReportFilterCampEnquiry());
        reportFilterDict.put("ReportFilterCampPerformance", new ReportFilterCampPerformance());
        reportFilterDict.put("ReportFilterCampStudent", new ReportFilterCampStudent());
        reportFilterDict.put("ReportFilterCampStudentID", new ReportFilterCampStudentID());
        */
    }

    public ReportFilterCamp getFilter(String nameOfFilter, String ID) {
        ReportFilterCamp rfc = reportFilterDict.get(nameOfFilter);
        rfc.setID(ID);
        return rfc;
    }

    public HashMap<String, ReportFilterCamp> getReportFilterDict() {
        return reportFilterDict;
    }

    public void writeCampReport(String nameOfFilter, String ID, String filename) {
        ReportFilterCamp rfc = getFilter(nameOfFilter, ID);
        rfc.write(filename);
    }

    public static ReportManager getInstance() {
        return reportManager;
    }

}
