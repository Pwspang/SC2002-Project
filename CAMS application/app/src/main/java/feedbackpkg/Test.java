import java.util.ArrayList;

public class Test {
    public static void main(String[] var0) {
        System.out.println("Hello World!");

        FeedbackManager var1 = FeedbackManager.getInstance();

        ArrayList<String> regCampList = new ArrayList<String>();
        regCampList.add("CAMP1");
        regCampList.add("CAMP2");

        // // TEST SUBMIT ENQUIRIES
        // System.out.println("Submit Suggestion 1");
        // var1.submitEnquiry("Charmain", "CAMP1", "I want to know more about this camp");
        // System.out.println("Submit Suggestion 2");
        // var1.submitEnquiry("Charmain", "CAMP4", "I want to know more about this camp");

        // TEST SUBMIT SUGGESTIONS
        System.out.println("Submit Suggestion 1");
        var1.submitSuggestion("Charmain", "CAMP1", "I want to know more about this camp");
        System.out.println("Submit Suggestion 2");
        var1.submitSuggestion("Charmain", "CAMP4", "I want to know more about this camp");

        // // Test getEnquiries
        // System.out.println("List of CampSuggestions");
        // try {
        //     var1.getEnquiries("Charmain");
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // // TEST REPLY ENQUIRY - VALID ENQUIRY, NOT IN REGCAMP
        // System.out.println("Reply to Enquiry not in regCampList");
        // try {
        // var1.replyEnquiry(2, regCampList, "I want to know more about this camp");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // }

        // // TEST REPLY ENQUIRY - INVALID ENQUIRY
        // System.out.println("Reply to Invalid Enquiry");
        // try {
        // var1.replyEnquiry(3, regCampList, "I want to know more about this camp");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // }

        // // TEST REPLY ENQUIRY - VALID ENQUIRY, IN REGCAMP
        // System.out.println("Reply to Enquiry 1 in regCampList");
        // try {
        // var1.replyEnquiry(1, regCampList, "Replied");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // }

        // // TEST DELETE ENQUIRY - VALID ENQUIRY
        // System.out.println("delete VAlID Enquiry ");
        // var1.deleteEnquiry(2, "Charmain");

        // // TEST DELETE ENQUIRY - INVALID ENQUIRY
        // System.out.println("delete invalid Enquiry ");
        // var1.deleteEnquiry(4, "Charmain");

        // // TEST EDIT ENQUIRY - VALID ENQUIRY
        // System.out.println("edit VAlID Enquiry ");
        // try {
        // var1.editEnquiry(1, "Charmain", "EDITED");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // // TEST EDIT ENQUIRY - INVALID ENQUIRY
        // System.out.println("edit invalid Enquiry ");
        // try {
        // var1.editEnquiry(4, "Charmain", "I want to know more about this camp");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // // TEST GET CAMP ENQUIRIES 
        // System.out.println("getCampEnquiries");
        // var1.getCampEnquiries("Charmain", regCampList);

        // // TEST GET SUGGESTIONS - VALID USER
        // System.out.println("getSuggestions - valid user");
        // var1.getSuggestions("Charmain");

        // // TEST GET SUGGESTIONS - INVALID USER
        // System.out.println("getSuggestions - invalid user");
        // var1.getSuggestions("zt");

        // // TEST EDIT SUGGESTION - VALID SUGGESTION
        // System.out.println("edit VAlID Suggestion ");
        // var1.editSuggestion(1, "Charmain", "EDITED");

        // // TEST EDIT SUGGESTION - INVALID SUGGESTION
        // System.out.println("edit invalid Suggestion ");
        // var1.editSuggestion(4, "Charmain", "EDITED");

        // // TEST DELETE SUGGESTION - VALID SUGGESTION
        // System.out.println("delete VAlID Suggestion ");
        // var1.deleteSuggestion(1, "Charmain");

        // // TEST DELETE SUGGESTION - INVALID SUGGESTION
        // System.out.println("delete invalid Suggestion ");
        // var1.deleteSuggestion(4, "Charmain");

        // TEST GET CAMP SUGGESTIONS - VALID CAMP
        System.out.println("getCampSuggestions - valid camp");
        var1.getCampSuggestions("CAMP4");

        // // TEST GET CAMP SUGGESTIONS - INVALID CAMP
        // System.out.println("getCampSuggestions - invalid camp");
        // var1.getCampSuggestions("CAMP2");

        // TEST APPROVE SUGGESTION - VALID SUGGESTION
        System.out.println("approve VAlID Suggestion ");
        var1.approveSuggestion(2);

        // // TEST APPROVE SUGGESTION - INVALID SUGGESTION
        // System.out.println("approve invalid Suggestion ");
        // var1.approveSuggestion(5);

        // TEST EDIT SUGGESTION - VALID SUGGESTION
        System.out.println("edit approved Suggestion ");
        var1.editSuggestion(2, "Charmain", "EDITED");
    }
}
