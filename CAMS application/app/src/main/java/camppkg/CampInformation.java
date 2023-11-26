
package camppkg;
import java.io.Serializable;
import java.util.*;
import authenticationpkg.Faculty;
import java.time.LocalDate;

/**
 * CampInformation represents the wrapper for the information the staff supplies.
 * Also the information can be viewed by relevant students.
 */
public class CampInformation implements Serializable {
    private String campName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationClosingDate;
    private Boolean openToWholeNTU;
    private Faculty userGroup;
    private String location;
    private String description;
    private String staffInCharge;
    private HashMap<String, Slots> slots = new HashMap<>();

    /**
     * Constructs a new CampInformation object,
     *
     * @param campName the name of the Camp
     * @param startDate the start date of the camp event
     * @param endDate the end date of the camp event
     * @param registrationClosingDate the registration closing date
     * @param openToWholeNTU whether the camp is open to whole NTU
     * @param userGroup faculty that the camp is opened to
     * @param location location of the camp
     * @param totalSlots total number of slots for all roles
     * @param campCommitteeSlots total number of slots for Camp Committee Members
     * @param description description of the camp
     * @param staffInCharge Staff in charge of this camp
     */
    public CampInformation(
        String campName, 
        String startDate,
        String endDate, 
        String registrationClosingDate, 
        Boolean openToWholeNTU,
        Faculty userGroup,
        String location,
        int totalSlots,
        int campCommitteeSlots,
        String description,
        String staffInCharge) {

        this.campName = campName;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.registrationClosingDate = LocalDate.parse(registrationClosingDate);
        this.openToWholeNTU = openToWholeNTU;
        this.userGroup = userGroup;
        this.location = location;
        this.description = description;
        this.staffInCharge = staffInCharge;

        this.slots = new HashMap<String, Slots>();
        this.slots.put("CCMember", new CCMemberSlots(campCommitteeSlots));
        this.slots.put("Attendee", new AttendeeSlots(totalSlots-campCommitteeSlots));
    }

    /**
     * Returns a string representation of the CampInformation.
     * Also, includes a summary of the slots quota.
     *
     * @return a string representation of the CampInformation
     */
    @Override
    public String toString() {
        return "CampInformation{\n" +
                "campName='" + campName + "',\n" +
                "startDate='" + startDate + "',\n" +
                "endDate='" + endDate + "',\n" +
                "registrationClosingDate='" + registrationClosingDate + "',\n" +
                "openToWholeNTU='" + openToWholeNTU + "',\n" +
                "userGroup='" + userGroup + "',\n" +
                "location='" + location + "',\n" +
                "description='" + description + "',\n" +
                "staffInCharge='" + staffInCharge + "',\n" +
                getSlotsSummary() + "}\n";
    }

    /**
     * @return the name of the camp
     */
    public String getCampName() {
        return campName;
    }

    /**
     * @return the start date of the camp
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return the end date of the camp
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @return the registration closing date of the camp
     */
    public LocalDate getRegisterationClosingDate() {
        return registrationClosingDate;
    }

    /**
     * @return if the camp is opened to whole NTU
     */
    public boolean getOpenToWholeNTU() {
        return openToWholeNTU;
    }

    /**
     * @return the faculty the camp is opened to
     */
    public Faculty getUserGroup() {
        return userGroup;
    }

    /**
     * @return location where camp is held
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return description of the camp
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return staff in charge of the camp
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * @return HashMap object of slotsID to Slots object
     */
    public HashMap<String, Slots> getAllSlots() {
        return slots;
    }

    /**
     * @param roleID the role of the object
     * @return Slots object of the associated ID
     */
    public Slots getSlotsFor(String roleID) {
        return slots.get(roleID);
    }

    /**
     * @return Get the total number of slots of this camp.
     */
    public int getTotalSlots() {
        int result = 0;
        for (Slots s : slots.values()) {
            result += s.getTotalSlots();
        }
        return result;
    }

    /**
     * @return Get a summary of the number of slots of each role.
     */
    public String getSlotsSummary() {
        String result = "";
        for (HashMap.Entry<String, Slots> entry : slots.entrySet()) {
            String key = entry.getKey();
            Slots i = entry.getValue();
            result += key + " slots: \n" + i.toString();
        }
        return result;
    }

    // set methods

    /**
     * @param campName the name of the camp
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * @param startDate the start date of the camp
     */
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    /**
     * @param endDate the end date of the camp
     */
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);;
    }

    /**
     * @param registrationClosingDate the registration closing date of the camp
     */
    public void setRegisterationClosingDate(String registrationClosingDate) {
        this.registrationClosingDate = LocalDate.parse(registrationClosingDate);;
    }

    /**
     * @param openToWholeNTU if the camp is opened to whole NTU
     */
    public void setOpenToWholeNTU(boolean openToWholeNTU) {
        this.openToWholeNTU = openToWholeNTU;
    }

    /**
     * @param userGroup the faculty the camp is opened to
     */
    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * @param location location where camp is held
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @param description description of the camp
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}