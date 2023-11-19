package feedbackpkg;

public class Suggestion extends Feedback {
    private transient boolean isApproved;

    public Suggestion(int feedbackID, String userID, String campID, String content, boolean isApproved) {
        super(feedbackID, userID, campID, content);
        this.isApproved = false;
    }

    public boolean isApproved() {
        return this.isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}