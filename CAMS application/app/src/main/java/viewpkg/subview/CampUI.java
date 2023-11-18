package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import de.vandermeer.asciitable.*;

import java.util.ArrayList;

import authenticationpkg.*;

public class CampUI {
        /**
     * Get user input to create a camp object
     * @param user
     */
    public static void createCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        String campName, startDate, endDate, registrationClosingDate, location, description;
        boolean openToWholeNTU;
        int totalSlots, campComitteeSlots;

        campName = textIO.newStringInputReader()
            .read("Camp Name");
        

        //To Call user.createCamp() here
        
    }
    /**
     * Display camps that staff created -> select from list -> select field to edit -> enter new field
     * @param user
     */
    public static void editCamp(AuthStaff user){
        
    }

    /**
     * Display all camps in camp manager -> select from list -> display detailed information
     * @param user
     */
    public static void viewAllCamps(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        ArrayList<String> campList = user.getAllCamps();

        terminal.setBookmark("viewAllCamps");

        displayCamps(campList);

        if (campList.size() == 0){
            textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campList.size())
            .read("View details of Camp: ");

        terminal.resetToBookmark("viewAllCamps");

        displayCampDetails(campList.get(campChoice-1), user);
        textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");
    }

    /**
     * Display camps that staff created -> Select from list -> Set visibility y/n
     * @param user
     */
    public static void toggleVisibilityCamp(AuthStaff user){}

    public static void viewAvailableCamp(AuthStudent user){}
    public static void viewRegisteredCamp(AuthStudent user){}
    public static void registerForCamp(AuthStudent user){}
    public static void withdrawFromCamp(AuthStudent user){}

    public static void displayCamps(ArrayList<String> campIDList){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        //CampList is empty 
        if (campIDList.size() == 0){
            terminal.println("No available camps");
            return;
        }

        AsciiTable at = new AsciiTable();

        at.addRule();
        for (int i=0; i < campIDList.size(); i++){
            at.addRow(i+1, campIDList);
            at.addRule();
        }

        terminal.print(at.render());

    }
      
    public static void displayCampDetails(String campID, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        terminal.print(user.getCamp(campID).toString());
    }
}
