package authenticationpkg;

import java.util.ArrayList;
import camppkg.*;
import feedbackpkg.*;
import pointspkg.*;
import reportpkg.*;

public class AuthCCMember extends AuthStudent {
    public AuthCCMember(String name, String userID, String password, Faculty faculty) {
        super(name, userID, password, faculty);
    }

    public ArrayList<String> getCampEnquiries() {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getCampEnquiries(getUserID(), getRegisteredCampList("CCMember"));
    }

    public void replyEnquiry(int feedbackID, String replyContent) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        PointsManager pointManager = PointsManager.getInstance();
        feedbackManager.replyEnquiry(feedbackID, getRegisteredCampList("CCMember"), replyContent);
        pointManager.addOnePoint(getUserID());
    }

    public ArrayList<String> getSuggestions() {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        return feedbackManager.getSuggestions(getUserID());
    }

    public void editSuggestion(int feedbackID, String newContent) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.editSuggestion(feedbackID, getUserID(), newContent);

    }

    public void deleteSuggestion(int feedbackID) {
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.deleteSuggestion(feedbackID, getUserID());
    }

    public void submitSuggestion(String campID, String content){
        iFeedbackCC feedbackManager = FeedbackManager.getInstance();
        feedbackManager.submitSuggestion(getUserID(), campID, content);
    }

    // Points function
    public void addOnePoint() {

        PointsManager pointsManager = PointsManager.getInstance();
        pointsManager.addOnePoint(getUserID());
    }

    // Report function
    public void writeCampReport(ReportFilterCamp filter) {
        ReportWriterCamp rwc = ReportWriterCamp.getInstance();
        rwc.writeCampReport(filter);
    }
}
