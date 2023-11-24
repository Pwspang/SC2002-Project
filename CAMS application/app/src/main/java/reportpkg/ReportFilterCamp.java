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

public abstract class ReportFilterCamp {

    /**
     * ID is dependent on the type of filter used.
     * Possible IDs: campID, userID.
     */
    private String ID;

    /**
     * Creates a new ReportFilterCamp with the given parameters.
     * This is an abstract class that cannot be instantiated.
     */
    public ReportFilterCamp(String ID) {
        this.ID = ID;
    }

    /**
     * Gets the ID of the search term for ReportFilterCamp.
     * 
     * @return The ID of the search term.
     */
    public String getID() {
        return ID;

    }

    /**
     * Sets the ID of the search term for ReportFilterCamp.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Writes the report to a specified file name.
     * 
     * @param filename The file name that the report will be written to.
     */
    public abstract void write(String filename);

}
