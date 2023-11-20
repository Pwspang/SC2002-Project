package feedbackpkg;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The FeedbackManager class manages the list of feedbacks in the system.
 * It implements the Serializable interface for serialization and deserialization of the feedback list,
 * and the iFeedbackCC and iFeedbackStaff interfaces for feedback operations.
 */
public class FeedbackManager implements Serializable, iFeedbackCC, iFeedbackStaff {

    /**
     * The list of feedbacks.
     */
    private ArrayList<Feedback> feedbackList = new ArrayList<>();
    
    /**
     * The singleton instance of FeedbackManager.
     */
    private static FeedbackManager feedbackManager;
    
    /**
     * The filename for serialized feedback data.
     */
    private static final String filename = "feedbacks.dat";

    /**
     * Private constructor for the singleton FeedbackManager.
     * It initializes the feedback list by reading from the serialized object file.
     * If the file does not exist or is empty, it initializes an empty feedback list.
     */
    private FeedbackManager() {
        feedbackList = readSerializedObject();
        if (feedbackList == null) {
            feedbackList = new ArrayList<>();
        }
    }

    /**
     * Returns the singleton instance of FeedbackManager.
     *
     * @return the singleton instance of FeedbackManager
     */
    public static FeedbackManager getInstance() {
        if (feedbackManager == null) {
            feedbackManager = new FeedbackManager();
        }
        return feedbackManager;
    }

    /**
     * Returns the list of feedbacks.
     *
     * @return the list of feedbacks
     */
    public ArrayList<Feedback> getFeedbackList() {
        if (feedbackList.size() == 0) {
            throw new NullPointerException("No feedbacks found");
        } else {
            return feedbackList;
        }
    }

