package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;

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
                terminal.println("Submit Suggestions");
                break;
            case 9: 
                terminal.println("View Enquiry");
                break;
            case 10: 
                terminal.println("Reply Enquiry");
                break;
            case 11: 
                terminal.println("Edit Suggestion");
                break;
            case 12: 
                terminal.println("Delete Suggestion");
                break;
            case 13: 
                terminal.println("Generate Report");
                break;

        }
    };


}
