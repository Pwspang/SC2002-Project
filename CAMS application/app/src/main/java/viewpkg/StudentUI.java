package viewpkg;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import viewpkg.subview.CampUI;

public class StudentUI implements iView{
    private static StudentUI ui=null;
    private static String[] options = {"Exit Program", "Logout", "Change password", "View Available Camps", "Register for Camp", "View Registered Camps", "Submit Enquiry for Camp", "View Reply to Enquiry", "Withdraw from Camp"};

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

        switch(option){
            case 4:
                //view available camp
                CampUI.viewAvailableCamp((AuthStudent) user);
                break;
            case 5:
                //register for camp
                CampUI.registerForCamp((AuthStudent) user);
                break;
            case 6:
                // View Registered Camp
                CampUI.viewRegisteredCamp((AuthStudent) user);
                break;
            case 7: 
                terminal.println("Submit Enquiry for Camp");
                break;
            case 8:
                terminal.println("View Reply to Enquiry");
                break;
            case 9: 
                // Withdraw from camp
                CampUI.withdrawFromCamp((AuthStudent) user);
                break;
        };
    }
}

