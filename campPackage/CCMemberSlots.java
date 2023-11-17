package camppackage;
import java.util.*;

public class CCMemberSlots extends Slots {
    private static final int maxTotalSlots = 10;

    /**
	 * Constructs a Camp object based on CampInformation.
	 * @param id Unique Camp name
	 * @param CampInformation Information needed to construct Camp
	 */
    public CCMemberSlots(int totalSlots) {
        super(totalSlots);
        if (totalSlots > maxTotalSlots) throw new IllegalArgumentException(
            String.format("Total slots should not be more than %d", totalSlots));
    }

    public int getTotalSlots(int totalSlots) {
        return maxTotalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        if (totalSlots > maxTotalSlots) throw new IllegalArgumentException(
            String.format("Total slots should not be more than %d", totalSlots));
        super.setTotalSlots(totalSlots);
    }

}