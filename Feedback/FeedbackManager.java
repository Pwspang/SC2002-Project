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

    @Override
    public void deleteEnquiry(int feedbackID, int studentID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void editEnquiry(int feedbackID, int studentID, String newContent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void submitEnquiries(int campID, int studentID, String content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void viewEnquiries(int studentID) {
        // TODO Auto-generated method stub
        
    }
    
}
