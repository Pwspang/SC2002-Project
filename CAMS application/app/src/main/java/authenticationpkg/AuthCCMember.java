package authenticationpkg;

import java.util.ArrayList;
import camppkg.*;
import feedbackpkg.*;
import pointspkg.*;
import reportpkg.*;
import viewpkg.CampComitteeUI;
import viewpkg.StudentUI;
import viewpkg.iView;

/**
 * AuthCCMember represents the account type of Camp Committee members.
 * Inherits from AuthStudent.
 */
public class AuthCCMember extends AuthStudent {
    /**
     * Constructor to instantiate AuthCCMember with the given parameters.
     * 
     * @param name     The name of student.
     * @param userID   The userID of student.
     * @param password The password associated with AuthCCMember account type.
     * @param faculty  The faculty that the student belongs to.
     */
    public AuthCCMember(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    /**
     * Gets a list of string representations of the enquiries made by this CCMember.
     * 
     * @return a list of string representations of the enquiries made by this
     *         CCMember.
     * 
     */
    public ArrayList<String> getCampEnquiries() {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampEnquiries(getRegisteredCampList("CCMember"));
    }

    /**
     * Submits a reply to a given enquiry associated with feedbackID.
     * 
     * @param feedbackID   The feedbackID associated with the enquiry.
     * @param replyContent The content that is the reply to the enquiry.
     * @throws Exception
     */
    public void replyEnquiry(int feedbackID, String replyContent) throws Exception {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        PointsManager pointManager = PointsManager.getInstance();
        feedbackManager.replyEnquiry(feedbackID, getRegisteredCampList("CCMember"), replyContent);
        pointManager.addOnePoint(getUserID());
    }

    /**
     * Gets a list of string representations of the suggestions made by this
     * CCMember.
     * 
     * @return a list of string representations of the suggestions made by this
     *         CCMember.
     */
    public ArrayList<String> getSuggestions() {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getSuggestions(getUserID());
    }

    /**
     * Edits the suggestion associated with the feedbackID made by this CCMember.
     * 
     * @param feedbackID The feedbackID associated with the suggestion to be edited.
     * @param newContent The new content that the suggestion is changed to.
     */
    public void editSuggestion(int feedbackID, String newContent) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editSuggestion(feedbackID, getUserID(), newContent);

    }

    /**
     * Deletes the suggestion associated with the feedbackID.
     * 
     * @param feedbackID The feedbackID associated with the suggestion to be
     *                   deleted.
     */
    public void deleteSuggestion(int feedbackID) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteSuggestion(feedbackID, getUserID());
    }

    /**
     * Submits the suggestion made by this CCMember for a camp.
     * 
     * @param campID  The campID associated with the camp.
     * @param content The content of the suggestion to be submitted.
     */
    public void submitSuggestion(String campID, String content) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitSuggestion(getUserID(), campID, content);
    }

    // Points function
    /**
     * Adds one point to the CCMember Account.
     */
    public void addOnePoint() {

        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(getUserID());
    }

    // Report function
    /**
     * Writes and saves the camp report to be generated.
     * 
     * @param filtername Name of the filter to be used.
     * @param ID         The ID that the filter requires to write report; dependent
     *                   on the type of filter. Possible IDs: campID.
     * @param filename   The file name that the report should be saved to.
     */
    public void writeCampReport(String filtername, String ID, String filename) {
        ReportManager rwc = ReportManager.getInstance();
        rwc.writeCampReport(filtername, ID, filename);
    }

    /**
     * Gets the UI for CCMember.
     * 
     * @return iView object for CCMember.
     */
    public iView getUI() {
        return CampComitteeUI.getInstance();
    };
}
