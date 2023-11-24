package reportpkg;

import authenticationpkg.AuthUser;

/**
 * ReportFilterCamp is an abstract class that represents the base class of all
 * types of filters.
 * It includes attributes: ID.
 * ID is dependent on the type of filter used.
 * Possible IDs: campID, userID.
 * 
 */

interface ReportFilterCamp {

    /**
     * Writes the report to a specified file name.
     * 
     * @param ID Either UserID or CampID
     * @param filename The file name that the report will be written to.
     */
    void write(String ID, String filename);

}
