package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

import authenticationpkg.AccountManager;
import authenticationpkg.Faculty;
import camppkg.*;

/**
 * ReportFilterCampCCMember inherits from ReportFilterCamp.
 * The type of report written by this filter is the information of camp and
 * list of all CCMembers.
 */
public class ReportFilterCampCCMember implements ReportFilterCamp {

    /**
     * Writes the report to a specfiied file name.
     * 
     * @param filename The file name that the report will be written to.
     */
    public void write(String campID, String filename) {
        CampManager campManager = CampManager.getInstance();
        CampInformation campInfo = campManager.getCampInfo(campID);
        String campName = campInfo.getCampName();
        LocalDate startDate = campInfo.getStartDate();
        LocalDate endDate = campInfo.getEndDate();
        LocalDate closingDate = campInfo.getRegisterationClosingDate();
        boolean openToNTU = campInfo.getOpenToWholeNTU();
        Faculty userGroup = campInfo.getUserGroup();
        String location = campInfo.getLocation();
        int totalSlots = campInfo.getTotalSlots();
        int ccSlots = campManager.getCampCommitteeSlots(campInfo.getCampName());
        String description = campInfo.getDescription();

        ArrayList<String> studentNameList = campManager.getRegisteredStudents(campID);
        HashMap<String, String> roles = campManager.getRegisteredStudentRoles(campID);

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
        content += "\nCamp Committee Slots: " + ccSlots;// ccSlots;
        content += "\nDescription: " + description;
        content += "\n\nStudent Details:";

        for (int i = 0; i < studentNameList.size(); i++) {
            String k = studentNameList.get(i);
            String v = roles.get(k);
            if (v.equals("CCMember")) {
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
