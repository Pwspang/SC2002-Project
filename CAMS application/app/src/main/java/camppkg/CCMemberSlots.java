package camppkg;

public class CCMemberSlots extends Slots {
    /**
     * Maximum total slots that can be set for this role.
     */
    private static final int maxTotalSlots = 10;

    /**
     * Constructor to instantiate CCMemberSlots with the given parameters.
     * 
     * @param totalSlots the total available slots in a camp for the role Camp Committee Member.
     */
    public CCMemberSlots(int totalSlots) {
        super(totalSlots);
        if (totalSlots > maxTotalSlots) throw new IllegalArgumentException(
            String.format("Total slots should not be more than %d", totalSlots));
    }

    /**
     * @return get the maximum total slots the Staff can set to for this role.
     */
    public int getMaxTotalSlots() {
        return maxTotalSlots;
    }

    /**
     * For staff to set the total slots for this role.
     * Additional condition of being under maxTotalSlots
     * @param totalSlots the intended total slots for the role
     */
    @Override
    public void setTotalSlots(int totalSlots) {
        if (totalSlots > maxTotalSlots) throw new IllegalArgumentException(
            String.format("Total slots should not be more than %d", totalSlots));
        super.setTotalSlots(totalSlots);
    }

}