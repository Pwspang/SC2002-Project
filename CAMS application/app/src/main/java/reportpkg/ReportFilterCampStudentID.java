package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import camppkg.*;

public class ReportFilterCampStudentID extends ReportFilterCamp {

    public ReportFilterCampStudentID(String studentID) {
        super(studentID);
    }

    public void write() {
        CampManager campManager = CampManager.getInstance();

        ArrayList<String> campList = campManager.getRegisteredCampList(getID());

        String content = "";

        for (int i = 0; i < campList.size(); i++) {

            content += campList.get(i) + "\n";
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
