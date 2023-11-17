package camppkg;

import java.util.HashMap;
import java.util.ArrayList;

interface iCampStaff {

    void createCamp(String staffID, String campName, String startDate, String endDate, String registrationClosingDate,
            bool openToNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description);

    void deleteCamp(String campID);

    HashMap<String, Camp> getAllCamps();

    ArrayList<String campID>

    getCreatedCamps(staffID: String);

    Camp getCamp(campID: String);

    void editDate(String campID, String startDate, String endDate);

    void editRegistrationClosingDate(String campID, String registrationClosingDate);

    void editVisibility(String campID, boolean openToNTU);

    void editVisibility(String campID, Faculty faculty);

    void editLocation(Stirng campID, String location);

    void editTotalSlots(String campID, int totalSlots);

    void editCampCommitteeSlots(String campID, int campCommitteeSlots);

    void editDescription(String campID, String description);

}