
package camppkg;
import java.util.*;
import authenticationpkg.Faculty;


public class CampManager implements iCampStaff, iCampStudent, iCampCommMember {

    // singleton constructor
    private static final CampManager campManager = new CampManager();
    private CampManager() {}
    public static CampManager getInstance() {
        return campManager;
    }

    private HashMap<String, Camp> campList;

    public void createCamp(String staffID, String campName, String startDate, String endDate, String registrationClosingDate,
            boolean openToWholeNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description) {
            
            CampInformation campinfo = new CampInformation(
                campName, 
                startDate,
                endDate, 
                registrationClosingDate, 
                openToWholeNTU,
                userGroup,
                location,
                totalSlots,
                campCommitteeSlots,
                description,
                staffID);
            createCamp(campinfo);
    }

    public void createCamp(CampInformation campInfo) {
        String campID = campInfo.getCampName();
        if (campList.get(campID) == null) throw new RuntimeException("Non-unique CampID");
        Camp camp = new Camp(campID, campInfo);
        campList.put(campID, camp);
    }

    public void deleteCamp(String campID) {
        campList.remove(campID);
    }

    public HashMap<String, Camp> getAllCamps() {
        return campList;
    }

    public ArrayList<String> getCreatedCamps(String staffID) {
        ArrayList<String> result = new ArrayList<>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            if (staffID == c.getCampInfo().getStaffInCharge()) result.add(campID);
        }
        return result;
    }

    public void editDate(String campID, String startDate, String endDate) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setStartDate(startDate);
        campinfo.setEndDate(endDate);
    }

    public void editRegistrationClosingDate(String campID, String registrationClosingDate) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setRegisterationClosingDate(registrationClosingDate);
    }
    
    public void toggleVisibility(String campID, boolean openToWholeNTU) {
        Camp c = campList.get(campID);
        c.toggleVisibility();
    }

    public void editOpenTo(String campID, boolean openToWholeNTU) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setOpenToWholeNTU(openToWholeNTU);
    }

    public void editOpenTo(String campID, Faculty faculty) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setUserGroup(faculty);
    }

    public void editLocation(String campID, String location) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setLocation(location);
    }

    public void editSlots(String campID, String roleID, int totalSlots) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        Slots s = campinfo.getSlotsFor(roleID);
        s.setTotalSlots(totalSlots);
    }

    public void editCampCommitteeSlots(String campID, int campCommitteeSlots) {
        editSlots(campID, "CCMember", campCommitteeSlots);
    }

    public void editDescription(String campID, String description) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setDescription(description);
    }

    // iCampStudent

    public ArrayList<String> getVisibleCampList(Faculty faculty) {
        ArrayList<String> result = new ArrayList<>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            CampInformation campinfo = c.getCampInfo();
            if (campinfo.getOpenToWholeNTU() || campinfo.getUserGroup() == faculty) {
                result.add(campID);
            }
        }
        return result;
    }

    public ArrayList<String> getRegisteredCampList(String studentID, String roleID) {
        ArrayList<String> result = new ArrayList<>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            CampInformation campinfo = c.getCampInfo();
            Slots s = campinfo.getSlotsFor(roleID);
            if (s.hasStudent(studentID)) result.add(studentID);
            continue;
        }
        return result;
    }

    public CampInformation getCampInfo(String campID) {
        Camp c = campList.get(campID);
        return c.getCampInfo();
    }

    public Camp getCamp(String campID) {
        return campList.get(campID);
    }
        
    public void register(String campID, String studentID, String roleID) {
        Camp myCamp = campList.get(campID);
        myCamp.register(studentID, roleID);
    }

    public void withdraw(String campID, String studentID) {
        Camp myCamp = campList.get(campID);
        myCamp.withdraw(studentID);
    }

    // iCampCommMember and iStaff

    public ArrayList<String> getRegisteredStudents(String campID) {
        ArrayList<String> result = new ArrayList<>();
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (String roleID : allSlots.keySet()) {
            Slots s = allSlots.get(roleID);
            result.addAll(s.getStuRegistered());
        }
        return result;
    }

    public HashMap<String, String> getRegisteredStudentRoles(String campID) {
        HashMap<String, String> result = new HashMap<>();
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        HashMap<String, Slots> allSlots = campinfo.getAllSlots();
        for (String roleID : allSlots.keySet()) {
            Slots s = allSlots.get(roleID);
            for (String student : s.getStuRegistered()) {
                result.put(student, roleID);
            }
        }
        return result;
    }
    
}