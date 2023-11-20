
package camppkg;
import java.io.Serializable;
import java.util.*;

public class Camp implements Serializable {
    private String id;
    private Boolean visibility;
    private CampInformation campinfo;
    private ArrayList<String> stuWithdrawn = new ArrayList<>();

    public Camp(String campID, CampInformation campinfo) {
        this.id = campID;
        this.visibility = false;
        this.campinfo = campinfo;
    }

    public String getID() {
        return this.id;
    }

    public CampInformation getCampInfo() {
        return this.campinfo;
    }

    public Boolean getVisibility() {
        return this.visibility;
    }

    public ArrayList<String> getStuWithdrawn() {
        return stuWithdrawn;
    }

    public ArrayList<String> getRegisteredStudents() {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (String roleID : allSlots.keySet()) {
            Slots s = allSlots.get(roleID);
            result.addAll(s.getStuRegistered());
        }
        return result;
    }

    public HashMap<String, String> getRegisteredStudentRoles() {
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (String roleID : allSlots.keySet()) {
            Slots s = allSlots.get(roleID);
            for (String student : s.getStuRegistered()) {
                result.put(student, roleID);
            }
        }
        return result;
    }

    public void toggleVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public void register(String studentID, String roleID) {
        if (stuWithdrawn.contains(studentID)) throw new RuntimeException("Cannot rejoin after withdrawal.");
        Slots mySlots = (Slots) campinfo.getSlotsFor(roleID);
        mySlots.register(studentID);
    }

    public void withdraw(String studentID) {
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (Slots mySlots : allSlots.values()) {
            if (mySlots.stuRegistered.contains(studentID)) {
                mySlots.withdraw(studentID);
                stuWithdrawn.add(studentID);
                return;
            }
        }
    }

}