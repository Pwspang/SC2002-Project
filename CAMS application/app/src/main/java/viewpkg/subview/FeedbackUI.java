package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;

import java.util.ArrayList;

import de.vandermeer.asciitable.*;


public class FeedbackUI {
    /**
     * Display unanswered enquiries for user to select, user can reply to the selected enquiry
     * @param user AuthStaff object
     */
    public static void replyEnquiry(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        // ArrayList<Integer> feedbackIDList = user.getEnquiry();
        // ArrayList<Integer> feedbackIDList = user.getFeedback();

        //displayEnquiry(feedbackIDList);

        textIO.newBooleanInputReader()
            .withDefaultValue(true)
            .read("Enter to continue");
    }

    /**
     * Display a list of suggestions that the staff owns
     * @param user
     */
    public static void viewSuggestion(AuthStaff user){
        // ArrayList<Integer> feedbackIDList = user.getFeedback();
        
    }

    /**
     * Display unanswered Suggestions for user to select, user can approve/reject/do nothing to the suggestion
     * @param user
     */
    public static void replySuggestion(AuthStaff user){}

    /**
     * Display a list of camps that the user is registered in but not part of camp comittee, user can choose from the camp to submit enquiry
     * @param user
     */
    public static void submitEnquiry(AuthStudent user){}

    /**
     * Display a list of enquiry that the user previously submitted
     * @param user
     */
    public static void viewEnquiry(AuthStudent user){}

    /**
     * Display a list of enquiry that the user previously submitted, user can choose from this list to edit their enquiry
     * @param user
     */
    public static void editEnquiry(AuthStudent user){}

    /**
     * Display a list of enquiry that the user previously submitted, use can choose from this list to delete their enquiry
     * @param user
     */
    public static void deleteEnquiry(AuthStudent user){}

    /**
     * Display all enquiry that user is a CCMember of a camp
     * @param user
     */
    public static void viewEnquiry(AuthCCMember user){}

    /**
     * Display unanswered enquiry, user can select an enquiry and reply to it
     * @param user
     */
    public static void replyEnquiry(AuthCCMember user){}

    /**
     * Submit Suggestion to camp that CC is part of
     * @param user
     */
    public static void submitSuggestion(AuthCCMember user){}

    /**
     * Display List of suggestions that CC submited
     * @param user
     */
    public static void viewSuggestion(AuthCCMember user){}

    /**
     * Display list of suggestions that CC submited, let user select and editSuggestion
     * @param user
     */
    public static void editSuggestion(AuthCCMember user){}

    /**
     * Display list of suggestions that CC submited, let user select and deleteSuggestion
     * @param user
     */
    public static void deleteSuggestion(AuthCCMember user){}

    /**
     * Print that feedbackIDList in asciitable format
     * @param feedbackIDList
     */
    public static void displayEnquiry(ArrayList<Integer> feedbackIDList){
        //feedbackManager.getFeedback();
        AsciiTable at = new AsciiTable();
        at.addRule();
    }

    /**
     * Print that feedbackIDList in asciitable format
     * @param feedbackIDList
     */
    public static void displaySuggestion(ArrayList<Integer> feedbackIDList){
        //feedbackManager.getFeedback();
    }

}
