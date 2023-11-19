package camppkg;
import java.util.*;
import authenticationpkg.Faculty;

public interface iCampStaff {

    void createCamp(String staffID, String campName, String startDate, String endDate, String registrationClosingDate,
            boolean openToWholeNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description);

    void deleteCamp(String campID);

    HashMap<String, Camp> getAllCamps();

    ArrayList<String> getCreatedCamps(String staffID);

    void editDate(String campID, String startDate, String endDate);

    void editRegistrationClosingDate(String campID, String registrationClosingDate);

    void editOpenTo(String campID, boolean openToWholeNTU);

    void editOpenTo(String campID, Faculty faculty);

    void editLocation(String campID, String location);

    void editSlots(String campID, String roleID, int totalSlots);

    void editCampCommitteeSlots(String campID, int campCommitteeSlots);

    void editDescription(String campID, String description);

    ArrayList<String> getRegisteredStudents(String campID);

    HashMap<String, String> getRegisteredStudentRoles(String campID);

}