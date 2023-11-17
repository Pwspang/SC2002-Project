package Feedback;

public interface iFeedbackStaff {
    public ArrayList<String> getCampEnquiries(String campID);
    public void replyEnquiry(int feedbackID, String campID, String studentID, String replyContent);
    public ArrayList<String> getCampSuggestions(String campID);
    public void approveSuggestions(int feedbackID, String campID);
}
