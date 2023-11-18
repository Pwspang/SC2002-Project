
package camppkg;
import java.util.*;

public class Camp {
    private String id;
    private boolean visibility;
    private CampInformation campinfo;
    private ArrayList<String> stuWithdrawn;

    public Camp(String campID, CampInformation campinfo) {
        this.id = campID;
        this.visibility = false;
        this.campinfo = campinfo;
        this.stuWithdrawn = new ArrayList<>();
    }

    public String getID() {
        return this.id;
    }

    public CampInformation getCampInfo() {
        return this.campinfo;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void toggleVisibility() {
        visibility = !visibility;
    }

    public void register(String studentID, String roleID) {
        if (!stuWithdrawn.contains(studentID)) throw new RuntimeException("Cannot rejoin after withdrawal.");
        Slots mySlots = (Slots) campinfo.getSlotsFor(roleID);
        mySlots.register(studentID);
    }

    public void withdraw(String studentID) {
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (Slots mySlots : allSlots.values()) {
            if (mySlots.stuRegistered.contains(studentID)) {
                mySlots.withdraw(studentID);
                return;
            }
        }
    }

}