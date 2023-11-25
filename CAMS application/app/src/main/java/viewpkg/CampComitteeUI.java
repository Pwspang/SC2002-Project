package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import viewpkg.subview.CampUI;
import viewpkg.subview.FeedbackUI;

/**
 * UI for CampComittee
 */
public class CampComitteeUI extends StudentUI{
    private static CampComitteeUI ui=null;
    private static String[] options = {"Exit Program", "Logout", "Change password", "View available camps", "Register for Camp", "View Registered Camps", "Submit Enquiry", "Edit Enquiry", "Delete Enquiry", "Withdraw from Camp", "Submit Suggestions", "View Enquiry", "Reply Enquiry", "View Suggestion", "Edit Suggestion", "Delete Suggestion", "Generate Report"};
    /**
     * Get the instance of CampComitteeUI
     * @return CampComitteeUI object as a singleton object
     */
    public static iView getInstance(){
        if (CampComitteeUI.ui == null){
            CampComitteeUI.ui = new CampComitteeUI();
        }
        return CampComitteeUI.ui;
    }

    /**
     * Display options and get user selection
     */
    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();   
        terminal.setBookmark("campComitteeUI");

        // To change header
        terminal.println(iView.displayHeader("CampComittee MainPage"));

        // Print options table
        terminal.println(iView.displayOptionTable(options));

        int choice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(options.length)
            .read("Option: ");


        terminal.resetToBookmark("campComitteeUI");
        return choice;
    };


    /**
     * Handles Selection input
     * @param option Selected Option
     * @param user AuthUser 
     */
    public void handleOption(int option, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        terminal.setBookmark("campcomitteeUI");

        terminal.println(iView.displayHeader("Camp Comittee: " + options[option-1]));

        switch(option){
            case 4:
                //view available camp
                super.handleOption(option, user);
                break;
            case 5:
                //register for camp
                super.handleOption(option, user);
                break;
            case 6:
                // View Registered Camp
                super.handleOption(option, user);
                break;
            case 7:
                //submit enquiry 
                super.handleOption(option, user);
                break;
            case 8:
                // Edit enquiry 
                super.handleOption(10, user);
                break;
            case 9:
                // Delete Enquiry
                super.handleOption(9, user);
                break;
            case 10: 
                // Withdraw from camp
                super.handleOption(11, user);
                break;
            case 11:
                FeedbackUI.submitSuggestion((AuthCCMember) user);
                break;
            case 12: 
                FeedbackUI.viewEnquiry((AuthCCMember) user);
                break;
            case 13: 
                FeedbackUI.replyEnquiry((AuthCCMember) user);
                break;
            case 14:
                FeedbackUI.viewSuggestion((AuthCCMember) user);
                break;
            case 15: 
                FeedbackUI.editSuggestion((AuthCCMember) user);
                break;
            case 16: 
                FeedbackUI.deleteSuggestion((AuthCCMember) user);
                break;
            case 17: 
                CampUI.generateCampReport((AuthCCMember) user);
                break;

        }

        if (option > 10){
            textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");
        }
       
        terminal.resetToBookmark("campcomitteeUI");

    };


}
