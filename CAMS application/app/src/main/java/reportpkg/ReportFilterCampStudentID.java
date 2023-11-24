package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import authenticationpkg.Faculty;
import camppkg.*;

/**
 * ReportFilterCampStudentID inherits from ReportFilterCamp.
 * The type of report written by this filter is a list of camps that this
 * student has joined.
 */
public class ReportFilterCampStudentID extends ReportFilterCamp {

    /**
     * Constructor used to instantiate the ReportFilterCampStudentID with studentID.
     * 
     * @param studentID studentID related to the student.
     */
    public ReportFilterCampStudentID(String studentID) {
        super(studentID);
    }

    /**
     * Writes the report to a specfiied file name.
     * 
     * @param filename The file name that the report will be written to.
     */
    public void write(String filename) {

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
