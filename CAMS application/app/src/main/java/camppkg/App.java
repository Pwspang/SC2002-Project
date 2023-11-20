package camppkg;
import java.io.*;
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

        System.out.println("\n=== getAllCamps ===");
        System.out.println(campManager.getAllCamps().toString());

        System.out.println("\n=== getCreatedCamps of STAFFKUN ===");
        System.out.println(campManager.getCreatedCamps("STAFFKUN"));

        System.out.println("\n=== get information on SCSEOCamp ===");
        System.out.println(campManager.getCampInfo("SCSEOCamp").toString());

        System.out.println("\n=== getVisibleCamps for SCSE students ===");
        campManager.getVisibleCampList(Faculty.SCSE);
        System.out.println("\n=== Oops we forgot to On visibility. Let's try again. ===");
        campManager.getCamp("HallOCamp").setVisibility(true);
        campManager.getCamp("SCSEOCamp").setVisibility(true);
        System.out.println(campManager.getVisibleCampList(Faculty.SCSE).toString());

        String studentA = "Ben Dover";
        String studentB = "Hugh Jass";
        System.out.println("\n=== Register Ben in both camps ===");
        campManager.register("HallOCamp", studentA, "CCMember");
        try { campManager.register("SCSEOCamp", studentA, "CCMember"); }
        catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}

        System.out.println("\n=== Let's push SCSEOCamp back. ===");
        campManager.editDate("SCSEOCamp", "2000-02-01", "2020-03-01");
        System.out.println(campManager.getCampInfo("SCSEOCamp").toString());

        System.out.println("\n=== Let's register for the same camp as Attendee. ===");
        try { campManager.register("HallOCamp", studentA, "Attendee"); }
        catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}

        System.out.println("\n=== Let's register for a different camp as CCMember. ===");
        campManager.register("SCSEOCamp", studentA, "CCMember");
        System.out.println("\n=== It works because we don't check if he is CCMember elsewhere. ===");
        campManager.register("HallOCamp", studentB, "Attendee");
        campManager.register("SCSEOCamp", studentB, "Attendee");

        System.out.println("\n=== Let's check their registered camps. ===");
        System.out.println(campManager.getRegisteredCampList(studentB).toString());
        System.out.println(campManager.getRegisteredCampList(studentA, "CCMember").toString());

        System.out.println("\n=== What if Ben withdraws?. ===");
        try { campManager.withdraw("SCSEOCamp", studentA); }
        catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}

        System.out.println("\n=== Hugh can withdraw as attendee, but can't register again. ===");
        try { campManager.withdraw("SCSEOCamp", studentB);
        campManager.register("SCSEOCamp", studentB, "CCMember");
        } catch(Exception e) {System.out.println("Your problem: " + e.getMessage() + "\n");}
        
        System.out.println("\n=== What the staff sees. ===");
        System.out.println(campManager.getRegisteredStudents("HallOCamp").toString());
        System.out.println(campManager.getRegisteredStudentRoles("HallOCamp").toString());

        System.out.println("\n=== Let's save the file. ===");
        String filename = "myCMfile.ser";
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(campManager);
        out.close();
        file.close();
        System.out.println("Object has been serialized");

        System.out.println("\n=== Let's read the file. ===");
        FileInputStream file2 = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file2);
        CampManager object1 = (CampManager)in.readObject();
        in.close();
        file.close();
        System.out.println("Object has been deserialized");
        System.out.println(object1.getAllCamps().toString()); // it works!
    }
}
