package authenticationpkg;

import java.util.ArrayList;
import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import reportpkg.*;
import viewpkg.*;

/**
 * AuthStaff represents the account type of Staff members.
 * Inherits from AuthUser.
 */
public class AuthStaff extends AuthUser {
    /**
     * Constructor to instantiate AuthStaff with the given parameters.
     * 
     * @param name     The name of staff.
     * @param userID   The userID of staff.
     * @param password The password associated with AuthStaff account type.
     * @param faculty  The faculty that the staff belongs to.
     */
    public AuthStaff(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Functions
    /**
     * Creates a camp with the given parameters.
     * 
     * @param campName               The name of camp.
     * @param startDate              The start date of camp.
     * @param endDate                The end date of camp.
     * @param registratonClosingDate The resgistration closing date of camp.
     * @param openToNTU              If the camp is open to the entire university.
     * @param userGroup              The faculty that can join this camp.
     * @param location               The location where this camp is held.
     * @param totalSlots             The total number of students that can register
     *                               for this camp.
     * @param campCommitteeSlots     The number of slots available for CCMembers.
     * @param description            The description of this camp.
     */
    public void createCamp(String campName, String startDate, String endDate, String registratonClosingDate,
            boolean openToNTU, String location, int totalSlots, int campCommitteeSlots,
            String description) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.createCamp(getUserID(), campName, startDate, endDate, registratonClosingDate, openToNTU, getFaculty(),
                location, totalSlots, campCommitteeSlots, description);
    }

    /**
     * Deletes the camp that is associated with campID.
     * 
     * @param campID The campID that is associated with the camp to be deleted.
     */
    public void deleteCamp(String campID) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.deleteCamp(campID);

    }

    /**
     * Gets a list of campIDs of all camps in NTU.
     * 
     * @return ArrayList of campIDs of all camps in NTU.
     */
    public ArrayList<String> getAllCamps() {
        iCampStaff campManager = CampManager.getInstance();
        return campManager.getAllCamps();
    }

    /**
     * Gets the campsIDs of camps that the staff has created.
     * 
     * @return ArrayList of campIDs of camps that staff has created.
     */
    public ArrayList<String> getCreatedCamps() {
        iCampStaff campManager = CampManager.getInstance();
        return campManager.getCreatedCamps(getUserID());
    }

    /**
     * Edits the start and end date of a camp.
     * 
     * @param campID    The campID associated with the camp.
     * @param startDate The new start date of the camp.
     * @param endDate   The new end date of the camp.
     */
    public void editDate(String campID, String startDate, String endDate) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editDate(campID, startDate, endDate);
    }

    /**
     * Edits the registration closing date of a camp.
     * 
     * @param campID                  The campID associated with the camp.
     * @param registrationClosingDate The new registration closing date of the camp.
     */
    public void editRegistrationClosingDate(String campID, String registrationClosingDate) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editRegistrationClosingDate(campID, registrationClosingDate);
    }

    /**
     * Edits whether a camp is opened to NTU.
     * 
     * @param campID    The campID associated with the camp.
     * @param openToNTU The new boolean representing if the camp is open to NTU.
     */
    public void editOpenTo(String campID, boolean openToNTU) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editOpenTo(campID, openToNTU);
    }

    /**
     * Sets the visibility of camp.
     * 
     * @param campID    The campID associated with the camp.
     * @param visiblity The new visibility of the camp.
     */
    public void setVisibility(String campID, boolean visiblity) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.setVisibility(campID, visiblity);
    }

    /**
     * Edits the location of the camp.
     * 
     * @param campID   The campID associated with the camp.
     * @param location The new location that the camp is held at.
     */
    public void editLocation(String campID, String location) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editLocation(campID, location);
    }

    /**
     * Edits the number of camp committee slots available.
     * 
     * @param campID             The campID associated with the camp.
     * @param campCommitteeSlots The new number of camp committee slots.
     */
    public void editCampCommitteeSlots(String campID, int campCommitteeSlots) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editCampCommitteeSlots(campID, campCommitteeSlots);
    }

    /**
     * Edits the description of the camp.
     * 
     * @param campID      The campID associated with the camp.
     * @param description The new description of the camp.
     */
    public void editDescription(String campID, String description) {
        iCampStaff campManager = CampManager.getInstance();
        campManager.editDescription(campID, description);
    }

    /**
     * Gets a list of string representations of the enquires related to the camps
     * that this staff has created.
     * 
     * @return A list of string representations of the enquires related to the camps
     *         that this staff has created.
     */
    public ArrayList<String> getCampEnquiries() {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampEnquiries(getCreatedCamps());
    }

    /**
     * Submits a reply for the given enquiry.
     * 
     * @param feedbackID   The feedbackID related to the enquiry.
     * @param replyContent The content of the reply to the enquiry.
     * @throws Exception
     */
    public void replyEnquiry(int feedbackID, String replyContent) throws Exception {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        feedbackManager.replyEnquiry(feedbackID, getCreatedCamps(), replyContent);
    }

    /**
     * Gets a list of string representations of the suggestions related to a
     * particular camp.
     * 
     * @param campID The campID related to the camp.
     * @return A list of string representations of the suggestions related to a
     *         particular camp.
     */
    public ArrayList<String> getCampSuggestions(String campID) {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampSuggestions(campID);

    }

    /**
     * Approves suggestion made by CCMember.
     * 
     * @param feedbackID The feedbackID associated with the suggestion.
     * @throws Exception
     */
    public void approveSuggestion(int feedbackID) throws Exception {
        iFeedbackStaff feedbackManager = FeedbackManager.getInstance();
        Feedback f = this.getFeedback(feedbackID);
        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(f.getUserID());
        feedbackManager.approveSuggestion(feedbackID);
    }

    // Report function
    /**
     * Writes and saves the camp report to be generated.
     * 
     * @param filtername Name of the filter to be used.
     * @param ID         The ID that the filter requires to write report; dependent
     *                   on the type of filter. Possible IDs: campID, userID.
     * @param filename   The file name that the report should be saved to.
     */

    public void writeCampReport(String filtername, String ID, String filename) {
        ReportManager rwc = ReportManager.getInstance();
        rwc.writeCampReport(filtername, ID, filename);
    }

    /**
     * Gets the UI for staff.
     * 
     * @return iView object for staff.
     */
    public iView getUI() {
        return StaffUI.getInstance();
    }

}
