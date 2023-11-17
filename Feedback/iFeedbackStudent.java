package Feedback;

import java.util.ArrayList;

public interface iFeedbackStudent {
    public ArrayList<String> getEnquiries(String studentID); 
    public void submitEnquiry(String studentID,String campID, String content);
    public void editEnquiry(int feedbackID, String studentID, String newContent);
    public void deleteEnquiry(int feedbackID, String studentID);
}
