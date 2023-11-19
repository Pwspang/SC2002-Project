package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import camppkg.*;

public class ReportFilterCampAttendee extends ReportFilterCamp {

    public ReportFilterCampAttendee(String campID, String filename) {
        super(campID, filename);
    }

    public void write() {
        CampManager campManager = CampManager.getInstance();
        AccountManager accountManager = AccountManager.getInstance();
        CampInformation campInfo = CampManager.getCampInfo();
        String campName = campInfo.getCampName();
        String startDate = campInfo.getStartDate();
        String endDate = campInfo.getEndDate();
        String closingDate = campInfo.getRegisterationClosingDate();
        boolean openToNTU = campInfo.getOpenToWholeNTU();
        Faculty userGroup = campInfo.getUserGroup();
        String location = campInfo.getLocation();
        int totalSlots = campInfo.getTotalSlots();
        int ccSlots = campInfo.getCampCommitteeSlots();
        String description = campInfo.getDescription();

        ArrayList<String> studentNameList = campManager.getRegisteredStudents();
        HashMap<String, String> roles = campManager.getRegisteredStudentRoles(getCampID());

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
        content += "\nCamp Committee Slots: " + ccSlots;
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
            FileWriter fileWriter = new FileWriter(getFileName());
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