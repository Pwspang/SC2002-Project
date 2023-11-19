
package camppkg;
import java.util.*;

/**
 *  Class of CampInformation. 
 *  Contain
 */
public abstract class Slots {
    private int totalSlots;
    private int occupiedSlots;
    private int remainingSlots;
    protected ArrayList<String> stuRegistered = new ArrayList<>();

    /**
	 * Constructs a Camp object based on CampInformation.
	 * @param id Unique Camp name
	 * @param CampInformation Information needed to construct Camp
	 */

    public Slots(int totalSlots) {
        this.totalSlots = totalSlots;
        this.occupiedSlots = 0;
        this.remainingSlots = totalSlots;
    }

    public String toString() {
        return (
            "\tTotal slots: " + totalSlots + "\n" +
            "\tOccupied slots: " + occupiedSlots + "\n" +
            "\tRemaining slots: " + remainingSlots + "\n");
    }

    public String getRoleID() {
        return "";
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public int getRemainingSlots() {
        return remainingSlots;
    }

    public ArrayList<String> getStuRegistered() {
        return stuRegistered;
    }

    public void setTotalSlots(int totalSlots) {
        if (this.occupiedSlots > totalSlots) throw new IllegalArgumentException(
            String.format("There are already %d occupied.", this.occupiedSlots));
        this.totalSlots = totalSlots;
    }

    public void addCount(int delta) {
        this.occupiedSlots += delta;
        this.remainingSlots -= delta;
    }
        
    public void register(String studentID) {
        if (this.remainingSlots <= 0) throw new RuntimeException("No vacancy.");
        this.stuRegistered.add(studentID);
        this.addCount(1);
    }

    public void withdraw(String studentID) {
        throw new RuntimeException("Cannot withdraw from this role");
    }

    public boolean hasStudent(String studentID) {
        return stuRegistered.contains(studentID);
    }

}