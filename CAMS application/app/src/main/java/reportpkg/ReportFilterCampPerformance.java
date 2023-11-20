package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import camppkg.*;

public class ReportFilterCampPerformance extends ReportFilterCamp {

    public ReportFilterCampPerformance(String campID) {
        super(campID);
    }

    public void write(String filename) {
        CampManager campManager = CampManager.getInstance();
        CampInformation campInfo = campManager.getCampInfo(getID());

        ArrayList<String> studentNameList = campManager.getRegisteredStudents(getID());
        HashMap<String, String> roles = campManager.getRegisteredStudentRoles(getID());

        Collections.sort(studentNameList);

        String content = "";

        for (int i = 0; i < studentNameList.size(); i++) {
            String k = studentNameList.get(i);
            String v = roles.get(k);
            if (v.equals("CCMember")) {
                content += "\nUserID: " + k + " Role: " + v;
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
