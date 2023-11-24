package feedbackpkg;

/**
 * The Suggestion class represents a suggestion in the feedback system.
 * It extends the Feedback class and adds a property to track if the suggestion has been approved.
 */
public class Suggestion extends Feedback {

    /**
     * Indicates whether the suggestion has been approved.
     */
    private Boolean isApproved;
    
    /**
     * Constructs a new Suggestion with the specified ID, user ID, campaign ID, content, and approval status.
     *
     * @param feedbackID the unique ID of the feedback
     * @param userID the ID(Name) of the user who made the suggestion
     * @param campID the ID(Name) of the camp the suggestion is associated with
     * @param content the content of the suggestion
     * @param isApproved whether the suggestion has been approved
     */
    public Suggestion(String userID, String campID, String content, boolean isApproved) {
        super(userID, campID, content);
        this.isApproved = false;
    }

    /**
     * Returns whether the suggestion has been approved.
     *
     * @return true if the suggestion has been approved, false otherwise
     */
    public boolean isApproved() {
        return this.isApproved;
    }

    /**
     * Sets whether the suggestion has been approved.
     *
     * @param isApproved true if the suggestion has been approved, false otherwise
     */
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    /**
     * Returns a string representation of the suggestion.
     *
     * @return a string representation of the suggestion
     */
    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s:%s", getFeedbackID(), getUserID(), getCampID(), getContent(), isApproved);
    }
}