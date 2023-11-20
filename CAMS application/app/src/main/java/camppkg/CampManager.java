
package camppkg;
import java.util.*;

import authenticationpkg.AuthUser;
import authenticationpkg.Faculty;

import java.io.Serializable;
import java.time.LocalDate;

public class CampManager implements Serializable, iCampStaff, iCampStudent, iCampCommMember {

    // singleton constructor
    private static final CampManager campManager = new CampManager();
    private CampManager() {}
    public static CampManager getInstance() {
        return campManager;
    }

    private HashMap<String, Camp> campList = new HashMap<>();

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
        if (campList.get(campID) != null) throw new RuntimeException("Camp not created due to invalid Camp ID");
        Camp camp = new Camp(campID, campInfo);
        campList.put(campID, camp);
    }

    public void deleteCamp(String campID) {
        campList.remove(campID);
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
    
    public void setVisibility(String campID, boolean visibility) {
        Camp c = campList.get(campID);
        c.setVisibility(visibility);
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
            if (c.getVisibility() == false) continue;
            CampInformation campinfo = c.getCampInfo();
            if (campinfo.getOpenToWholeNTU() || campinfo.getUserGroup() == faculty) {
                result.add(campID);
            }
        }
        return result;
    }

    public ArrayList<String> getRegisteredCampList(String studentID) {
        ArrayList<String> result = new ArrayList<>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            CampInformation campinfo = c.getCampInfo();
            HashMap<String, Slots> allSlots = campinfo.getAllSlots();
            for (String roleID : allSlots.keySet()) {
                Slots s = allSlots.get(roleID);
                if (s.stuRegistered.contains(studentID)) {
                    result.add(campID);
                    break;
                }
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
            if (s.hasStudent(studentID)) result.add(campID);
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

    public boolean isClashing(String AcampID, String BcampID) {
        Camp campA = campList.get(AcampID);
        Camp campB = campList.get(BcampID);
        LocalDate startA = campA.getCampInfo().getStartDate();
        LocalDate endA = campA.getCampInfo().getEndDate();
        LocalDate startB = campB.getCampInfo().getStartDate();
        LocalDate endB = campB.getCampInfo().getEndDate();

        return !startA.isAfter(endB) && !endA.isBefore(startB);
    }
        
    public void register(String campID, String studentID, String roleID) {
        if (getRegisteredStudents(campID).contains(studentID)) {
            throw new RuntimeException(studentID + " is already registered for " + campID);
        }
        ArrayList<String> registeredCampList = getRegisteredCampList(studentID);
        for (String icampID : registeredCampList) {
            if (isClashing(campID, icampID)) {
                throw new RuntimeException(campID + " clashes with " + icampID);
            }
        }
        campList.get(campID).register(studentID, roleID);
    }

    public void withdraw(String campID, String studentID) {
        Camp myCamp = campList.get(campID);
        myCamp.withdraw(studentID);
    }

    // iCampCommMember and iStaff

    public ArrayList<String> getRegisteredStudents(String campID) {
        Camp c = campList.get(campID);
        return c.getRegisteredStudents();
    }

    public HashMap<String, String> getRegisteredStudentRoles(String campID) {
        Camp c = campList.get(campID);
        return c.getRegisteredStudentRoles();
    }

    public void editTotalSlots(String campID, int totalSlots){};
    
    public ArrayList<String> getAllCamps(){
        ArrayList<String> s = new ArrayList<String>();
        for (String key : campList.keySet()){
            s.add(key);
        }
        return s;
    };

    public void editVisibility(String campID, boolean openToWholeNTU){};
}
