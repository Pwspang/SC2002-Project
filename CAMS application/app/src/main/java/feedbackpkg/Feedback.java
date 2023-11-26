package feedbackpkg;
import java.io.Serializable;

/**
 * The Feedback class represents a feedback in the system.
 * It is an abstract class that can be extended by specific types of feedback.
 */
public abstract class Feedback implements Serializable{

    /**
     * The ID to be assigned to the next feedback that is created.
     * This is a static variable, so it's shared among all instances of the Feedback class.
     * It's incremented each time a new feedback is created.
     */
    private static Integer nextFeedbackID = 1;

    /**
     * The unique ID for this feedback.
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
     * Constructs a new Feedback with the specified user ID, camp ID, and content.
     * The feedback ID is automatically generated and incremented for each new Feedback.
     *
     * @param userID The ID of the user who made the feedback.
     * @param campID The ID of the camp the feedback is associated with.
     * @param content The content of the feedback.
     */
    public Feedback(String userID, String campID, String content) {
        this.feedbackID = nextFeedbackID++;
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
        return feedbackID;
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
     * Gets the ID/Name of the user who made the feedback.
     * @return Camp ID
     */
    public String getCampID() {
        return this.campID;
    }

    /**
     * Get the content of the feedback
     * @return Content 
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