package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;

import java.util.ArrayList;

import de.vandermeer.asciitable.*;

/**
 * Class for handling UI related to Feedback
 */
public class FeedbackUI {
    /**
     * Display unanswered enquiries for user to select, user can reply to the selected enquiry
     * @param user AuthStaff
     */
    public static void replyEnquiry(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        viewEnquiry(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Enquiry ID: ");
        
        String reply = textIO.newStringInputReader()
            .read("Enter reply");

        try {
            user.replyEnquiry(feedbackID, reply);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
        

    }
    /**
     * UI for staff to view all enquiries
     * @param user AuthStaff
     */
    public static void viewEnquiry(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        try{   

            ArrayList<String> enquiries = user.getCampEnquiries();

            displayFeedback(enquiries);

        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }

    /**
     * UI for staff to view all suggestions
     * @param user AuthStaff
     */
    public static void viewSuggestion(AuthStaff user){
        // ArrayList<Integer> feedbackIDList = user.getFeedback();
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        // Select Camp to view suggestion 
        ArrayList<String> campIDList = user.getCreatedCamps();

        campIDList.add("Back to main menu");

        terminal.setBookmark("selectCamp");

        int campChoice = CampUI.displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }

        terminal.resetToBookmark("selectCamp");

        try{   
            ArrayList<String> feedbackIDList = user.getCampSuggestions(campIDList.get(campChoice-1));

            displayFeedback(feedbackIDList);

        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }

    /**
     * Display unanswered Suggestions for user to select, user can approve/reject/do nothing to the suggestion
     * @param user AuthStaff
     */
    public static void replySuggestion(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        viewSuggestion(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter FeedbackID");

        try {
            user.approveSuggestion(feedbackID);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
    }

    /**
     * Display a list of camps that the user is registered in but not part of camp comittee, user can choose from the camp to submit enquiry
     * @param user AuthStudent
     */
    public static void submitEnquiry(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        ArrayList<String> campIDList = user.getVisibleCampList();
        
        int option = CampUI.displayCamps(campIDList);

        if (campIDList.size() == 0) {
            return;
        }

        String content = textIO.newStringInputReader()
            .read("Enter enquiry");
        
        try {
            user.submitEnquiry(campIDList.get(option-1), content);
        } catch (Exception e) {
            terminal.println(e.getMessage());
        }
    }

    /**
     * Display a list of enquiry that the user previously submitted
     * @param user AuthStudent
     */
    public static void viewEnquiry(AuthStudent user){
        ArrayList<String> feedback = user.getEnquiries();
        displayFeedback(feedback);
    }

    /**
     * Display a list of enquiry that the user previously submitted, user can choose from this list to edit their enquiry
     * @param user AuthStudent
     */
    public static void editEnquiry(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        viewEnquiry(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Enquiry ID");
        
        String newContent = textIO.newStringInputReader()
            .read("Enter content");
    
        try {
            user.editEnquiry(feedbackID, newContent);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
    }

    /**
     * Display a list of enquiry that the user previously submitted, use can choose from this list to delete their enquiry
     * @param user AuthStudent
     */
    public static void deleteEnquiry(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  
        viewEnquiry(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Enquiry ID");
        
        try {
            user.deleteEnquiry(feedbackID);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
    }

    /**
     * Display all enquiry that user is a CCMember of a camp
     * @param user AuthCCMember
     */
    public static void viewEnquiry(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        try {
            ArrayList<String> feedback = user.getCampEnquiries();

            displayFeedback(feedback);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }

    /**
     * Display unanswered enquiry, user can select an enquiry and reply to it
     * @param user AuthCCMember
     */
    public static void replyEnquiry(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        viewEnquiry(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Feedback ID");
        
        String content = textIO.newStringInputReader()
            .read("Enter Content");
        
        try {
            user.replyEnquiry(feedbackID, content);
        } catch (Exception e) {
            terminal.println(e.getMessage());
        }
    }

    /**
     * Submit Suggestion to camp that CC is part of
     * @param user AuthCCMember
     */
    public static void submitSuggestion(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        ArrayList<String> campIDList = user.getRegisteredCampList("CCMember");

        if (campIDList.size() == 0) {
            return;
        }

        String content = textIO.newStringInputReader()
            .read("Enter Suggestion");
        
        try {
            user.submitSuggestion(campIDList.get(0), content);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
        
    }

    /**
     * Display List of suggestions that CC submited
     * @param user AuthCCMember
     */
    public static void viewSuggestion(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        ArrayList<String> feedbackList = user.getSuggestions();
        try {
            displayFeedback(feedbackList);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
        
    }

    /**
     * Display list of suggestions that CC submited, let user select and editSuggestion
     * @param user AuthCCMember
     */
    public static void editSuggestion(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();  

        viewSuggestion(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Suggestion ID");
        
        String content = textIO.newStringInputReader()
            .read("Enter content");
        
        try {
            user.editSuggestion(feedbackID, content);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }

        
    }

    /**
     * Display list of suggestions that CC submited, let user select and deleteSuggestion
     * @param user AuthCCMember
     */ 
    public static void deleteSuggestion(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();

        viewSuggestion(user);

        int feedbackID = textIO.newIntInputReader()
            .read("Enter Suggestion ID");

        try {
            user.deleteSuggestion(feedbackID);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }

    /**
     * Helper function to print the list of feedback in asciitable format
     * @param feedbackIDList ArrayList containing feedbackID to be displayed
     */
    public static void displayFeedback(ArrayList<String> feedbackIDList){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        if (feedbackIDList.size() == 0){
            terminal.println("No feedback found.");
            return;
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Feedback ID", "User ID", "Camp ID", "Content", "Reply");
        at.addRule();
        for (String s: feedbackIDList){
            String [] si = s.split(":");
            at.addRow(si[0], si[1], si[2], si[3], si[4]);
            at.addRule();
        }
        at.getRenderer().setCWC(new CWC_LongestLine());
        at.setPaddingLeftRight(1);
        terminal.println(at.render());

    }

}
