package feedbackpkg;

public class Enquiry extends Feedback {
    private boolean isReplied;

    public Enquiry(int feedbackID, String userID, String campID, String content, boolean isReplied) {
        super(feedbackID, userID, campID, content);
        this.isReplied = false;
    }

    public boolean isReplied() {
        return this.isReplied;
    }

    public void setIsReplied(boolean isReplied) {
        this.isReplied = isReplied;
    }

    @Override
    public String toString(){
        return "Enquiry";
    }

}