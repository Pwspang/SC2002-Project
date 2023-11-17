
package camppackage;
import java.util.*;
//import authenticationpkg.*;

public class CampInformation {
    private String campName;
    private String startDate;
    private String endDate;
    private String registrationClosingDate;
    private boolean openToWholeNTU;
    private Faculty userGroup;
    private String location;
    private String description;
    private String staffInCharge;
    private HashMap<String, Slots> slots;

    public CampInformation() {
        this(
        "generic campName", 
        "generic startDate",
        "generic endDate", 
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
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.openToWholeNTU = openToWholeNTU;
        this.userGroup = userGroup;
        this.location = location;
        this.description = description;
        this.staffInCharge = staffInCharge;

        this.slots = new HashMap<String, Slots>();
        this.slots.put("CCMember", new CCMemberSlots(campCommitteeSlots));
        this.slots.put("Attendee", new AttendeeSlots(totalSlots-campCommitteeSlots));
    }

    public String getCampName() {
        return campName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRegisterationClosingDate() {
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

    public String getStaffInCharge(String staffInCharge) {
        return staffInCharge;
    }


    public HashMap<String, Slots> getSlots() {
        return slots;
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
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRegisterationClosingDate(String registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public void setOpenToWholeNTU(boolean openToWholeNTU) {
        this.openToWholeNTU = openToWholeNTU;
    }

    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}