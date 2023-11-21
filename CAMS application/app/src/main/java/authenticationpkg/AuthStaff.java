package authenticationpkg;

import java.util.ArrayList;
import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import reportpkg.*;
import viewpkg.*;

public class AuthStaff extends AuthUser {
    public AuthStaff(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Functions
    public void createCamp(String campName, String startDate, String endDate, String registratonClosingDate,
            boolean openToNTU, Faculty userGroup, String location, int totalSlots, int campCommitteeSlots,
            String description) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.createCamp(getUserID(), campName, startDate, endDate, registratonClosingDate, openToNTU, userGroup,
                location, totalSlots, campCommitteeSlots, description);
    }

    public void deleteCamp(String campID) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.deleteCamp(campID);

    }

    public ArrayList<String> getAllCamps() {
        iCampStaff campManager = CampManager.getInstance();
        return campManager.getAllCamps();
    }

    public ArrayList<String> getCreatedCamps() {
        iCampStaff campManager = CampManager.getInstance();
        return campManager.getCreatedCamps(getUserID());
    }

    public void editDate(String campID, String startDate, String endDate) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editDate(campID, startDate, endDate);
    }

    public void editRegistrationClosingDate(String campID, String registrationClosingDate) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editRegistrationClosingDate(campID, registrationClosingDate);
    }

    public void editOpenTo(String campID, boolean openToNTU) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editOpenTo(campID, openToNTU);
    }

    public void editOpenTo(String campID, Faculty faculty) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editOpenTo(campID, faculty);
    }

    public void setVisibility(String campID, boolean visiblity) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.setVisibility(campID, visiblity);
    }

    public void editLocation(String campID, String location) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editLocation(campID, location);
    }

    public void editCampCommitteeSlots(String campID, int campCommitteeSlots) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editCampCommitteeSlots(campID, campCommitteeSlots);
    }

    public void editDescription(String campID, String description) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editDescription(campID, description);
    }


    public ArrayList<String> getCampEnquiries() {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampEnquiries(getCreatedCamps());
    }

    public void replyEnquiry(int feedbackID, String replyContent) throws Exception {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        feedbackManager.replyEnquiry(feedbackID, getCreatedCamps(), replyContent);
    }


    public ArrayList<String> getCampSuggestions(String campID) {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampSuggestions(campID);

    }

    public void approveSuggestion(int feedbackID) throws Exception {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        Feedback f = this.getFeedback(feedbackID);
        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(f.getUserID());
        feedbackManager.approveSuggestion(feedbackID);
    }

    // Report function

    public void writeCampReport(String filtername,  String ID, String filename) {
        ReportManager rwc = ReportManager.getInstance();
        rwc.writeCampReport(filtername, ID, filename);
    }

    public iView getUI() {
        return StaffUI.getInstance();
    }

}
