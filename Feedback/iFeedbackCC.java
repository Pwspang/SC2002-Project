package Feedback;

public interface iFeedbackCC {
    public ArrayList<String> getCampEnquiries(String campID);
    public void replyEnquiry(int feedbackID, String campID, String studentID, String replyContent);
    public ArrayList<String> getSuggestions(String userID);
    public void editSuggestion(int feedbackID, String userID, String newContent);
    public void deleteSuggestion(int feedbackID, String userID);
    public void submitSuggestion(int feedbackID, String userID, String content);
}
