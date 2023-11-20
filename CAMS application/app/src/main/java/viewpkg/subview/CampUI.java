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
        // To do fix formatting
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
        terminal.printf("%s created.\n");
        
    }
    /**
     * Display camps that staff created -> select from list -> select field to edit -> enter new field
     * @param user
     */
    public static void editCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getCreatedCamps();

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }

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

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }

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

        ArrayList<String> campIDList = user.getCreatedCamps();

        campIDList.add("Back to main menu");

        terminal.setBookmark("toggleVisibilityCamps");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }

        terminal.resetToBookmark("toggleVisibilityCamps");

    }

    public static void viewAvailableCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getVisibleCampList();

        terminal.setBookmark("viewAvailableCamp");

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }   

        terminal.resetToBookmark("viewAvailableCamp");

        displayCampDetails(campIDList.get(campChoice-1), user);

    }
    // TO DO indicate if the camp is Attendee or CCMember
    public static void viewRegisteredCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getRegisteredCampList("Attendee");
        campIDList.addAll(user.getRegisteredCampList("CCMember"));

        terminal.setBookmark("viewRegisteredCamp");

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }  

        terminal.resetToBookmark("viewRegisteredCamp");

        displayCampDetails(campIDList.get(campChoice-1), user); 
    }

    public static void registerForCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getVisibleCampList();

        terminal.setBookmark("registerForCamp");

        campIDList.add("Back to Main Menu");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }
            
        String roleID = textIO.newStringInputReader()
            .withInlinePossibleValues("Attendee","CCMember")
            .read("Role");

        terminal.resetToBookmark("registerForCamp");

        try {
            user.register(campIDList.get(campChoice-1), roleID);
            terminal.printf("Successfully registered for %s as %s.\n", campIDList.get(campChoice-1), roleID);
        } catch(Exception e){
            terminal.println(e.getMessage());
        }

    }
    public static void withdrawFromCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getRegisteredCampList("Attendee");

        campIDList.add("Back to main menu");

        terminal.setBookmark("withdrawFromCamp");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }       

        terminal.resetToBookmark("withdrawFromCamp");

        try {
            user.withdraw(campIDList.get(campChoice-1));
            terminal.println("Succesfully withdraw from " + campIDList.get(campChoice-1));
        } catch (Exception e){
            terminal.println(e.getMessage());
        }
    }

    public static int displayCamps(ArrayList<String> campIDList){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 

        //CampList is empty 
        if (campIDList.size() == 0){
            terminal.println("No available camps");
            return 0;
        }

        AsciiTable at = new AsciiTable();

        at.addRule();
        for (int i=0; i < campIDList.size(); i++){
            at.addRow(i+1, campIDList.get(i));
            at.addRule();
        }
        at.getRenderer().setCWC(new CWC_LongestLine());
        at.setPaddingLeftRight(1);
        terminal.println(at.render());

        int campChoice = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(campIDList.size())
            .read("Select Camp");

        return campChoice;
    }
      
    public static void displayCampDetails(String campID, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        terminal.print(user.getCamp(campID).toString());
    }
}
