package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import de.vandermeer.asciitable.*;
import reportpkg.ReportManager;
import searchfilterpkg.SearchFilterManager;

import java.util.*;

import authenticationpkg.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * CampUI Class to handle UI related to Camps
 */
public class CampUI {
    /**
     * UI for Staff Create Camp
     * @param user AuthStaff user 
     */
    public static void createCamp(AuthStaff user){
        // To do fix formatting
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        
        String campName, startDate, endDate, registrationClosingDate, location, description;
        boolean openToWholeNTU;
        int totalSlots, campComitteeSlots;

        campName = textIO.newStringInputReader()
            .read("Camp Name");
        
        Pattern pattern = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        // Checking date format 
        do {
            startDate = textIO.newStringInputReader()
                .read("Start Date");
            matcher = pattern.matcher(startDate);
            if (matcher.find()) {
                break;
            }
            terminal.println("Invalid date format use YYYY-MM-DD.");
        } while (true);

        do {
            endDate = textIO.newStringInputReader()
                .read("Start Date");
            matcher = pattern.matcher(endDate);
            if (matcher.find()) {
                break;
            }
            terminal.println("Invalid date format use YYYY-MM-DD.");
        } while (true);

        do {
            registrationClosingDate = textIO.newStringInputReader()
                .read("Start Date");
            matcher = pattern.matcher(registrationClosingDate);
            if (matcher.find()) {
                break;
            }
            terminal.println("Invalid date format use YYYY-MM-DD.");
        } while (true);
        
        openToWholeNTU = textIO.newBooleanInputReader()
            .withDefaultValue(true)
            .read("Open to Whole NTU");
        
        location = textIO.newStringInputReader()
            .read("Location");

        totalSlots = textIO.newIntInputReader()
            .withMinVal(10)
            .read("Total Slots");
        
        campComitteeSlots = textIO.newIntInputReader()
            .withMinVal(1)
            .withMaxVal(10)
            .read("Camp Comittee Slots");
        
        description = textIO.newStringInputReader()
            .read("Description");

        try{
            user.createCamp(campName, startDate, endDate, registrationClosingDate, openToWholeNTU, location, totalSlots, campComitteeSlots, description);
            terminal.printf("%s created.\n", campName);
        } catch (Exception e){
            terminal.println("Camp not created due to " + e.getMessage());
            return;
        }
        
    }
    /**
     * UI to Delete Camp
     * @param user AuthStaff user 
     */
    public static void deleteCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getCreatedCamps();

