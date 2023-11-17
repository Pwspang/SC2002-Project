package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

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
        
        
    }
     
    public static void editCamp(AuthStaff user){}
    public static void viewAllCamps(AuthStaff user){}
    public static void toggleVisibilityCamp(AuthStaff user){}

    public static void viewAvailableCamp(AuthStudent user){}
    public static void viewRegisteredCamp(AuthStudent user){}
    public static void registerForCamp(AuthStudent user){}
    public static void withdrawFromCamp(AuthStudent user){}

    public static void displayCamps(ArrayList<String> campIDList){}
      
    public static void displayCampDetails(String campID, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        terminal.print(user.getCamp(campID));
    }
}
