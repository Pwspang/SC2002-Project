package Feedback;

public interface iFeedbackStudent {
    public void getEnquiries(String userID); 
    public void submitEnquiry(String campID, String userID, String content);
    public void editEnquiry(int feedbackID, String userID, String newContent);
    public void deleteEnquiry(int feedbackID, String userID);
}
