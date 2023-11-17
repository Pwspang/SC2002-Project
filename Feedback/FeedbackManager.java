package Feedback;
import java.util.ArrayList;

public class FeedbackManager implements iFeedbackStudent, iFeedbackCC, iFeedbackStaff {
    
    private ArrayList<Feedback> feedbackList;

    public FeedbackManager() {
        feedbackList = new ArrayList<>();
    }

    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }

    //iFeedbackStudent
    @Override
    public void getEnquiries(String userID) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void submitEnquiry(String campID, String userID, String content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void editEnquiry(int feedbackID, String userID, String newContent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteEnquiry(int feedbackID, String userID) {
        // TODO Auto-generated method stub
        
    }

   //iFeedbackCC
    @Override
    public ArrayList<String> getCampEnquiries(String campID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void replyEnquiry(int feedbackID, String campID, String studentID, String replyContent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<String> getSuggestions(String userID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void editSuggestion(int feedbackID, String userID, String newContent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteSuggestion(int feedbackID, String userID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void submitSuggestion(int feedbackID, String userID, String content) {
        // TODO Auto-generated method stub
        
    }

    //iFeedbackStaff -> enquiry methods done in iFeedbackCC
    @Override
    public ArrayList<String> getCampSuggestions(String campID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void approveSuggestions(int feedbackID, String campID) {
        // TODO Auto-generated method stub
        
    }   
    
}