        campIDList.add("Back to main menu");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }

        try{
            user.deleteCamp(campIDList.get(campChoice-1));
            terminal.println("Camp successfully deleted.");
        } catch (Exception e) {
            terminal.println(e.getMessage());
        }
    }
    /**
     * UI to view created camps
     * @param user AuthStaff user 
     */
    public static void viewCreatedCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getCreatedCamps();

        terminal.setBookmark("viewAllCamps");

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }

        terminal.resetToBookmark("viewAllCamps");

        displayCampDetails(campIDList.get(campChoice-1), user);

    }
    
    /**
     * UI to edit created camps
     * @param user AuthStaff user 
     */
    public static void editCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getCreatedCamps();

        campIDList.add("Back to main menu");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Camp Committee Slots", "Start/End Date", "Registration Ending Date", "Description", "Location", "Open to whole NTU"));
        int option = displayCamps(options);
        Pattern pattern = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        try{
            switch (option){
                case 1:
                int slots = textIO.newIntInputReader().withMaxVal(10).read("Camp Comitte Slots");
                user.editCampCommitteeSlots(campIDList.get(campChoice-1), slots);
                break;
                case 2:
                String startDate, endDate;
                do {
                    startDate = textIO.newStringInputReader()
                        .read("Start Date");
                    matcher = pattern.matcher(startDate);
                    if (matcher.find()) {
                        break;
                    }
                    terminal.println("Invalid date format use YYYY-MM-DD.");
                } while (true);

                do {
                    endDate = textIO.newStringInputReader()
                        .read("Start Date");
                    matcher = pattern.matcher(endDate);
                    if (matcher.find()) {
                        break;
                    }
                    terminal.println("Invalid date format use YYYY-MM-DD.");
                } while (true);
                user.editDate(campIDList.get(campChoice-1), startDate, endDate);
                break;
                case 3:
                String registrationClosingDate;
                do {
                    registrationClosingDate = textIO.newStringInputReader()
                        .read("Start Date");
                    matcher = pattern.matcher(registrationClosingDate);
                    if (matcher.find()) {
                        break;
                    }
                    terminal.println("Invalid date format use YYYY-MM-DD.");
                } while (true);
                user.editRegistrationClosingDate(campIDList.get(campChoice-1), registrationClosingDate);
                break;
                case 4:
                String description = textIO.newStringInputReader()
                    .read("Description");
                user.editDescription(campIDList.get(campChoice-1), description);
                break;
                case 5:
                String location = textIO.newStringInputReader()
                    .read("Location");
                user.editLocation(campIDList.get(campChoice-1), location);
                break;
                case 6:
                Boolean openToWholeNTU = textIO.newBooleanInputReader()
                    .withDefaultValue(true)
                    .read("Open to Whole NTU");
                user.editOpenTo(campIDList.get(campChoice-1), openToWholeNTU);
                break;
            }

        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }

    /**
     * UI to View All Camps
     * @param user AuthStaff user 
     */
    public static void viewAllCamps(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getAllCamps();

        terminal.setBookmark("viewAllCamps");

        SearchFilterManager searchFilterManager = SearchFilterManager.getInstance();

        String nameOfFilter = textIO.newStringInputReader()
            .withNumberedPossibleValues(searchFilterManager.getFilterList())
            .read("Select Filter Option");
        
        if (!nameOfFilter.equals("No Filter")){
            String filterOption = textIO.newStringInputReader()
                .read("Filter by");
            try {
                campIDList = searchFilterManager.filter(nameOfFilter, filterOption, campIDList);
            } catch (Exception e){
                terminal.println("Invalid Filter entered.");
            }
            
        }
        
        
        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }

        terminal.resetToBookmark("viewAllCamps");

        displayCampDetails(campIDList.get(campChoice-1), user);
    }

    /**
     * UI to toggle visibility of camps
     * @param user AuthStaff user 
     */
    public static void toggleVisibilityCamp(AuthStaff user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        ArrayList<String> campIDList = user.getCreatedCamps();

        campIDList.add("Back to main menu");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }

        boolean visible = textIO.newBooleanInputReader()
            .withDefaultValue(true)
            .read("Visible?");

        try {
            user.setVisibility(campIDList.get(campChoice-1), visible);
        } catch (Exception e){
            terminal.println(e.getMessage());
        }

    }
    /**
     * UI to view available camps
     * @param user AuthStudent 
     */
    public static void viewAvailableCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getVisibleCampList();

        terminal.setBookmark("viewAvailableCamp");

        SearchFilterManager searchFilterManager = SearchFilterManager.getInstance();

        String nameOfFilter = textIO.newStringInputReader()
            .withNumberedPossibleValues(searchFilterManager.getFilterList())
            .read("Select Filter Option");
        
        if (!nameOfFilter.equals("No Filter")){
            String filterOption = textIO.newStringInputReader()
                .read("Filter by");
            try {
                campIDList = searchFilterManager.filter(nameOfFilter, filterOption, campIDList);
            } catch (Exception e){
                terminal.println("Invalid Filter entered.");
            }
            
        }

        int campChoice = displayCamps(campIDList);

        if (campChoice == 0){
            return;
        }   

        terminal.resetToBookmark("viewAvailableCamp");

        displayCampDetails(campIDList.get(campChoice-1), user);

    }
    public static void viewRegisteredCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getRegisteredCampList("Attendee");
        ArrayList<String> campIDListCC = user.getRegisteredCampList("CCMember");

        //display camp details for registered camps as attendee 
        if (campIDList.size() > 0){
            terminal.println("===================");
            terminal.println("Joined as attendee.");
            terminal.println("===================");
            for (String campID: campIDList){
                displayCampDetails(campID, user);
            }
        }


        //display detials for CC Camps 
        if (campIDListCC.size() > 0){
            terminal.println("=======================");
            terminal.println("Joined as Camp Comittee");
            terminal.println("=======================");
            for (String campID : campIDListCC) {
                displayCampDetails(campID, user);
            }
        }
    }
    /**
     * UI to register for camp
     * @param user AuthStudent 
     */
    public static void registerForCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        // Should get the camp from user
        ArrayList<String> campIDList = user.getVisibleCampList();

        terminal.setBookmark("registerForCamp");

        campIDList.add("Back to Main Menu");

        int campChoice = displayCamps(campIDList);

        if (campChoice == campIDList.size()){
            return;
        }
            
        String roleID = textIO.newStringInputReader()
            .withNumberedPossibleValues("Attendee","CCMember")
            .read("Role");

        terminal.resetToBookmark("registerForCamp");

        try {
            user.register(campIDList.get(campChoice-1), roleID);
            terminal.printf("Successfully registered for %s as %s.\n", campIDList.get(campChoice-1), roleID);
        } catch(Exception e){
            terminal.println(e.getMessage());
        }

    }
    /**
     * UI to withdraw from camp
     * @param user AuthStudent 
     */
    public static void withdrawFromCamp(AuthStudent user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
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
    /**
     * UI to generate camp report for staff
     * @param user AuthStaff
     */
    public static void generateCampReport(AuthStaff user) {
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        ReportManager reportManager = ReportManager.getInstance();

        //choose a filter
        String filtername = textIO.newStringInputReader()
            .withNumberedPossibleValues(reportManager.getFilterList())
            .read("Select Filter");

        String filename = textIO.newStringInputReader()
            .read("Filename");

        String ID;
        
        if (filtername.equals("ReportFilterCampStudentID")){
            ID = textIO.newStringInputReader()
                .read("Student ID");
        } else {
            ID = textIO.newStringInputReader()
                .withNumberedPossibleValues(user.getCreatedCamps())
                .read("Select Camp ID");

        }

        try { 
            user.writeCampReport(filtername, ID, filename);
            terminal.println("Wrote to " + filename);
        } catch (Exception e) {
            terminal.println(e.getMessage());
        }

    }

    /**
     * UI to generate camp report for CCMember
     * @param user AuthCCMember
     */
    public static void generateCampReport(AuthCCMember user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

        //choose a filter
        String filtername = textIO.newStringInputReader()
            .withNumberedPossibleValues("ReportFilterCampAttendee", "ReportFilterCampCCMember", "ReportFilterCampEnquiry", "ReportFilterCampStudent")
            .read("Select Filter");

        String filename = textIO.newStringInputReader()
            .read("Filename");

        String ID = textIO.newStringInputReader()
            .withNumberedPossibleValues(user.getRegisteredCampList("CCMember"))
            .read("Select Camp ID");

        try { 
            user.writeCampReport(filtername, ID, filename);
            terminal.println("Wrote to " + filename);
        } catch (Exception e) {
            terminal.println(e.getMessage());
        }

    }
    /**
     * Helper function to displayCamps and obtain user selection
     * @param campIDList List of Camps
     * @return User selected choice
     */
    public static int displayCamps(ArrayList<String> campIDList){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 

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
    /**
     * UI to display specific details of selected camp
     * @param campID Specific ID for the camp
     * @param user User object to access get camp method
     */
    public static void displayCampDetails(String campID, AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal(); 
        terminal.print(user.getCamp(campID).toString());
    }
}
