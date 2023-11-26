
package camppkg;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a camp in the system.
 */
public class Camp implements Serializable {
    /**
     * The unique ID for this camp.
     */
    private String id;
    /**
     * The visibility of the camp. Auto set to False when Camp is first created.
     */
    private Boolean visibility;
    /**
     * The CampInformation object which students can view.
     */
    private CampInformation campinfo;
    /**
     * An ArrayList of students withdrawn from this camp.
     */
    private ArrayList<String> stuWithdrawn = new ArrayList<>();

    /**
     * Constructs a new Feedback with the specified user ID, camp ID, and content.
     * The feedback ID is automatically generated and incremented for each new Feedback.
     *
     * @param campID The ID of the camp the feedback is associated with.
     * @param campinfo The CampInformation wrapper object for the supplied information.
     */
    public Camp(String campID, CampInformation campinfo) {
        this.id = campID;
        this.visibility = true;
        this.campinfo = campinfo;
    }

    /**
     * @return ID of the Camp object.
     */
    public String getID() {
        return this.id;
    }

    /**
     * @return The CampInformation wrapper object for the supplied information.
     */
    public CampInformation getCampInfo() {
        return this.campinfo;
    }

    /**
     * @return The visibility of this camp. If the camp is open for students to view.
     */
    public Boolean getVisibility() {
        return this.visibility;
    }

    /**
     * @return ArrayList of students who previously withdrawn from this Camp.
     */
    public ArrayList<String> getStuWithdrawn() {
        return stuWithdrawn;
    }

    /**
     * @return ArrayList of students who are registered to this Camp.
     */
    public ArrayList<String> getRegisteredStudents() {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (String roleID : allSlots.keySet()) {
            Slots s = allSlots.get(roleID);
            result.addAll(s.getStuRegistered());
        }
        return result;
    }

    /**
     * @return HashMap of registered students and their associated role.
     */
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

    /**
     * Cannot set visibility to False if there are already students registered to the Camp.
     * @param visibility sets the visibility of this camp
     */
    public void setVisibility(Boolean visibility) {
        ArrayList<String> stuRegistered = getRegisteredStudents();
        if (!stuRegistered.isEmpty() && visibility == false) {
            throw new RuntimeException("Camp with registered students cannot turn visibility off.");
        }
        this.visibility = visibility;
    }

    /**
     * Register function for this camp.
     * @param studentID ID of the student registering
     * @param roleID ID of the desired role
     */
    public void register(String studentID, String roleID) {
        ArrayList<String> stuRegistered = getRegisteredStudents();
        if (stuRegistered.contains(studentID)) throw new RuntimeException("Already registered.");
        if (stuWithdrawn.contains(studentID)) throw new RuntimeException("Cannot rejoin after withdrawal.");
        Slots mySlots = (Slots) campinfo.getSlotsFor(roleID);
        mySlots.register(studentID);
    }

    /**
     * Withdraw function for this camp.
     * @param studentID ID of the withdrawing student
     */
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

    /**
     * Returns a string representation of the Camp.
     *
     * @return a string representation of the Camp
     */
    @Override
    public String toString(){
        return campinfo.toString();
    }
    

}
