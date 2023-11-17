
package camppkg;
import java.util.*;


public class CampManager {

    // singleton constructor
    private static final CampManager campManager = new CampManager();
    private CampManager() {}
    public static CampManager getInstance() {
        return campManager;
    }

    private HashMap<String, Camp> campList;
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
}