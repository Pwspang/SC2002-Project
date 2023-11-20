package authenticationpkg;

import java.util.*;
import feedbackpkg.*;
import camppkg.*;
import pointspkg.*;
import viewpkg.*;

public class AuthStudent extends AuthUser {
    AuthStudent(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Methods
    public ArrayList<String> getVisibleCampList() {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getVisibleCampList(getFaculty());
    }

    public Camp getCamp(String campID) {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getCamp(campID);
    }

    public void register(String campID, String roleID) {
        iCampStudent campManager = CampManager.getInstance();
        AccountManager accountManager = AccountManager.getInstance();
        ArrayList<String> campList = campManager.getRegisteredCampList(getUserID(), roleID);
        if (campList.isEmpty()) {
            campManager.register(campID, getUserID(), roleID);
            accountManager.changeAccountType(this,
                    new AuthCCMember(this.getName(), this.getUserID(), this.getPassword(), this.getFaculty()));

        } else {
            throw new RuntimeException("You have already registered as a CCMember for another camp.");
        }

    }

    public void withdraw(String campID) {
        iCampStudent campManager = CampManager.getInstance();
        campManager.withdraw(campID, getUserID());
    }

    public ArrayList<String> getRegisteredCampList(String roleID) {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getRegisteredCampList(getUserID(), roleID);
    }

    // Enquiry Methods
    public ArrayList<String> getEnquiries() {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getEnquiries(getUserID());

    }

    public void submitEnquiry(String campID, String content) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitEnquiry(getUserID(), campID, content);
    }

    public void editEnquiry(int feedbackID, String newContent) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editEnquiry(feedbackID, getUserID(), newContent);
    }

    public void deleteEnquiry(int feedbackID) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteEnquiry(feedbackID, getUserID());
    }

    // Points Methods
    public int getPoints() {
        PointsManager pointsManager = PointsManager.getInstance();
        return pointsManager.getPoints(getUserID());
    }

    public iView getUI() {
        return StudentUI.getInstance();
    };

}
