package camppkg;
import java.util.*;

public class AttendeeSlots extends Slots {
    protected static ArrayList<String> stuWithdrawn;

    /**
	 * Constructs a Camp object based on CampInformation.
	 * @param id Unique Camp name
	 * @param CampInformation Information needed to construct Camp
	 */
    public AttendeeSlots(int totalSlots) {
        super(totalSlots);
    }

    public void register(String studentID) {
        if (!stuWithdrawn.contains(studentID)) throw new RuntimeException("Cannot rejoin after withdrawal.");
        super.register(studentID);
    }

    public void withdraw(String studentID) {
        if (!stuRegistered.contains(studentID)) throw new RuntimeException("Not registered.");
        stuRegistered.remove(studentID);
        stuWithdrawn.add(studentID);
        this.addCount(-1);
    }

}