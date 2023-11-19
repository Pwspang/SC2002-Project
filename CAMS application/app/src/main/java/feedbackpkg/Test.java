import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollBarUI;
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        FeedbackManager fm = FeedbackManager.getInstance();
        // System.out.println("Submit suggestion 1");
        // fm.submitSuggestion("Charmain", "Camp1", "I want to know more about this camp");

        
        // System.out.println("View suggestion of Charmain");
        // fm.getSuggestions("Charmain");

    
        // System.out.println("Submit charmain s2");
        // fm.submitSuggestion("Charmain", "Camp2", "I want to know more about this camp");

        // System.out.println("View suggestions of Charmain");
        // fm.getSuggestions("Charmain");            

        // System.out.println("Delete enquiry with wrong feedback ID");
        // fm.deleteSuggestion(10, "Charmain");

        
        // System.out.println("Delete enquiry with correct feedback ID");
        // fm.deleteSuggestion(1, "Charmain");

        // System.out.println("View suggestions of Charmain");
        // fm.getSuggestions("Charmain");

        ArrayList<String> regCampList = new ArrayList<String>();
        regCampList.add("CAMP1");
        regCampList.add("CAMP2");

        fm.getEnquiries("Charmain");

        ArrayList<String> regCampList1 = new ArrayList<String>();

        // fm.submitEnquiry("Charmain", "CAMP1", "CONTENT");
        fm.submitSuggestion("Charmain", "CAMP2", "I want to know more about this camp");
        fm.submitSuggestion("Charmain", "CAMP2", "I want to know more about this camp");
        fm.getCampSuggestions("Charmain");

        // System.out.println("GET CAMP SUGGESITONS");
        // fm.getCampSuggestions("CAMP2");
        
        // // System.out.println("GET CAMP ENQUIRIES - CORRECT CAMP NAME");
        // // fm.getCampEnquiries("Charmain", regCampList);

        // // System.out.println("reply enquiry - wrong feedback id");
        // // fm.replyEnquiry(0, regCampList, "CAMP1", "REPLIED");
        
        // // System.out.println("reply enquiry - wrong feedback camp name");
        // // fm.replyEnquiry(1, regCampList, "CAMP3", "REPLIED");

        // // System.out.println("reply enquiry - correct feedback id");
        // // fm.replyEnquiry(1, regCampList, "CAMP2", "REPLIED");

        // // System.out.println("reply enquiry - WRONG feedback id");
        // // fm.deleteEnquiry(10, "Charmain");

        // System.out.println("delete SUGGESITON - wrong feedback id");
        // fm.deleteSuggestion(10, "Charmain");

        // System.out.println("delete SUGGESITON - wrong student id");
        // fm.deleteSuggestion(1, "ZT");

        // System.out.println("delete SUGGESITON - CORRECT student id");
        // fm.deleteSuggestion(1, "Charmain");

        
        System.out.println("Approve SUGGESITONS - WRONG feedbackID");
        fm.approveSuggestion(10);
        
        System.out.println("Approve SUGGESITONS - CORRECT");
        fm.approveSuggestion(1);

        
        System.out.println("EDIT APPROVED SUGGESITONS");
        fm.editEnquiry(1, "Charmain", "newContent");

        
        System.out.println("EDIT UNAPPROVED SUGGESITONS");
        fm.editSuggestion(2, "Charmain", "newContent");

    }
}
