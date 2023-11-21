package feedbackpkg;
import java.util.ArrayList;

/**
 * Interface for feedback operations specific to a staff member.
 */
public interface iFeedbackStaff {

    /**
     * Retrieves a list of enquiries related to the camps that the user is overseeing.
     *
     * @param userID The ID(Name) of the user.
     * @param regCampList A list of camp IDs(Names) that the user is registered for.
     * @return A list of enquiries related to the camps that the user is overseeing.
     */
    public ArrayList<String> getCampEnquiries(String userID, ArrayList<String> regCampList);

    /**
     * Replies to an enquiry.
     *
     * @param feedbackID The ID of the enquiry to reply to.
     * @param regCampList A list of camp IDs(Names) that the user is registered for.
     * @param replyContent The content of the reply.
     * @throws Exception If an error occurs while replying to the enquiry.
     */
    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String replyContent) throws Exception;

    /**
     * Retrieves a list of suggestions for a specific camp.
     *
     * @param campID The ID(Name) of the camp.
     * @return A list of suggestions for the specified camp.
     */
    public ArrayList<String> getCampSuggestions(String campID);

    /**
     * Approves a suggestion.
     *
     * @param feedbackID The ID of the suggestion to approve.
     */
    public void approveSuggestion(int feedbackID);
}