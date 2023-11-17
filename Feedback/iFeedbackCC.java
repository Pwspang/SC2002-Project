package Feedback;
import java.util.ArrayList;

public interface iFeedbackCC extends iFeedbackStudent{
    public ArrayList<String> getCampEnquiries(String userID, ArrayList<String> regCampList);
    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String campID, String replyContent);
    
    public ArrayList<String> getSuggestions(String CampCommID);
    public void editSuggestion(int feedbackID, String CampCommID, String newContent);
    public void deleteSuggestion(int feedbackID, String CampCommID);
    public void submitSuggestion(String CampCommID, String campID, String content);
}
