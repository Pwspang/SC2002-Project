
package camppkg;
import java.util.*;
import authenticationpkg.Faculty;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Manages a list of Camps in the system.
 * Implements Serializable for serialization and deserialization.
 * iCampStaff, iCampStudent, iCampCommMember interfaces for each user type.
 */
public class CampManager implements Serializable, iCampStaff, iCampStudent, iCampCommMember {

    /**
	 * An instance of CampManager.
	 */
    private static final CampManager campManager = new CampManager();

    /**
	 * File path that CampManager reads and writes to.
	 */
    private static final String filename = "src/main/resources/CampManager.dat";

    /**
	 * A HashMap with campID as key and Camp for value.
	 * This HashMap will contain all camps in the CAMS system.
	 */
    private HashMap<String, Camp> campList;

    /**
	 * Used to write serialised campList to the CampManager.dat file.
	 */
    public void writeSerializedObject() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(campList);
			out.close();
			System.out.println("Camp Manager Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    /**
	 * Reads from the CampManager.dat file to initialise campList.
	 * 
	 * @return HashMap object with campID as key, Camp as value.
	 */
	public HashMap<String, Camp> readSerializedObject() {
		HashMap<String, Camp> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (HashMap<String, Camp>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		// System.out.println(" Details Size: " + pDetails.size());
		// System.out.println();
		return pDetails;
	}

    /**
	 * Constructor method used to construct CampManager.
	 * Initialises the campList from the CampManager.dat file.
	 */
    private CampManager() {
        campList = readSerializedObject();
    }

    /**
	 * Gets an instance of the CampManager.
	 * Ensures that only a single instance of CampManager is created.
	 * 
	 * @return CampManager object
	 */
    public static CampManager getInstance() {
        return campManager;
    }

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

    /**
	 * For Staff to create a new camp.
	 * 
	 * @param campInfo Wrapper for all the information to be supplied by the camp creator.
	 */
    public void createCamp(CampInformation campInfo) {
        String campID = campInfo.getCampName();
        if (campList.containsKey(campID)) throw new RuntimeException("Non-unique CampID");
        Camp camp = new Camp(campID, campInfo);
        campList.put(campID, camp);
    }

    /**
	 * For Staff to delete a camp.
     * Cannot delete a camp that already has students registered.
	 * 
	 * @param campID ID of the camp to be deleted.
	 */
    public void deleteCamp(String campID) {
        ArrayList<String> stuRegistered = getRegisteredStudents(campID);
        if (!stuRegistered.isEmpty()) throw new RuntimeException("Cannot delete camp with students registered.");
        else campList.remove(campID);
    }

    /**
	 * Get the camps created by a Staff.
     * Staff can create multiple camps.
	 * 
	 * @param staffID Staff's userID based on login credentials.
	 * @return ArrayList object of camps created.
	 */
    public ArrayList<String> getCreatedCamps(String staffID) {
        ArrayList<String> result = new ArrayList<String>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            if (staffID.equals(c.getCampInfo().getStaffInCharge()) ) result.add(campID);
        }
        Collections.sort(result);
        return result;
    }

    /**
	 * Edit the start and end dates of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param startDate new camp starting date to set to
     * @param campID new camp ending date to set to
	 */
    public void editDate(String campID, String startDate, String endDate) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setStartDate(startDate);
        campinfo.setEndDate(endDate);
    }

