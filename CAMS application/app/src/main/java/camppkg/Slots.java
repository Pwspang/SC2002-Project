
package camppkg;
import java.io.Serializable;
import java.util.*;

/**
 *  Class of CampInformation. 
 *  Contain
 */
public abstract class Slots implements Serializable {
    private Integer totalSlots;
    protected Integer occupiedSlots;
    protected ArrayList<String> stuRegistered = new ArrayList<>();

    /**
	 * Constructs a Camp object based on CampInformation.
	 * @param id Unique Camp name
	 * @param CampInformation Information needed to construct Camp
	 */

    public Slots(int totalSlots) {
        this.totalSlots = totalSlots;
        this.occupiedSlots = 0;
    }

    public String toString() {
        return (
            "\tTotal slots: " + totalSlots + "\n" +
            "\tOccupied slots: " + occupiedSlots + "\n" +
            "\tRemaining slots: " + this.getRemainingSlots() + "\n");
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
        return totalSlots - occupiedSlots;
    }

    public ArrayList<String> getStuRegistered() {
        return stuRegistered;
    }

    public void setTotalSlots(int totalSlots) {
        if (this.occupiedSlots > totalSlots) throw new IllegalArgumentException(
            String.format("There are already %d occupied.", this.occupiedSlots));
        this.totalSlots = totalSlots;
    }
        
    public void register(String studentID) {
        if (this.getRemainingSlots() <= 0) throw new RuntimeException("No vacancy.");
        this.stuRegistered.add(studentID);
        occupiedSlots++;
    }

    public void withdraw(String studentID) {
        throw new RuntimeException("Cannot withdraw from this role.");
    }

    public boolean hasStudent(String studentID) {
        return stuRegistered.contains(studentID);
    }

}