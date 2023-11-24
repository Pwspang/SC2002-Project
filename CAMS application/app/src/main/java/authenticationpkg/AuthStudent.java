package authenticationpkg;

import java.util.*;
import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import viewpkg.*;

/**
 * AuthStudent represents the account type of normal students.
 * This class inherits from AuthUser.
 */
public class AuthStudent extends AuthUser {

    /**
     * Constructor to instantiate AuthStudent with the given parameters.
     * 
     * @param name     The name of student.
     * @param userID   The userID of student.
     * @param password The password associated with AuthStudent account type.
     * @param faculty  The faculty that the student belongs to.
     */
    AuthStudent(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Methods
    /**
     * Gets the list of campIDs that is visible to the student.
     * 
     * @return ArrayList of campIDs.
     */
    public ArrayList<String> getVisibleCampList() {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getVisibleCampList(getFaculty());
    }

    /**
     * Gets the Camp object associated with the campID.
     * 
     * @return Camp object associated with campID.
     */
    public Camp getCamp(String campID) {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getCamp(campID);
    }

    /**
     * Registers the student for a camp.
     * 
     * @param campID The campID associated with the camp to be registered for.
     * @param roleID The role of the student is registering for.
     */
    public void register(String campID, String roleID) {
        iCampStudent campManager = CampManager.getInstance();
        AccountManager accountManager = AccountManager.getInstance();
        ArrayList<String> campList = campManager.getRegisteredCampList(getUserID(), "CCMember");
        if (campList.isEmpty()) {
            campManager.register(campID, getUserID(), roleID);
            if (roleID.equals("CCmember")){
                            accountManager.changeAccountType(this,
                    new AuthCCMember(this.getName(), this.getUserID(), this.getPassword(), this.getFaculty()));
            }


        } else {
            throw new RuntimeException("You have already registered as a CCMember for another camp.");
        }

    }

    /**
     * Withdraws the student from a camp.
     * 
     * @param campID campID of the camp that student will be withdrawn from.
     */
    public void withdraw(String campID) {
        iCampStudent campManager = CampManager.getInstance();
        campManager.withdraw(campID, getUserID());
    }

    /**
     * Gets a list of campIDs that student has registered for with respect to their
     * role.
     * 
     * @param roleID The role that the student has registered for.
     * @return ArrayList of campIDs that student has registered for with respect to
     *         their role.
     */
    public ArrayList<String> getRegisteredCampList(String roleID) {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getRegisteredCampList(getUserID(), roleID);
    }

    // Enquiry Methods
    /**
     * Gets a list of string representations of the enquiries made by the
     * this student
     * 
     * @return a list of string representations of the enquiries made by the
     *         this student
     */
    public ArrayList<String> getEnquiries() {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getEnquiries(getUserID());

    }

    /**
     * Submits a student enquiry related to a camp.
     * 
     * @param campID  The campID related to the camp student wants to submit enquiry
     *                regarding.
     * @param content The content of the enquiry to be submitted.
     */
    public void submitEnquiry(String campID, String content) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitEnquiry(getUserID(), campID, content);
    }

    /**
     * Edits the enquiry related to feedbackID.
     * 
     * @param feedbackID The feedbackID associated with the enquiry to be edited.
     * @param newContent The content of enquiry that student wants to change to.
     * @throws Exception
     */
    public void editEnquiry(int feedbackID, String newContent) throws Exception {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editEnquiry(feedbackID, getUserID(), newContent);
    }

    /**
     * Deletes the enquiry associated with the feedbackID.
     * 
     * @param feedbackID The feedbackID associated with the enquiry to be deleted.
     */
    public void deleteEnquiry(int feedbackID) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteEnquiry(feedbackID, getUserID());
    }

    // Points Methods
    /**
     * Gets the number of points accumulated by the student.
     * 
     * @return The number of points that the student has accumulated.
     */
    public int getPoints() {
        PointsManager pointsManager = PointsManager.getInstance();
        return pointsManager.getPoints(getUserID());
    }

    /**
     * Gets the UI for the student account.
     * 
     * @return iView object for student account.
     */
    public iView getUI() {
        return StudentUI.getInstance();
    };

}
