package feedbackpkg;
import java.util.ArrayList;

/**
 * Interface for feedback operations specific to a Camp Committee (CC).
 * This interface extends the iFeedbackStudent interface to provide additional methods for handling enquiries and suggestions.
 */
public interface iFeedbackCC extends iFeedbackStudent {

    /**
     * Retrieves a list of enquiries related to the camps that the user is registered for.
     *
     * @param userID The ID of the user.
     * @param regCampList A list of camp IDs that the user is registered for.
     * @return A list of enquiries related to the camps that the user is registered for.
     */
    public ArrayList<String> getCampEnquiries(String userID, ArrayList<String> regCampList);

    /**
     * Replies to an enquiry.
     *
     * @param feedbackID The ID of the enquiry to reply to.
     * @param regCampList A list of camp IDs that the user is registered for.
     * @param replyContent The reply content.
     * @throws Exception if the user is not registered for the camp associated with the enquiry.
     */
    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String replyContent)throws Exception;

    /**
     * Retrieves a list of suggestions made by a specific user.
     *
     * @param CampCommID The ID of the user whose suggestions are to be retrieved.
     * @return A list of suggestions made by the specified user.
     */
    public ArrayList<String> getSuggestions(String CampCommID);

    /**
     * Edits the content of a suggestion made by a specific user.
     *
     * @param feedbackID The ID of the suggestion to edit.
     * @param CampCommID The ID of the user who made the suggestion.
     * @param newContent The new content for the suggestion.
     */
    public void editSuggestion(int feedbackID, String CampCommID, String newContent);

    /**
     * Deletes a suggestion made by a specific user.
     *
     * @param feedbackID The ID of the suggestion to delete.
     * @param CampCommID The ID of the user who made the suggestion.
     */
    public void deleteSuggestion(int feedbackID, String CampCommID);

    /**
     * Submits a new suggestion for a specific camp.
     *
     * @param CampCommID The ID of the user submitting the suggestion.
     * @param campID The ID of the camp that the suggestion is for.
     * @param content The content of the suggestion.
     */
    public void submitSuggestion(String CampCommID, String campID, String content);
}