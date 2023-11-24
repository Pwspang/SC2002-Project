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

    public void withdraw(String studentID) {
        if (!stuRegistered.contains(studentID)) throw new RuntimeException("Not registered.");
        stuRegistered.remove(studentID);
        occupiedSlots--;
    }

}