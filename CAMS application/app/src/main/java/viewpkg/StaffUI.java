package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import feedbackpkg.Feedback;
import viewpkg.subview.CampUI;
import viewpkg.subview.FeedbackUI;



public class StaffUI implements iView{
    private static StaffUI ui=null;
    private static String[] options = {"Exit Program", "Logout", "Change password", "Create Camp", "Edit Camp", "View All Camps", "Toggle Visibility of Camp", "View Enquiry", "Reply Enquriy", "View Suggestions","Accept/Reject Suggestion", "Generate Report"};

    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   
        terminal.setBookmark("staffUI");

        // To change header
        terminal.println(iView.displayHeader("Staff Main Page"));
        terminal.println(iView.displayOptionTable(options));

        int choice = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(options.length)
                .read("Option: ");

        terminal.resetToBookmark("staffUI");
        return choice;
    };

    public static iView getInstance(){
        if (StaffUI.ui == null){
            StaffUI.ui = new StaffUI();
        }
        return StaffUI.ui;
    }

    public void handleOption(int option, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        terminal.setBookmark("staffUI");

        terminal.println(iView.displayHeader("Staff: " + options[option]));

        switch(option){
            case 4:
                CampUI.createCamp((AuthStaff)user);
                break;
            case 5:
                CampUI.editCamp((AuthStaff)user);
                break;
            case 6:
                CampUI.viewAllCamps((AuthStaff)user);
                break;
            case 7: 
                CampUI.toggleVisibilityCamp((AuthStaff)user);
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                FeedbackUI.viewSuggestion((AuthStaff) user);
                break;
            case 11: 
                FeedbackUI.replySuggestion((AuthStaff) user);
                break;
            case 12:
                break;
        }

        textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");

        terminal.resetToBookmark("staffUI");
    }
}
