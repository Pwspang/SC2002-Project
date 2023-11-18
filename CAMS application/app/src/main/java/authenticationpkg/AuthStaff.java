package authenticationpkg;

import java.util.ArrayList;
import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import reportpkg.*;

public class AuthStaff extends AuthUser {
    public AuthStaff(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Functions
    public void createCamp(String campName, String startDate, String endDate, String registratonClosingDate,
            boolean openToNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description) {
        iCampStaff campManager = campManager.getInstance();
        campManager.createCamp(getUserID(), campName, startDate, endDate, registratonClosingDate, openToNTU, userGroup,
                location, totalSlots, campCommitteeSlots, description);
    }

    public void deleteCamp(String campID) {
        iCampStaff campManager = campManager.getInstance();
        campManager.deleteCamp(campID);

    }

    public ArrayList<String> getAllCamps() {
        iCampStaff campManager = campManager.getInstance();
        return campManager.getAllCamps();
    }

    public ArrayList<String> getCreatedCamps() {
        iCampStaff campManager = campManager.getInstance();
        return campManager.getCreatedCamps(getUserID());
    }

    public void editDate(String campID, String startDate, String endDate) {
        iCampStaff campManager = campManager.getInstance();
        return campManager.editDate(campID, startDate, endDate);
    }

    public void editRegistrationClosingDate(String campID, String registrationClosingDate) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editRegistrationClosingDate(campID, registrationClosingDate);
    }

    public void editOpenTo(String campID, boolean openToNTU) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editOpenTo(campID, openToNTU);
    }

    public void editOpenTo(String campID, Faculty faculty) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editOpenTo(campID, faculty);
    }

    public void toggleVisibility(String campID, boolean visiblity) {
        iCampStaff campManager = campManager.getInstance();
        campManager.toggleVisibility(campID, visiblity);
    }

    public void editLocation(String campID, String location) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editLocation(campID, location);
    }

    public void editSlots(String campID, String roleID, int totalSlots) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editTotalSlots(campID, roleID, totalSlots);
    }

    public void editCampCommitteeSlots(String campID, int campCommitteeSlots) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editCampCommitteeSlots(campID, campCommitteeSlots);
    }

    public void editDescription(String campID, String description) {
        iCampStaff campManager = campManager.getInstance();
        campManager.editDescription(campID, description);
    }

    // Enquiry Methods
    public ArrayList<String> getCampEnquiries(ArrayList<String> regCampList) {
        iFeedbackStaff feedbackManager = feedbackManager.getInstance();
        return feedbackManager.getCampEnquiries(regCampList);
    }

    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String campID, String replyContent) {
        iFeedbackStaff feedbackManager = feedbackManager.getInstance();
        return feedbackManager.replyEnquiry(feedbackID, regCampList, campID, replyContent);
    }

    public ArrayList<String> getCampSuggestions(String campID) {
        iFeedbackStaff feedbackManager = feedbackManager.getInstance();
        return feedbackManager.getCampSuggestions(campID);

    }

    public void approveSuggestion(int feedbackID) {
        iFeedbackStaff feedbackManager = feedbackManager.getInstance();
        Feedback f = this.getFeedback(feedbackID);
        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(f.getUserID());
        feedbackManager.approveSuggestion(feedbackID);
    }

    // Report function
}
