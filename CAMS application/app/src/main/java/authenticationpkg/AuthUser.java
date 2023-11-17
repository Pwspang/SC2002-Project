package authenticationpkg;

import java.util.ArrayList;

public class AuthUser {
    public ArrayList<Integer> getFeedback(){
        ArrayList<Integer> feedback = new ArrayList<Integer>();
        feedback.add(1);
        feedback.add(2);
        feedback.add(3);
        feedback.add(4);

        return feedback;
    }

    public String getCamp(String campID){
        return "Camp details";
    }
}
