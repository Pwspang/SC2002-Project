package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import feedbackpkg.FeedbackManager;
import viewpkg.subview.FeedbackUI;

public class CampComitteeUI extends StudentUI{
    private static CampComitteeUI ui=null;
    private static String[] options = {"Exit Program", "Logout", "Change password", "View available camps", "Register for Camp", "View Registered Camps", "Withdraw from Camp", "Submit Suggestions", "View Enquiry", "Reply Enquiry", "Edit Suggestion", "Delete Suggestion", "Generate Report"};

    public static iView getInstance(){
        if (CampComitteeUI.ui == null){
            CampComitteeUI.ui = new CampComitteeUI();
        }
        return CampComitteeUI.ui;
    }

    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   
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
     * 
     */
    public void handleOption(int option, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

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
                // Withdraw from camp
                super.handleOption(9, user);
                break;
            case 8:
                FeedbackUI.submitSuggestion((AuthCCMember) user);
                terminal.println("Submit Suggestions");
                break;
            case 9: 
                FeedbackUI.viewEnquiry((AuthCCMember) user);
                terminal.println("View Enquiry");
                break;
            case 10: 
                FeedbackUI.replyEnquiry((AuthCCMember) user);
                terminal.println("Reply Enquiry");
                break;
            case 11: 
                FeedbackUI.editSuggestion((AuthCCMember) user);
                terminal.println("Edit Suggestion");
                break;
            case 12: 
                FeedbackUI.deleteSuggestion((AuthCCMember) user);
                terminal.println("Delete Suggestion");
                break;
            case 13: 
                terminal.println("Generate Report");
                break;

        }

        textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");

        terminal.resetToBookmark("campcomitteeUI");

    };


}
