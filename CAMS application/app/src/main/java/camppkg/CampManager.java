
package camppkg;
import java.util.*;


public class CampManager implements iCampStaff{

    // singleton constructor
    private static final CampManager campManager = new CampManager();
    private HashMap<String, Camp> campList = new HashMap<String, Camp>();

    public static CampManager getInstance() {
        return campManager;
    }

    
    public HashMap<String, Camp> getCampList() {
        return campList;
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

    public Camp getCamp(String campID){
        return campList.get(campID);
    }

    // To remove 
    public void createCamp(String staffID, String campName, String startDate, String endDate, String registrationClosingDate,
    boolean openToNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
    String description){};

    public ArrayList<String> getAllCamps(){
        ArrayList<String> s = new ArrayList<String>();
        for ( String key : campList.keySet() ) {
            s.add(key);
        }
        return s;
    };

    public ArrayList<String> getCreatedCamps(String staffID){
        ArrayList<String> s = new ArrayList<String>();
        s.add("Camp 1");
        s.add("Camp 2");
        return s;
    };

    public void editDate(String campID, String startDate, String endDate){};

    public void editRegistrationClosingDate(String campID, String registrationClosingDate){};

    public void editVisibility(String campID, boolean openToNTU){};

    public void editVisibility(String campID, Faculty faculty){};

    public void editLocation(String campID, String location){};

    public void editTotalSlots(String campID, int totalSlots){};

    public void editCampCommitteeSlots(String campID, int campCommitteeSlots){};

    public void editDescription(String campID, String description){};
}