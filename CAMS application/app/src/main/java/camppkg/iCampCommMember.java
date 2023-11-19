package camppkg;
import java.util.*;

interface iCampCommMember extends iCampStudent {
    
    ArrayList<String> getRegisteredStudents(String campID);

    HashMap<String, String> getRegisteredStudentRoles(String campID);
}
