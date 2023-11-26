package reportpkg;

/**
 * ReportFilterCamp is an interface that represents all report filters shall
 * implement.
 */
interface ReportFilterCamp {

    /**
     * Writes the report to a specified file name.
     * 
     * @param ID       Either UserID or CampID
     * @param filename The file name that the report will be written to.
     */
    void write(String ID, String filename);

}
