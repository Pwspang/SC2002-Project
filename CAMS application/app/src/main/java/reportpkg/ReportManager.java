package reportpkg;

import java.util.*;

/***
 * Used to store the types of report filters.
 * Purpose is to call the write function of filters.
 */
public class ReportManager {
    /**
     * A HashMap with filter name as key and ReportFilterCamp for value.
     * This HashMap will contain types of report filters.
     */
    private static HashMap<String, ReportFilterCamp> reportFilterDict;
    /**
     * An instance of ReportManager.
     */
    private static final ReportManager reportManager = new ReportManager();

    /**
     * Constructor method used to construct ReportManager.
     * Initialises the reportFilterDict.
     */
    private ReportManager() {
        reportFilterDict = new HashMap<String, ReportFilterCamp>();

        reportFilterDict.put("ReportFilterCampAttendee", new ReportFilterCampAttendee());
        reportFilterDict.put("ReportFilterCampCCMember", new ReportFilterCampCCMember());
        reportFilterDict.put("ReportFilterCampEnquiry", new ReportFilterCampEnquiry());
        reportFilterDict.put("ReportFilterCampPerformance", new ReportFilterCampPerformance());
        reportFilterDict.put("ReportFilterCampStudent", new ReportFilterCampStudent());
        reportFilterDict.put("ReportFilterCampStudentID", new ReportFilterCampStudentID());

    }

    /**
     * Gets the list of the types of filters.
     * 
     * @return List of the types of filters.
     */
    public ArrayList<String> getFilterList() {
        return new ArrayList<>(reportFilterDict.keySet());
    }

    /**
     * Gets the ReportFilterCamp object based on the name of filter.
     * 
     * @param nameOfFilter The name of the filter.
     * @return ReportFilterCamp with the corresponding filter name.
     */
    public ReportFilterCamp getFilter(String nameOfFilter) {
        ReportFilterCamp rfc = reportFilterDict.get(nameOfFilter);
        return rfc;
    }

    /**
     * Gets the reportFilterDict of ReportManager.
     * 
     * @return HashMap object with filter name as key and ReportFilterCamp for
     *         value.
     */
    public HashMap<String, ReportFilterCamp> getReportFilterDict() {
        return reportFilterDict;
    }

    /**
     * Calls the write method of the filter to generate the report.
     * 
     * @param nameOfFilter The name of the filter to be used.
     * @param ID           The parameter to filter by, depending on filter used.
     *                     (e.g. campID, studentID).
     * @param filename     The file name to write to.
     */
    public void writeCampReport(String nameOfFilter, String ID, String filename) {
        ReportFilterCamp rfc = getFilter(nameOfFilter);
        rfc.write(ID, filename);
    }

    /**
     * Gets an instance of the ReportManager.
     * Ensures that only a single instance of ReportManager is created.
     * 
     * @return ReportManager object
     */
    public static ReportManager getInstance() {
        return reportManager;
    }

}