    /**
     * Returns the feedback with the specified ID.
     *
     * @param feedbackID the ID of the feedback to be returned
     * @return the feedback with the specified ID
     * @throws Exception if the feedback is not found
     */
    public Feedback getFeedback(int feedbackID) throws Exception {
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID) {
                return f;
            }
        }
        throw new Exception("Feedback not found");
    }

    /**
     * Adds the specified feedback to the list of feedbacks.
     *
     * @param feedback the feedback to be added
     */
    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
        writeSerialisedObj();
    }

    /**
     * Writes the current state of the feedback list to a file.
     * The feedback list is serialized and written to a file with the name specified by the 'filename' field.
     * If the operation is successful, it prints "Object Persisted" to the console.
     * If an IOException occurs during the operation, it prints the stack trace to the error output stream.
     */
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
    
    /**
     * Reads the serialized feedback list from a file.
     * The feedback list is deserialized from a file with the name specified by the 'filename' field.
     * If the operation is successful, it returns the deserialized feedback list.
     * If an IOException or ClassNotFoundException occurs during the operation, it prints the stack trace to the error output stream and returns null.
     *
     * @return the deserialized feedback list
     */
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

    // --- iFeedbackStudent ---

    /**
     * Retrieves a list of enquiries made by a specific student.
     * 
     * This method iterates over the feedback list, and for each feedback that is an instance of Enquiry
     * that was made by the specified student, it checks if the enquiry has been replied to.
     * If the enquiry has not been replied to, it is added to the list of enquiries to return.
     * 
     * If no enquiries are found for the specified student, an exception is thrown.
     * 
     * @param studentID the ID(Name) of the student whose enquiries are to be retrieved
     * @return a list of string representations of the enquiries made by the specified student
     * @throws Exception if no enquiries are found for the specified student
     */
    @Override
    public ArrayList<String> getEnquiries(String studentID) throws Exception {
        ArrayList<String> EnquiryList = new ArrayList<>();
        boolean userFound = false;
        if (feedbackList.size() == 0) {
            return EnquiryList;
        }
        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(studentID) && f instanceof Enquiry) {
                userFound = true;

                Enquiry e = (Enquiry) f;
                // check if enquiry is not replied
                if (!e.isReplied()) {
                    EnquiryList.add(f.toString());
                }
            }
        }
        if (!userFound) {
            throw new Exception("User not found");
        }
        // // For testing
        // for(String s: EnquiryList){
        //     System.out.println(s.toString());
        // }
        return EnquiryList;
    }

    /**
     * Submits an enquiry to the system.
     * 
     * This method creates a new Enquiry object with the specified student ID, camp ID, and content.
     * The new Enquiry object is added to the feedback list.
     * 
     * @param studentID the ID(Name) of the student who made the enquiry
     * @param campID the ID(Name) of the camp the enquiry is associated with
     * @param content the content of the enquiry
     */
    @Override
    public void submitEnquiry(String studentID, String campID, String content) {
        int feedbackID = feedbackList.size() + 1;
        Enquiry enquiry = new Enquiry(feedbackID, studentID, campID, content, false);
        addFeedback(enquiry);
    }

    /**
     * Edits an enquiry made by a specific student.
     * 
     * This method iterates over the feedback list, and for each feedback that is an instance of Enquiry
     * that was made by the specified student, it checks if the enquiry has been replied to.
     * If the enquiry has not been replied to, its content is updated with the specified new content.
     * 
     * If no enquiries are found for the specified student, or if the specified enquiry has already been replied to,
     * an exception is thrown.
     * 
     * @param feedbackID the ID of the enquiry to be edited
     * @param studentID the ID(Name) of the student who made the enquiry
     * @param newContent the new content of the enquiry
     * @throws Exception if no enquiries are found for the specified student, or if the specified enquiry has already been replied to
     */
    @Override
    public void editEnquiry(int feedbackID, String studentID, String newContent) throws Exception {
        boolean isEdited = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                // check if enquiry is not replied
                if (!e.isReplied()) {
                    f.setContent(newContent);
                    isEdited = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
        }
        if (!isEdited) {
                throw new Exception("Enquiry not found or already replied");
        }
    }

    /**
     * Deletes an enquiry made by a specific student.
     * 
     * This method iterates over the feedback list, and for each feedback that is an instance of Enquiry
     * that was made by the specified student, it checks if the enquiry has been replied to.
     * If the enquiry has not been replied to, it is removed from the feedback list.
     * 
     * If no enquiries are found for the specified student, or if the specified enquiry has already been replied to,
     * an exception is thrown.
     * 
     * @param feedbackID the ID of the enquiry to be deleted
     * @param studentID the ID(Name) of the student who made the enquiry
     * @throws Exception if no enquiries are found for the specified student, or if the specified enquiry has already been replied to
     */
    @Override
    public void deleteEnquiry(int feedbackID, String studentID) {
        boolean isFound = false;

        for (Feedback f : feedbackList) {
            // check if feedback belongs to student and is an enquiry before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(studentID) && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                // check if enquiry is not replied
                if (!e.isReplied()) {
                    feedbackList.remove(f);
                    isFound = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
        }

        if (!isFound) {
                throw new NullPointerException("Enquiry not found");
        }
    }

    // --- iFeedbackCC ---

    /**
     * Retrieves a list of enquiries made to a specific camp.
     * 
     * This method iterates over the feedback list, and for each feedback that is an instance of Enquiry
     * that is associated with the specified camp, it checks if the enquiry has been replied to.
     * If the enquiry has not been replied to, it is added to the list of enquiries to return.
     * 
     * If no enquiries are found for the specified camp, an exception is thrown.
     * 
     * @param campID the ID(Name) of the camp whose enquiries are to be retrieved
     * @return a list of string representations of the enquiries made to the specified camp
     * @throws Exception if no enquiries are found for the specified camp
     */
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

        if(CampEnquiriesList.size() == 0){
            throw new NullPointerException("No enquiries found");
        }

        //For testing
        // for(String s: CampEnquiriesList){
        //     System.out.println(s.toString());
        // }

        return CampEnquiriesList;
    }

    /**
     * Replies to an enquiry made to a specific camp.
     * 
     * This method iterates over the feedback list, and for each feedback that is an instance of Enquiry
     * that is associated with the specified camp, it checks if the enquiry has been replied to.
     * If the enquiry has not been replied to, it is updated with the specified reply content.
     * 
     * If no enquiries are found for the specified camp, or if the specified enquiry has already been replied to,
     * an exception is thrown.
     * 
     * @param feedbackID the ID of the enquiry to be replied to
     * @param campID the ID(Name) of the camp the enquiry is associated with
     * @param replyContent the reply content
     * @throws Exception if no enquiries are found for the specified camp, or if the specified enquiry has already been replied to
     */
    @Override
    public void replyEnquiry(int feedbackID, ArrayList<String> regCampList, String replyContent) throws Exception {
        // loop to check if feedbackID is in feedbackList
        boolean isFound = false;
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID && f instanceof Enquiry) {
                Enquiry e = (Enquiry) f;
                // check if the campID for the feedback is in the regCampList
                if (regCampList.contains(e.getCampID())) {
                    e.setContent(replyContent);
                    e.setIsReplied(true);

                    isFound = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
        }

        if (!isFound) {
           throw new Exception("Enquiry not found or camp not in regCampList");
        }
    }

    /**
     * Retrieves a list of unapproved suggestions made by a specific user.
     *
     * This method iterates over the feedback list and checks if the feedback is a suggestion made by the user specified by CampCommID.
     * If the suggestion is not approved, it is added to the SuggestionList.
     * If no suggestions are found, a NullPointerException is thrown.
     *
     * @param CampCommID The ID of the user whose suggestions are to be retrieved.
     * @return An ArrayList of unapproved suggestions from the specified user in String format.
     * @throws NullPointerException If no suggestions are found for the specified user.
     */
    @Override
    public ArrayList<String> getSuggestions(String CampCommID) {
        ArrayList<String> SuggestionList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                // check if suggestion is not approved
                if (!s.isApproved()) {
                    SuggestionList.add(f.toString());
                }
            }
        }

        if(SuggestionList.size() == 0){
            throw new NullPointerException("No suggestions found");
        }

        // // For testing
        // for(String s: SuggestionList){
        //     System.out.println(s.toString());
        // }

        return SuggestionList;
    }

    /**
     * Edits the content of an unapproved suggestion made by a specific user.
     *
     * This method iterates over the feedback list and checks if the feedback is a suggestion made by the user specified by CampCommID and has the specified feedbackID.
     * If the suggestion is not approved, its content is updated with newContent, the file is updated, and the method ends.
     * If no matching unapproved suggestion is found, a NullPointerException is thrown.
     *
     * @param feedbackID The ID of the feedback to be edited.
     * @param CampCommID The ID(Name) of the user who made the suggestion.
     * @param newContent The new content for the suggestion.
     * @throws NullPointerException If no matching unapproved suggestion is found.
     */
    @Override
    public void editSuggestion(int feedbackID, String CampCommID, String newContent) {
        boolean isEdited = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before editing
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                // check if suggestion is not approved
                if (!s.isApproved()) {
                    f.setContent(newContent);
                    isEdited = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if (!isEdited) {
                throw new NullPointerException("Suggestion not found or already approved");
            }
        }
    }

    /**
     * Deletes an unapproved suggestion made by a specific user.
     *
     * This method iterates over the feedback list and checks if the feedback is a suggestion made by the user specified by CampCommID and has the specified feedbackID.
     * If the suggestion is not approved, it is removed from the feedback list, the file is updated, and the method ends.
     * If no matching unapproved suggestion is found, a NullPointerException is thrown.
     *
     * @param feedbackID The ID of the feedback to be deleted.
     * @param CampCommID The ID(Name) of the user who made the suggestion.
     * @throws NullPointerException If no matching unapproved suggestion is found.
     */
    @Override
    public void deleteSuggestion(int feedbackID, String CampCommID) {
        boolean isFound = false;
        for (Feedback f : feedbackList) {
            // check if feedback belongs to CC and is a suggestion before deleting
            if (f.getFeedbackID() == feedbackID && f.getUserID().equals(CampCommID) && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;
                // check if suggestion is not approved
                if (!s.isApproved()) {
                    feedbackList.remove(f);
                    isFound = true;

                    // //For testing
                    //System.out.println("Suggestion deleted");

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        if (!isFound) {
           throw new NullPointerException("Suggestion not found");
        }
    }

    /**
     * Submits a suggestion to the system.
     *
     * This method creates a new Suggestion object with the specified user ID, camp ID, and content.
     * The new Suggestion object is added to the feedback list.
     *
     * @param CampCommID The ID(Name) of the user who made the suggestion.
     * @param campID The ID(Name) of the camp the suggestion is associated with.
     * @param content The content of the suggestion.
     */
    @Override
    public void submitSuggestion(String CampCommID, String campID, String content) {
        int newFeedbackID = feedbackList.size() + 1;
        Suggestion suggestion = new Suggestion(newFeedbackID, CampCommID, campID, content, false);

        addFeedback(suggestion);

    }

    // --- iFeedbackStaff --- (enquiry methods done in iFeedbackCC)

    /**
     * Retrieves a list of suggestions made to a specific camp.
     *
     * This method iterates over the feedback list and checks if the feedback is a suggestion made to the specified camp.
     * If the suggestion is approved, it is added to the CampSuggestionsList.
     * If no suggestions are found for the specified camp, a NullPointerException is thrown.
     *
     * @param campID The ID(Name) of the camp whose suggestions are to be retrieved.
     * @return An ArrayList of approved suggestions made to the specified camp in String format.
     * @throws NullPointerException If no suggestions are found for the specified camp.
     */
    @Override
    public ArrayList<String> getCampSuggestions(String campID) {
        ArrayList<String> CampSuggestionsList = new ArrayList<>();

        for (Feedback f : feedbackList) {
            if (f.getCampID().equals(campID) && f instanceof Suggestion) {
                CampSuggestionsList.add(f.toString());
            }
        }

        if(CampSuggestionsList.size() == 0){
            throw new NullPointerException("No suggestions found");
        }
        // //For testing
        // for(String s: CampSuggestionsList){
        //     System.out.println(s.toString());
        // }
        return CampSuggestionsList;
    }

    /**
     * Approves a suggestion made to a specific camp.
     *
     * This method iterates over the feedback list and checks if the feedback is a suggestion made to the specified camp.
     * If the suggestion is not approved, it is updated to approved, the file is updated, and the method ends.
     * If no matching suggestion is found, a NullPointerException is thrown.
     *
     * @param feedbackID The ID of the feedback to be approved.
     * @param campID The ID(Name) of the camp the suggestion is associated with.
     * @throws NullPointerException If no matching suggestion is found.
     */
    @Override
    public void approveSuggestion(int feedbackID) {
        boolean isFound = false;
        for (Feedback f : feedbackList) {
            if (f.getFeedbackID() == feedbackID && f instanceof Suggestion) {
                Suggestion s = (Suggestion) f;

                if (!s.isApproved()) {
                    s.setIsApproved(true);
                    isFound = true;

                    // update file
                    writeSerialisedObj();
                    break;
                }
            }      
        }

        if (!isFound) {
                throw new NullPointerException("Suggestion not found or already approved");
        }
    }
}