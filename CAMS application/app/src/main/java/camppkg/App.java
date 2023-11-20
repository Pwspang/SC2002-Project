package camppkg;
import java.util.*;
import authenticationpkg.Faculty;

public class App {
    public static void main(){
        System.out.println("====Testing Begins Lesgo!====");
        CampManager campManager = CampManager.getInstance();

        campManager.createCamp(
            "STAFFSAN",
            "HallOCamp",
            "2000-01-01",
            "2000-01-29",
            "1999-12-02",
            true,
            Faculty.NBS,
            "Sentosa",
            1234,
            10,
            "Fun camp for Hall freshmen."
            );
        
        campManager.createCamp(
            "STAFFKUN",
            "SCSEOCamp",
            "2000-01-20",
            "2000-01-29",
            "1999-12-02",
            false,
            Faculty.SCSE,
            "Suntec Convention",
            1234,
            10,
            "Fun camp for SCSE freshmen."
            );

        System.out.println(campManager.getAllCamps().toString());
        System.out.println(campManager.getCreatedCamps("STAFFKUN"));
        System.out.println(campManager.getCampInfo("SCSEOCamp").toString());

        String studentA = "Ben Dover";
        String studentB = "Hugh Jass";
        campManager.register("HallOCamp", studentA, "CCMember");
        try { campManager.register("SCSEOCamp", studentA, "CCMember"); }
        catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}

        // SCSEOCamp clashes with HallOCamp
        // let's push SCSEOCamp back
        campManager.editDate("SCSEOCamp", "2000-02-01", "2020-03-01");
        System.out.println(campManager.getCampInfo("SCSEOCamp").toString());

        // register again
        campManager.register("SCSEOCamp", studentA, "CCMember");
        campManager.register("HallOCamp", studentB, "Attendee");
        campManager.register("SCSEOCamp", studentB, "Attendee");

        // maybe you should check if he is already a CC Member
        System.out.println(campManager.getRegisteredCampList(studentB).toString());
        System.out.println(campManager.getRegisteredCampList(studentA, "CCMember").toString());

        // What if they want to withdraw?
        try { campManager.withdraw("SCSEOCamp", studentA); }
        catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}

        // Attendee can withdraw, but cannot register again.
        try { campManager.withdraw("SCSEOCamp", studentB);
        campManager.register("SCSEOCamp", studentB, "CCMember");
        } catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}
        
        // what the staff sees
        System.out.println(campManager.getRegisteredStudents("HallOCamp").toString());
        System.out.println(campManager.getRegisteredStudentRoles("HallOCamp").toString());
    }
}
