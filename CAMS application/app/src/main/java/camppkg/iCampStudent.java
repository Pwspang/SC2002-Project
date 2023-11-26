package camppkg;
import java.util.*;
import authenticationpkg.Faculty;

public interface iCampStudent {
    
    /**
	 * Get the list of camps visible for the student of a certain faculty.
	 * 
	 * @param faculty the faculty of the viewing Student
     * @return ArrayList of campID visible to students of the faculty
	 */
    ArrayList<String> getVisibleCampList(Faculty faculty);
    
    /**
	 * Get the list of registered camps for a particular student regardless of role.
	 * 
	 * @param studentID ID of the Student user.
     * @return ArrayList of campID of the registered camps.
	 */
    ArrayList<String> getRegisteredCampList(String studentID, String roleID);

    /**
	 * Get the camp information of a cmap.
	 * 
	 * @param campID ID of the target camp.
     * @return CampInformation object for the target camp.
	 */
    CampInformation getCampInfo(String campID);

    /**
	 * Get the Camp object associated with the campID.
	 * 
	 * @param campID ID of the target camp.
     * @return Camp object.
	 */
    Camp getCamp(String campID);

    /**
	 * Allow a student to register for a role in a camp.
     * Student cannot register if they have already registered or if registeration period is over.
     * Or if the camp clashes with one of the other camps they have already registered for.
	 * 
	 * @param campID ID of the target camp
     * @param studentID ID of the student registering
     * @param roleID ID of the desired role
	 */
    void register(String campID, String studentID, String roleID);

    /**
	 * Allow a student to withdraw from a camp.
	 * 
	 * @param campID ID of the target camp
     * @param studentID ID of the student withdrawing
	 */
    void withdraw(String campID, String studentID);

}
