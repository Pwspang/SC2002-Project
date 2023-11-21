package feedbackpkg;
import java.io.Serializable;

/**
 * The Feedback class represents a feedback in the system.
 * It is an abstract class that can be extended by specific types of feedback.
 */
public abstract class Feedback implements Serializable{

    /**
     * The unique ID of the feedback.
     */
    private Integer feedbackID;
    /**
     * The ID/Name of the user who made the feedback.
     */
    private String userID;
    /**
     * The ID/Name of the camp the feedback is associated with.
     */
    private String campID;
    /**
     * The content of the feedback.
     */
    private String content;

    /**
     * Constructs a new Feedback with the specified ID, user ID, campaign ID, and content.
     *
     * @param feedbackID the unique ID of the feedback
     * @param userID the ID/Name of the user who made the feedback
     * @param campID the ID/Name of the camp the feedback is associated with
     * @param content the content of the feedback
     */
    public Feedback(int feedbackID, String userID, String campID, String content) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.campID = campID;
        this.content = content;
    }

    /**
     * Returns the unique ID of the feedback.
     *
     * @return the unique ID of the feedback
     */
    public int getFeedbackID() {
        return this.feedbackID;
    }

    /**
     * Sets the unique ID of the feedback.
     *
     * @param feedbackID the unique ID of the feedback
     */
    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    /**
     * Returns the ID/Name of the user who made the feedback.
     *
     * @return the ID/Name of the user who made the feedback
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Sets the ID/Name of the user who made the feedback.
     *
     * @param userID the ID/Name of the user who made the feedback
     */
    public String getCampID() {
        return this.campID;
    }

    /**
     * Sets the ID/Name of the camp the feedback is associated with.
     *
     * @param campID the ID/Name of the camp the feedback is associated with
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Sets the content of the feedback.
     *
     * @param content the content of the feedback
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns a string representation of the feedback.
     *
     * @return a string representation of the feedback
     */
    public abstract String toString();

}