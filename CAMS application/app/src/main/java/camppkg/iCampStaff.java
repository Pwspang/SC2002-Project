package camppkg;
import java.util.*;
import authenticationpkg.Faculty;
/**
 * Interface for Staff to access camp manager
 */
public interface iCampStaff {

    /** 
     * Create camp
     * @param staffID Staff ID
     * @param campName Camp Name    
     * @param startDate Start Date      
     * @param endDate End Date
     * @param registrationClosingDate Registration closing date
     * @param openToWholeNTU If open to whole NTU 
     * @param userGroup Faculty of staff
     * @param location Location 
     * @param totalSlots Total number of slots
     * @param campCommitteeSlots Number of CC Slots
     * @param description Description for camp
     */
    void createCamp(String staffID, String campName, String startDate, String endDate, String registrationClosingDate,
            boolean openToWholeNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description);

    /**
	 * For Staff to delete a camp.
     * Cannot delete a camp that already has students registered.
	 * 
	 * @param campID ID of the camp to be deleted.
	 */
    void deleteCamp(String campID);

    /**
	 * Returns a list of IDs of all camps in the system.
	 * 
     * @return ArrayList of campID in the system
	 */
    ArrayList<String> getAllCamps();

    /**
	 * Get the camps created by a Staff.
     * Staff can create multiple camps.
	 * 
	 * @param staffID Staff's userID based on login credentials.
	 * @return ArrayList object of camps created.
	 */
    ArrayList<String> getCreatedCamps(String staffID);

    /**
	 * Edit the start and end dates of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param startDate new camp starting date to set to
     * @param endDate new camp ending date to set to
	 */
    void editDate(String campID, String startDate, String endDate);

    /**
	 * Edit the registration closing date of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param registrationClosingDate new camp registration closing date to set to
	 */
    void editRegistrationClosingDate(String campID, String registrationClosingDate);

    /**
	 * Edit the visibility of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param visiblity new visibility to set to
	 */
    void setVisibility(String campID, boolean visiblity);

    /**
	 * Edit the target student group who can join.
	 * 
	 * @param campID campID of the target camp.
     * @param openToWholeNTU if the student group is the whole of NTU, or just the Faculty
	 */
    void editOpenTo(String campID, boolean openToWholeNTU);
    
    /**
	 * Edit the location of the camp.
	 * 
	 * @param campID campID of the target camp.
     * @param location new location of the camp.
	 */
    void editLocation(String campID, String location);

    /**
	 * Edit the total number of slots for a particular role of a camp.
     * Cannot set it to be lower than the number of students already registered for the role.
	 * 
	 * @param campID campID of the target camp.
     * @param roleID ID of the role to be edited
     * @param totalSlots total number of slots to be set
	 */
    void editSlots(String campID, String roleID, int totalSlots);

    /**
	 * Edit the total number of slots for Camp Committee Members of a camp.
     * Cannot set it to be lower than the number of students already registered for the role.
	 * 
	 * @param campID campID of the target camp.
     * @param campCommitteeSlots total number of slots to be set
	 */
    void editCampCommitteeSlots(String campID, int campCommitteeSlots);

    /**
	 * Edit the description for the camp.
	 * 
	 * @param campID campID of the target camp.
     * @param description the new description of the camp
	 */
    void editDescription(String campID, String description);

    /**
	 * Returns a list of students registered for a camp
     * Accessible only for Camp Committee Members and Staff overseeing the camp.
	 * 
	 * @param campID ID of the target camp
     * @return ArrayList of StudentID
	 */
    ArrayList<String> getRegisteredStudents(String campID);

    /**
	 * Returns a list of students registered for a camp and their associated roles
     * Accessible only for Camp Committee Members and Staff overseeing the camp.
	 * 
	 * @param campID ID of the target camp
     * @return HashMap of StudentID and their associated RoleID
	 */
    HashMap<String, String> getRegisteredStudentRoles(String campID);

}
