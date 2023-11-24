package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import viewpkg.subview.CampUI;
import viewpkg.subview.FeedbackUI;

public class StudentUI implements iView{
    private static StudentUI ui=null;
    private static String[] options = {"Exit Program", "Logout", "Change password", "View Available Camps", "Register for Camp", "View Registered Camps", "Submit Enquiry for Camp", "View Enquiry", "Delete Enquiry", "Edit Enquiry", "Withdraw from Camp"};

    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   
        terminal.setBookmark("studentUI");

        // To change header
        terminal.println(iView.displayHeader("Student MainPage"));

        // Print options table
        terminal.println(iView.displayOptionTable(options));

        int choice = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(options.length)
                .read("Option: ");

        terminal.resetToBookmark("studentUI");
        return choice;
    };

    public static iView getInstance(){
        if (StudentUI.ui == null){
            StudentUI.ui = new StudentUI();
        }
        return StudentUI.ui;
    }

    public void handleOption(int option, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        terminal.setBookmark("studentUI");

        terminal.println(iView.displayHeader("Student: " + options[option-1]));

        switch(option){
            case 4:
                CampUI.viewAvailableCamp((AuthStudent) user);
                break;
            case 5:
                CampUI.registerForCamp((AuthStudent) user);
                break;
            case 6:
                CampUI.viewRegisteredCamp((AuthStudent) user);
                break;
            case 7: 
                FeedbackUI.submitEnquiry((AuthStudent) user);
                break;
            case 8:
                FeedbackUI.viewEnquiry((AuthStudent) user);
                break;
            case 9:
                FeedbackUI.deleteEnquiry((AuthStudent) user);
                break;
            case 10:
                FeedbackUI.editEnquiry((AuthStudent) user);
                break;  
            case 11: 
                // Withdraw from camp
                CampUI.withdrawFromCamp((AuthStudent) user);
                break;
        };

        textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");

        terminal.resetToBookmark("studentUI");
    }
}

