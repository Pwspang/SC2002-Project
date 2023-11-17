package viewPackage;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;


public class staffUI implements iView{
    private static staffUI ui=null;
    private static String[] options = {"Logout", "Change password", "Create Camp", "Edit Camp", "View All Camps", "Toggle Visibility of Camp", "View Suggestions", "Generate Report"};

    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   
        terminal.setBookmark("staffUI");

        // To change header
        terminal.println("Options for staff");
        terminal.println(iView.displayOptionTable(options));

        int choice = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(options.length)
                .read("Option: ");

        terminal.resetToBookmark("staffUI");
        return choice;
    };

    public static iView getInstance(){
        if (staffUI.ui == null){
            staffUI.ui = new staffUI();
        }
        return staffUI.ui;
    }

    public void handleOption(int option){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        switch(option){
            case 3:
                terminal.println("Create Camp");
                break;
            case 4:
                terminal.println("Edit Camp");
                break;
            case 5:
                terminal.println("View all Camps");
                break;
            case 6: 
                terminal.println("Toggle Visibility");
                break;
            case 7:
                terminal.println("View Suggestion");
            case 8: 
                terminal.println("");
        }
    }
}
