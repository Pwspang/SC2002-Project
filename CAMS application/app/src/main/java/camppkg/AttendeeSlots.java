package camppkg;
import java.util.*;

/**
 * AttendeeSlots represents the slots details for the role Attendee.
 * This class inherits from Slots.
 */
public class AttendeeSlots extends Slots {
    /**
     * Stores the lsit of students that withdraw from the camp
     */
    protected static ArrayList<String> stuWithdrawn;

    /**
     * Constructor to instantiate AttendeeSlots with the given parameters.
     * 
     * @param totalSlots the total available slots in a camp for the role Attendee.
     */
    public AttendeeSlots(int totalSlots) {
        super(totalSlots);
    }

    /**
	 * Withdraw from this role of this camp.
	 * 
	 * @param studentID ID of the withdrawing student
	 */
    public void withdraw(String studentID) {
        if (!stuRegistered.contains(studentID)) throw new RuntimeException("Not registered.");
        stuRegistered.remove(studentID);
        occupiedSlots--;
    }

}