package reportpkg;

import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import authenticationpkg.AccountManager;
import camppkg.*;
import feedbackpkg.*;

/**
 * ReportFilterCampEnquiry inherits from ReportFilterCamp.
 * The type of report written by this filter is a list of enquiries for a camp.
 */
public class ReportFilterCampEnquiry implements ReportFilterCamp {

    /**
     * Writes the report to a specfiied file name.
     * 
     * @param filename The file name that the report will be written to.
     */
    public void write(String campID, String filename) {
        FeedbackManager feedbackManager = FeedbackManager.getInstance();
        ArrayList<Feedback> feedbackList = feedbackManager.getFeedbackList();
        String content = "";
        for (int i = 0; i < feedbackList.size(); i++) {
            if (feedbackList.get(i).getCampID().equals(campID) && feedbackList.get(i) instanceof Enquiry) {
                content += "UserID: " + feedbackList.get(i).getUserID();
                content += "\nContent: " + feedbackList.get(i).getContent();
                content += "\n\n";
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
