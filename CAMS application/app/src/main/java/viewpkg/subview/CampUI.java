package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import de.vandermeer.asciitable.*;

import java.util.ArrayList;

import authenticationpkg.*;;


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

        startDate = textIO.newStringInputReader()
            .read("Start Date");

        endDate = textIO.newStringInputReader()
            .read("End Date");
        
        registrationClosingDate = textIO.newStringInputReader()
            .read("Registration Closing Date");
        
        openToWholeNTU = textIO.newBooleanInputReader()
            .withDefaultValue(true)
            .read("Open to Whole NTU");
        
        location = textIO.newStringInputReader()
            .read("Location");
        
        totalSlots = textIO.newIntInputReader()
            .read("Total Slots");
        
        campComitteeSlots = textIO.newIntInputReader()
            .read("Camp Comittee Slots");
        
        description = textIO.newStringInputReader()
            .read("Description");
        try{
            user.createCamp(campName, startDate, endDate, registrationClosingDate, openToWholeNTU, Faculty.NBS, location, totalSlots, campComitteeSlots, description);
        } catch (Exception e){
            terminal.println(e.getMessage());
            return;
        }
        
        // To edit
        terminal.println("Camp created.");
        
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

        ArrayList<String> campIDList = user.getAllCamps();

        terminal.setBookmark("viewAllCamps");

        displayCamps(campIDList);

        if (campIDList.size() == 0){
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campIDList.size())
            .read("View details of Camp");

        terminal.resetToBookmark("viewAllCamps");

        displayCampDetails(campIDList.get(campChoice-1), user);
    }

    /**
     * Display camps that staff created -> Select from list -> Set visibility y/n
     * @param user
     */
    public static void toggleVisibilityCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        ArrayList<String> campList = user.getCreatedCamps();

        terminal.setBookmark("toggleVisibilityCamps");

        displayCamps(campList);

        if (campList.size() == 0){
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campList.size())
            .read("View details of Camp");

        terminal.resetToBookmark("toggleVisibilityCamps");


    }

    public static void viewAvailableCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getVisibleCampList();

        terminal.setBookmark("viewAvailableCamp");

        displayCamps(campIDList);

        if (campIDList.size() == 0){
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campIDList.size())
            .read("View details of Camp");        

        terminal.resetToBookmark("viewAvailableCamp");

        displayCampDetails(campIDList.get(campChoice-1), user);

    }
    public static void viewRegisteredCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getRegisteredCampList("CCMember");

        terminal.setBookmark("viewRegisteredCamp");

        displayCamps(campIDList);

        if (campIDList.size() == 0){
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campIDList.size())
            .read("View details of Camp");        

        terminal.resetToBookmark("viewRegisteredCamp");

        displayCampDetails(campIDList.get(campChoice-1), user); 
    }
    public static void registerForCamp(AuthStudent user){

    }
    public static void withdrawFromCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getRegisteredCampList("CCMember");

        terminal.setBookmark("viewRegisteredCamp");

        displayCamps(campIDList);

        if (campIDList.size() == 0){
            return;
        }

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campIDList.size())
            .read("View details of Camp");        

        terminal.resetToBookmark("viewRegisteredCamp");

        displayCampDetails(campIDList.get(campChoice-1), user); 
    }

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

        terminal.println(at.render());

    }
      
    public static void displayCampDetails(String campID, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        terminal.print(user.getCamp(campID).toString());
    }
}
