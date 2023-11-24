package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import authenticationpkg.Faculty;
import java.time.LocalDate;
import camppkg.*;

/**
 * ReportFilterCampAttendee inherits from ReportFilterCamp.
 * The type of report written by this filter is the information of camp and
 * list of all attendees.
 */
public class ReportFilterCampAttendee extends ReportFilterCamp {

    /**
     * Constructor used to instantiate the ReportFilterCampAttendee with campID.
     * 
     * @param campID The campID associated to the camp.
     */
    public ReportFilterCampAttendee(String campID) {
        super(campID);
    }

    /**
     * Writes the report to a specfiied file name.
     * 
     * @param filename The file name that the report will be written to.
     */
    public void write(String filename) {
        CampManager campManager = CampManager.getInstance();
        CampInformation campInfo = campManager.getCampInfo(getID());
        String campName = campInfo.getCampName();
        LocalDate startDate = campInfo.getStartDate();
        LocalDate endDate = campInfo.getEndDate();
        LocalDate closingDate = campInfo.getRegisterationClosingDate();
        boolean openToNTU = campInfo.getOpenToWholeNTU();
        Faculty userGroup = campInfo.getUserGroup();
        String location = campInfo.getLocation();
        int totalSlots = campInfo.getTotalSlots();
        // int ccSlots = campInfo.getCampCommitteeSlots();
        String description = campInfo.getDescription();

        ArrayList<String> studentNameList = campManager.getRegisteredStudents(getID());
        HashMap<String, String> roles = campManager.getRegisteredStudentRoles(getID());

        Collections.sort(studentNameList);

        String content = "";
        content += "Camp Name: " + campName;
        content += "\nStart Date: " + startDate;
        content += "\nEnd Date: " + endDate;
        content += "\nRegistration Closing Date: " + closingDate;
        content += "\nOpen to NTU: " + openToNTU;
        content += "\nFaculty: " + userGroup;
        content += "\nLocation: " + location;
        content += "\nTotal Slots: " + totalSlots;
        content += "\nCamp Committee Slots: " + 10; // ccSlots;
        content += "\nDescription: " + description;
        content += "\n\nStudent Details:";

        for (int i = 0; i < studentNameList.size(); i++) {
            String k = studentNameList.get(i);
            String v = roles.get(k);
            if (v.equals("Attendee")) {
                content += "\nName: " + k + " Role: " + v;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write content to the file
            bufferedWriter.write(content);

            // Close the writers to release resources
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Content has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
