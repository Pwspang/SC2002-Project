package feedbackpkg;
/**
 * The Enquiry class represents a user enquiry in the feedback system.
 * It extends the Feedback class and adds a property to track if the enquiry has been replied to.
 */

 public class Enquiry extends Feedback{
    /**
     * Indicates whether the enquiry has been replied to.
     */
    private Boolean isReplied;

    private String replyString;

     /**
     * Constructs a new Enquiry with the specified ID, user ID, campaign ID, content, and reply status.
     *
     * @param feedbackID the unique ID of the feedback
     * @param userID the ID/Name of the user who made the enquiry
     * @param campID the ID/Name of the camp the enquiry is associated with
     * @param content the content of the enquiry
     * @param isReplied whether the enquiry has been replied to
     */
    public Enquiry(int feedbackID, String userID, String campID, String content, boolean isReplied) {
        super(feedbackID, userID, campID, content);
        this.isReplied = false;
        this.replyString = " ";
    }

    /**
     * Returns whether the enquiry has been replied to.
     *
     * @return true if the enquiry has been replied to, false otherwise
     */
    public boolean isReplied() {
        return this.isReplied;
    }

    public String getReplyString(){
        return replyString;
    }

    public void setReplyString(String replyString){
        this.replyString = replyString;
    }

    /**
     * Sets whether the enquiry has been replied to.
     *
     * @param isReplied true if the enquiry has been replied to, false otherwise
     */
    public void setIsReplied(boolean isReplied) {
        this.isReplied = isReplied;
    }

    

    /**
     * Returns a string representation of the enquiry.
     *
     * @return a string representation of the enquiry
     */
    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s:%s", getFeedbackID(), getUserID(), getCampID(), getContent(), getReplyString());
    }


}