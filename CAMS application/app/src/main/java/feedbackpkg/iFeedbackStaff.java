package feedbackpkg;

import java.util.ArrayList;

public interface iFeedbackStaff {
    public ArrayList<String> getCampEnquiries(String userID, ArrayList<String> regCampList);

    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String campID, String replyContent);

    public ArrayList<String> getCampSuggestions(String campID);

    public void approveSuggestion(int feedbackID);
}