    /**
	 * Edit the registration closing date of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param registrationClosingDate new camp registration closing date to set to
	 */
    public void editRegistrationClosingDate(String campID, String registrationClosingDate) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setRegisterationClosingDate(registrationClosingDate);
    }
    
    /**
	 * Edit the visibility of a camp.
	 * 
	 * @param campID campID of the target camp.
     * @param visibility new visibility to set to
	 */
    public void setVisibility(String campID, boolean visibility) {
        Camp c = campList.get(campID);
        c.setVisibility(visibility);
    }

    /**
	 * Edit the target student group who can join.
	 * 
	 * @param campID campID of the target camp.
     * @param openToWholeNTU if the student group is the whole of NTU, or just the Faculty
	 */
    public void editOpenTo(String campID, boolean openToWholeNTU) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setOpenToWholeNTU(openToWholeNTU);
    }

    /**
	 * Edit the location of the camp.
	 * 
	 * @param campID campID of the target camp.
     * @param location new location of the camp.
	 */
    public void editLocation(String campID, String location) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setLocation(location);
    }

    /**
	 * Edit the total number of slots for a particular role of a camp.
     * Cannot set it to be lower than the number of students already registered for the role.
	 * 
	 * @param campID campID of the target camp.
     * @param roleID ID of the role to be edited
     * @param totalSlots total number of slots to be set
	 */
    public void editSlots(String campID, String roleID, int totalSlots) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        Slots s = campinfo.getSlotsFor(roleID);
        s.setTotalSlots(totalSlots);
    }

    /**
	 * Edit the total number of slots for Camp Committee Members of a camp.
     * Cannot set it to be lower than the number of students already registered for the role.
	 * 
	 * @param campID campID of the target camp.
     * @param editCampCommitteeSlots total number of slots to be set
	 */
    public void editCampCommitteeSlots(String campID, int campCommitteeSlots) {
        editSlots(campID, "CCMember", campCommitteeSlots);
    }

    /**
	 * Edit the description for the camp.
	 * 
	 * @param campID campID of the target camp.
     * @param description the new description of the camp
	 */
    public void editDescription(String campID, String description) {
        Camp c = campList.get(campID);
        CampInformation campinfo = c.getCampInfo();
        campinfo.setDescription(description);
    }

    // iCampStudent

    /**
	 * Get the list of camps visible for the student of a certain faculty.
	 * 
	 * @param faculty the faculty of the viewing Student
     * @return ArrayList of campID visible to students of the faculty
	 */
    public ArrayList<String> getVisibleCampList(Faculty faculty) {
        ArrayList<String> result = new ArrayList<String>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            if (c.getVisibility() == false) continue;
            CampInformation campinfo = c.getCampInfo();
            if (campinfo.getOpenToWholeNTU() || campinfo.getUserGroup() == faculty) {
                result.add(campID);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
	 * Get the list of registered camps for a particular student regardless of role.
	 * 
	 * @param studentID ID of the Student user.
     * @return ArrayList of campID of the registered camps.
	 */
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
        Collections.sort(result);
        return result;
    }

    /**
	 * Get the list of registered camps for a particular student for a particular role.
	 * 
	 * @param studentID ID of the Student user.
     * @param roleID ID of role the student wishes to search for.
     * @return ArrayList of campID of the registered camps.
	 */
    public ArrayList<String> getRegisteredCampList(String studentID, String roleID) {
        ArrayList<String> result = new ArrayList<>();
        for (String campID : campList.keySet()) {
            Camp c = campList.get(campID);
            CampInformation campinfo = c.getCampInfo();
            Slots s = campinfo.getSlotsFor(roleID);
            if (s.hasStudent(studentID)) result.add(campID);
            continue;
        }
        Collections.sort(result);
        return result;
    }

    /**
	 * Get the camp information of a cmap.
	 * 
	 * @param campID ID of the target camp.
     * @return CampInformation object for the target camp.
	 */
    public CampInformation getCampInfo(String campID) {
        Camp c = campList.get(campID);
        return c.getCampInfo();
    }

    /**
	 * Get the Camp object associated with the campID.
	 * 
	 * @param campID ID of the target camp.
     * @return Camp object.
	 */
    public Camp getCamp(String campID) {
        return campList.get(campID);
    }

    /**
	 * Check if the dates of two camps clash.
	 * 
	 * @param AcampID ID of one camp.
     * @param BcampID ID of the second camp.
     * @return boolean value for if the camps clash
	 */
    public boolean isClashing(String AcampID, String BcampID) {
        Camp campA = campList.get(AcampID);
        Camp campB = campList.get(BcampID);
        LocalDate startA = campA.getCampInfo().getStartDate();
        LocalDate endA = campA.getCampInfo().getEndDate();
        LocalDate startB = campB.getCampInfo().getStartDate();
        LocalDate endB = campB.getCampInfo().getEndDate();

        return !startA.isAfter(endB) && !endA.isBefore(startB);
    }

    /**
	 * Check if the camp registeration period is over.
	 * 
	 * @param campID ID of the target camp
     * @return boolean value for if the camp registeration period is over
	 */
    public boolean isOver(String campID) {
        Camp c = campList.get(campID);
        LocalDate registerationClosingDate = c.getCampInfo().getRegisterationClosingDate();
        LocalDate today = LocalDate.now();
        return today.isAfter(registerationClosingDate);
    }
    
    /**
	 * Allow a student to register for a role in a camp.
     * Student cannot register if they have already registered or if registeration period is over.
     * Or if the camp clashes with one of the other camps they have already registered for.
	 * 
	 * @param campID ID of the target camp
     * @param studentID ID of the student registering
     * @param roleID ID of the desired role
	 */
    public void register(String campID, String studentID, String roleID) {
        if (getRegisteredStudents(campID).contains(studentID)) {
            throw new RuntimeException(studentID + " is already registered for " + campID);
        }
        if (isOver(campID)) {
            throw new RuntimeException(campID + " registration period is over.");
        }
        ArrayList<String> registeredCampList = getRegisteredCampList(studentID);
        for (String icampID : registeredCampList) {
            if (isClashing(campID, icampID)) {
                throw new RuntimeException(campID + " clashes with " + icampID);
            }
        }
        campList.get(campID).register(studentID, roleID);
    }

    /**
	 * Allow a student to withdraw from a camp.
	 * 
	 * @param campID ID of the target camp
     * @param studentID ID of the student withdrawing
	 */
    public void withdraw(String campID, String studentID) {
        Camp myCamp = campList.get(campID);
        myCamp.withdraw(studentID);
    }

    // iCampCommMember and iStaff

    /**
	 * Returns a list of students registered for a camp
     * Accessible only for Camp Committee Members and Staff overseeing the camp.
	 * 
	 * @param campID ID of the target camp
     * @return ArrayList of StudentID
	 */
    public ArrayList<String> getRegisteredStudents(String campID) {
        Camp c = campList.get(campID);
        return c.getRegisteredStudents();
    }

    /**
	 * Returns a list of students registered for a camp and their associated roles
     * Accessible only for Camp Committee Members and Staff overseeing the camp.
	 * 
	 * @param campID ID of the target camp
     * @return HashMap of StudentID and their associated RoleID
	 */
    public HashMap<String, String> getRegisteredStudentRoles(String campID) {
        Camp c = campList.get(campID);
        return c.getRegisteredStudentRoles();
    }
    
    /**
	 * Returns a list of IDs of all camps in the system.
	 * 
	 * @param campID ID of the target camp
     * @return ArrayList of campID in the system
	 */
    public ArrayList<String> getAllCamps(){
        ArrayList<String> result = new ArrayList<String>();
        for (String key : campList.keySet()){
            result.add(key);
        }
        Collections.sort(result);
        return result;
    };
}
