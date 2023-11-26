package camppkg;
import java.util.*;

public interface iCampCommMember extends iCampStudent {
    
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
