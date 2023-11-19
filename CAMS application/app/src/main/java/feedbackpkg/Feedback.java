package feedbackpkg;

public abstract class Feedback {
    private int feedbackID;
    private String userID;
    private String campID;
    private String content;

    public Feedback(int feedbackID, String userID, String campID, String content) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.campID = campID;
        this.content = content;
    }

    public int getFeedbackID() {
        return this.feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getCampID() {
        return this.campID;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public abstract String toString();

}