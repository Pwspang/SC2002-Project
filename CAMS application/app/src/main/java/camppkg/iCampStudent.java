package camppkg;
import java.util.*;
import authenticationpkg.Faculty;

interface iCampStudent {
    
    ArrayList<String> getVisibleCampList(Faculty faculty);
    
    ArrayList<String> getRegisteredCampList(String studentID, String roleID);

    CampInformation getCampInfo(String campID);

    void register(String campID, String studentID, String roleID);

    void withdraw(String campID, String studentID);

}
