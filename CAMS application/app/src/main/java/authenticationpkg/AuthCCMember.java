package authenticationpkg;

import java.util.ArrayList;
import camppkg.*;
import feedbackpkg.*;
import pointspkg.*;

public class AuthCCMember extends AuthStudent {
    public AuthCCMember(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    public ArrayList<String> getCampEnquiries(ArrayList<String> regCampList) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampEnquries(userID, regCampList);
    }

    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String campID, String replyContent) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        PointsManager pointManager = PointsManager.getInstance();
        feedbackManager.replyEnquiry(feedbackID, regCampList, campID, replyContent);
        pointManager.addOnePoint(userID);
    }

    public ArrayList<String> getSuggestions() {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.getSuggestions(userID);
    }

    public void editSuggestion(int feedbackID, String newContent) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editSuggestion(feedbackID, userID, newContent);

    }

    public void deleteSuggestion(int feedbackID) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteSuggestion(feedbackID, userID);
    }

    public submitSuggestion(String campID, String content){
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitSuggestion(userID, campID, content);
    }

    // Points function
    public void addOnePoint() {

        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(userID);
    }

}
