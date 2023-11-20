
package camppkg;
import java.io.Serializable;
import java.util.*;
import authenticationpkg.Faculty;
import java.time.LocalDate;

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

    public CampInformation() {
        this(
        "generic campName", 
        "2000-01-01",
        "2000-01-01", 
        "generic registrationClosingDate", 
        true,
        Faculty.SCSE,
        "generic location",
        20,
        5,
        "generic description",
        "generic staffInCharge");
    }

    public CampInformation(
        String campName, 
        String startDate,
        String endDate, 
        String registrationClosingDate, 
        boolean openToWholeNTU,
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

    public String getCampName() {
        return campName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getRegisterationClosingDate() {
        return registrationClosingDate;
    }

    public boolean getOpenToWholeNTU() {
        return openToWholeNTU;
    }

    public Faculty getUserGroup() {
        return userGroup;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public HashMap<String, Slots> getAllSlots() {
        return slots;
    }

    public Slots getSlotsFor(String roleID) {
        return slots.get(roleID);
    }

    public int getTotalSlots() {
        int result = 0;
        for (Slots s : slots.values()) {
            result += s.getTotalSlots();
        }
        return result;
    }

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

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);;
    }

    public void setRegisterationClosingDate(String registrationClosingDate) {
        this.registrationClosingDate = LocalDate.parse(registrationClosingDate);;
    }

    public void setOpenToWholeNTU(boolean openToWholeNTU) {
        this.openToWholeNTU = openToWholeNTU;
    }

    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}