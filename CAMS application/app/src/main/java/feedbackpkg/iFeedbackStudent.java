package feedbackpkg;
import java.util.ArrayList;

/**
 * Interface for feedback operations specific to a student.
 */
public interface iFeedbackStudent {

    /**
     * Retrieves a list of enquiries made by a specific user.
     *
     * @param studentID The ID(Name) of the user whose enquiries are to be retrieved.
     * @return A list of enquiries made by the specified user.
     */
    public ArrayList<String> getEnquiries(String studentID);

    /**
     * Submits a new enquiry for a specific camp.
     *
     * @param studentID The ID(Name) of the student submitting the enquiry.
     * @param campID The ID(Name) of the camp that the enquiry is for.
     * @param content The content of the enquiry.
     */
    public void submitEnquiry(String studentID, String campID, String content);

    /**
     * Edits the content of an enquiry made by a specific student.
     *
     * @param feedbackID The ID of the enquiry to edit.
     * @param studentID The ID(Name) of the student who made the enquiry.
     * @param newContent The new content for the enquiry.
     * @throws Exception If an error occurs while editing the enquiry.
     */
    public void editEnquiry(int feedbackID, String studentID, String newContent) throws Exception;

    /**
     * Deletes an enquiry made by a specific student.
     *
     * @param feedbackID The ID of the enquiry to delete.
     * @param studentID The ID(Name) of the student who made the enquiry.
     */
    public void deleteEnquiry(int feedbackID, String studentID);
}