
package camppkg;
import java.io.Serializable;
import java.util.*;

/**
 *  Class of Slots Information. 
 *  Stores the details of the slots available for a role.
 */
public abstract class Slots implements Serializable {

    /**
     * Total number of slots available.
     */
    private Integer totalSlots;

    /**
     * Number of slots registered.
     */
    protected Integer occupiedSlots;

    /**
     * List of ID of students registered for this camp.
     */
    protected ArrayList<String> stuRegistered = new ArrayList<>();

    /**
	 * Constructs a Slots object.
	 * @param totalSlots Unique Camp name
	 */
    public Slots(int totalSlots) {
        this.totalSlots = totalSlots;
        this.occupiedSlots = 0;
    }

    /**
     * Returns a string summary of the Slots.
     *
     * @return a string summary of the Slots
     */
    public String toString() {
        return (
            "\tTotal slots: " + totalSlots + "\n" +
            "\tOccupied slots: " + occupiedSlots + "\n" +
            "\tRemaining slots: " + this.getRemainingSlots() + "\n");
    }

    /**
     * @return the total number of slots available
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * @return number of slots registered.
     */
    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    /**
     * @return number of slots left
     */
    public int getRemainingSlots() {
        return totalSlots - occupiedSlots;
    }

    /**
     * @return list of ID of students registered
     */
    public ArrayList<String> getStuRegistered() {
        return stuRegistered;
    }

    /**
     * @param totalSlots the total number of slots available
     */
    public void setTotalSlots(int totalSlots) {
        if (this.occupiedSlots > totalSlots) throw new IllegalArgumentException(
            String.format("There are already %d occupied.", this.occupiedSlots));
        this.totalSlots = totalSlots;
    }
    
    /**
     * Register a student to this role.
     * @param studentID ID of the student registering.
     */
    public void register(String studentID) {
        if (this.getRemainingSlots() <= 0) throw new RuntimeException("No vacancy.");
        this.stuRegistered.add(studentID);
        occupiedSlots++;
    }

    /**
     * Withdraw a student from this role.
     * @param studentID ID of the student withdrawing.
     */
    public void withdraw(String studentID) {
        throw new RuntimeException("Cannot withdraw from this role.");
    }

    /**
     * Check if student is registered to this role
     * @param studentID ID of the student registering.
     * @return if student is registered to this role
     */
    public boolean hasStudent(String studentID) {
        return stuRegistered.contains(studentID);
    }

}