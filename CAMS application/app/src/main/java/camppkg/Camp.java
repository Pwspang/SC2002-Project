
package camppkg;

import java.util.*;

public class Camp {
    private String id;
    private boolean visibility;
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

    public boolean getVisibility() {
        return this.visibility;
    }

    public void toggleVisibility(boolean visibility) {
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

    @Override
    public String toString(){
        return """
            Camp Name                   : %s
            Start Date                  : %s
            End Date                    : %s
            Registration Closing Date   : %s
            Faculty                     : %s
            Location                    : %s
            Total Slots                 : %d
            Camp Comittee Slots         : %d
            Description                 : %s
        """.formatted(campinfo.getCampName(), 
        campinfo.getStartDate(), 
        campinfo.getEndDate(),
        campinfo.getRegisterationClosingDate(),
        "User Group",
        campinfo.getLocation(),
        campinfo.getTotalSlots(),
        "Camp comitte Slots",
        campinfo.getDescription()
        );
    }
    

}
