package feedbackpkg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FeedbackManager implements iFeedbackCC, iFeedbackStaff {

    private ArrayList<Feedback> feedbackList;
    // instance of FeedbackManager
    private static FeedbackManager feedbackManager;

    // singleton constructor
    private FeedbackManager() {}; 

    // get instance of FeedbackManager
    public static FeedbackManager getInstance() {
        if (feedbackManager == null) {
            feedbackManager = new FeedbackManager();
        }
        return feedbackManager;
    }

    // get feedbackList
    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }

    // get feedback by ID
    public Feedback getFeedback(int feedbackID){
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID) {
                return f;
            }
        }
        return null;
    }

    // Add Feedback to file
    private void addFeedback(Feedback feedback) {
        // Add the feedback to the list
        feedbackList.add(feedback);

        // Write the feedback list to the file
        try {
            PrintWriter writer = new PrintWriter("feedbacks.txt");
            for (Feedback f : feedbackList) {
                writer.println(f.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Edit & Delete Feedback to file
    private void writeFeedbackToFile() {
        try {
            PrintWriter writer = new PrintWriter("feedbacks.txt");
            for (Feedback f : feedbackList) {
                writer.println(f.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // iFeedbackStudent
    @Override
    public ArrayList<String> getEnquiries(String studentID) {
        ArrayList<String> EnquiryList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(studentID) && f instanceof Enquiry) {
                EnquiryList.add(f.toString());
            }
        }

        return EnquiryList;
    }

    @Override
    public void submitEnquiry(String studentID, String campID, String content) {
        int feedbackID = feedbackList.size() + 1;
        Enquiry enquiry = new Enquiry(feedbackID, studentID, campID, content, false);
        addFeedback(enquiry);
    }

    @Override
    public void editEnquiry(int feedbackID, String studentID, String newContent) {
        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                f.setContent(newContent);

                // update file
                writeFeedbackToFile();
                break;
            }
        }
    }

    @Override
    public void deleteEnquiry(int feedbackID, String studentID) {
        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                feedbackList.remove(f);

                // update file
                writeFeedbackToFile();
                break;
            }
        }
    }

    // iFeedbackCC
    @Override
    public ArrayList<String> getCampEnquiries(String userID, ArrayList<String> regCampList) {
        ArrayList<String> CampEnquiriesList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(userID) && f instanceof Enquiry) {
                // get all enquiries that are in the camp that the CC oversees
                for (String campID : regCampList) {
                    if (f.getCampID().equals(campID)) {
                        CampEnquiriesList.add(f.toString());
                    }
                }
            }
        }

        return CampEnquiriesList;
    }

    @Override
    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String campID, String replyContent) {
        // loop to check if campID is in regCampList - CC can only reply if they oversee
        // the camp
        for (String c : regCampList) {
            if (c.equals(campID)) {
                // loop to check if feedbackID is in feedbackList
                for (Feedback f : feedbackList) {
                    if (f.getFeedbackID() == feedbackID && f instanceof Enquiry) {
                        Enquiry e = (Enquiry) f;
                        e.setContent(replyContent);

                        // update file
                        writeFeedbackToFile();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<String> getSuggestions(String CampCommID) {
        ArrayList<String> SuggestionList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                SuggestionList.add(f.toString());
            }
        }

        return SuggestionList;
    }

    @Override
    public void editSuggestion(int feedbackID, String CampCommID, String newContent) {
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                f.setContent(newContent);

                // update file
                writeFeedbackToFile();
                break;
            }
        }
    }

    @Override
    public void deleteSuggestion(int feedbackID, String CampCommID) {
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                feedbackList.remove(f);

                // update file
                writeFeedbackToFile();
                break;
            }
        }
    }

    @Override
    public void submitSuggestion(String CampCommID, String campID, String content) {
        int newFeedbackID = feedbackList.size() + 1;
        Suggestion suggestion = new Suggestion(newFeedbackID, CampCommID, campID, content, false);
        addFeedback(suggestion);
    }

    // iFeedbackStaff -> enquiry methods done in iFeedbackCC
    @Override
    public ArrayList<String> getCampSuggestions(String campID) {
        ArrayList<String> CampSuggestionsList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getCampID().equals(campID) && f instanceof Suggestion) {
                CampSuggestionsList.add(f.toString());
            }
        }
        return CampSuggestionsList;
    }

    @Override
    public void approveSuggestion(int feedbackID) {
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;

                s.setIsApproved(true);

                // update file
                writeFeedbackToFile();
                break;
            }
        }
    }

}