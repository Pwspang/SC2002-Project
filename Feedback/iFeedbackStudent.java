package Feedback;

public interface iFeedbackStudent {
    public void viewEnquiries(int studentID); 
    public void submitEnquiries(int campID, int studentID, String content);
    public void editEnquiry(int feedbackID, int studentID, String newContent);
    public void deleteEnquiry(int feedbackID, int studentID);
}
