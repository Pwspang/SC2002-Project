package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import camppkg.*;

import authenticationpkg.Faculty;
import java.time.LocalDate;

public class ReportFilterCampStudent extends ReportFilterCamp {
    
    public ReportFilterCampStudent(String campID, String filename) {
        super(campID, filename);
    }

    public void write() {
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
        int ccSlots = 1;//campInfo.getCampCommitteeSlots();
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
        content += "\nCamp Committee Slots: " + ccSlots;
        content += "\nDescription: " + description;
        content += "\n\nStudent Details:";

        for (int i = 0; i < studentNameList.size(); i++) {
            String k = studentNameList.get(i);
            String v = roles.get(k);

            content += "\nName: " + k + " Role: " + v;
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
