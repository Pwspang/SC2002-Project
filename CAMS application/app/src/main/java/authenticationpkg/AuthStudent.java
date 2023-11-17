package authenticationpkg;

import java.util.ArrayList;
import feedbackpkg.*;
import camppkg.*;
//import pointspkg.*;

public class AuthStudent extends AuthUser {

    AuthStudent(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    // Camp Methods
    public ArrayList<String> getVisibleCampList() {
        iCampManager campManager = CampManager.getInstance();
        return campManager.getVisibleCampList(userID);
    }

    public Camp getCamp(String campID) {
        iCampStudent campManager = CampManager.getInstance();
        return campManager.getCamp(campID);
    }

    public void register(String campID, String roleID) {
        iCampStudent campManager = campManager.getInstance();
        campManager.register(campID, userID, roleID);

    }

    public void withdraw(String campID) {
        iCampStudent campManager = campManager.getInstance();
        campManager.withdraw(campID, userID);
    }

    public ArrayList<String> getRegisteredCampList(String roleID) {
        iCampStudent campManager = campManager.getInstance();
        campManager.getRegisteredCampList(userID, roleID);
    }

    // Enquiry Methods
    public ArrayList<String> getEnquiries() {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getEnquiries(userID);

    }

    public void submitEnquiry(String campID, String content) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitEnquiry(userID, campID, content);
    }

    public void editEnquiry(int feedbackID, String newContent) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editEnquiry(feedbackID, userID, newContent);
    }

    public void deleteEnquiry(int feedbackID) {
        iFeedbackStudent feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteEnquiry(feedbackID, userID);
    }

    // Points Methods
    public int getPoints(){
        PointsManager pointsManager = PointsManager.getInstance()
        return pointsManager.getPoints(userID);
    }

}
