package feedbackpkg;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FeedbackManager implements Serializable, iFeedbackCC, iFeedbackStaff {

    private transient ArrayList<Feedback> feedbackList = new ArrayList<>();
    // instance of FeedbackManager
    private static FeedbackManager feedbackManager;
    private static final String filename = "feedbacks.dat";

    private FeedbackManager() {
        feedbackList = readSerializedObject();
        if (feedbackList == null) {
            feedbackList = new ArrayList<>();
        }
    }; 

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

    // add feedback
    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
        writeSerialisedObj();
    }

    public void writeSerialisedObj() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(feedbackList);
            out.close();
            fos.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Feedback> readSerializedObject() {
        ArrayList<Feedback> list = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            list = (ArrayList<Feedback>) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return list;
    }


    // iFeedbackStudent
    @Override
    public ArrayList<String> getEnquiries(String studentID) {
        ArrayList<String> EnquiryList = new ArrayList<>();
        if(feedbackList.size() == 0){
            return EnquiryList;
        }
        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(studentID) && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                //check if enquiry is not replied
                if(!e.isReplied()){
                    EnquiryList.add(f.toString());
                }
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
        boolean isEdited = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                //check if enquiry is not replied
                if(!e.isReplied()){
                    f.setContent(newContent);
                    isEdited = true;

                    //update file
                    writeSerialisedObj();       
                    break;
                }        
            }
            if (isEdited) {
                break;
            }
        }
    }

    @Override
    public void deleteEnquiry(int feedbackID, String studentID) {

        boolean isFound = false;
       
        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                //check if enquiry is not replied
                if(!e.isReplied()){
                    feedbackList.remove(f);
                    isFound = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if (isFound) {
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
        // loop to check if campID is in regCampList - CC can only reply if they oversee the camp
        boolean isFound = false;
        for (String c : regCampList) {
            if (c.equals(campID)) {
                // loop to check if feedbackID is in feedbackList
                for (Feedback f : feedbackList) {
                    if (f.getFeedbackID() == feedbackID && f instanceof Enquiry) {
                        Enquiry e = (Enquiry) f;
                        e.setContent(replyContent);
                        e.setIsReplied(true);

                        isFound = true;

                        // update file
                        writeSerialisedObj();
                        break;
                    }
                }
                if(isFound){
                    break;
                }
            }
        }
    }

    @Override
    public ArrayList<String> getSuggestions(String CampCommID) {
        ArrayList<String> SuggestionList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                //check if suggestion is not approved
                if(!s.isApproved()){
                    SuggestionList.add(f.toString());
                }
            }
        }
        return SuggestionList;
    }

    @Override
    public void editSuggestion(int feedbackID, String CampCommID, String newContent) {
        boolean isEdited = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                //check if suggestion is not approved
                if(!s.isApproved()){
                    f.setContent(newContent);
                    isEdited = true;
                    
                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if (isEdited) {
                break;
            }
        }
    }

    @Override
    public void deleteSuggestion(int feedbackID, String CampCommID) {
        boolean isFound = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                //check if suggestion is not approved
                if(!s.isApproved()){
                    feedbackList.remove(f);
                    isFound = true;

                    System.out.println("Suggestion deleted");

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if(isFound){
                break;
            }
        }
        if(!isFound){
            System.out.println("Suggestion not found");
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
        boolean isFound = false;
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                
                if(!s.isApproved()){    
                    s.setIsApproved(true);
                    isFound = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if(isFound){
                break;
            }
        }
    }
}