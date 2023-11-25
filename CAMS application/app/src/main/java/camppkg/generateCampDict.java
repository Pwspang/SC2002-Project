package camppkg;

import java.io.Serializable;
import java.util.HashMap;

import authenticationpkg.AuthStaff;
import authenticationpkg.AuthStudent;
import authenticationpkg.Faculty;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class generateCampDict implements Serializable{
    public static void main(String args[]) {
        HashMap<String, Camp> myHashMap = new HashMap<>();
        myHashMap.put("Camp 1", new Camp("Camp 1", new CampInformation("Camp 1", "2024-01-10", "2024-01-20", "2024-01-10", true, Faculty.EEE, "Singapore", 100, 10, "Camp Description", "UPAM@ntu.edu.sg")));
        myHashMap.put("Camp 2", new Camp("Camp 2", new CampInformation("Camp 2", "2024-02-10", "2024-02-20", "2024-01-20", false, Faculty.SCSE, "Singapore", 100, 10, "Camp Description", "HUKUMAR@ntu.edu.sg")));
        myHashMap.put("Camp 3", new Camp("Camp 3", new CampInformation("Camp 3", "2024-03-10", "2024-03-20", "2024-01-20", true, Faculty.EEE, "Singapore", 100, 10, "Camp Description", "UPAM@ntu.edu.sg")));
        myHashMap.put("Camp 4", new Camp("Camp 4", new CampInformation("Camp 4", "2024-04-10", "2024-04-20", "2024-01-20", false, Faculty.EEE, "Singapore", 100, 10, "Camp Description", "UPAM@ntu.edu.sg")));
        myHashMap.put("Camp 5", new Camp("Camp 5", new CampInformation("Camp 5", "2024-05-10", "2024-05-20", "2024-01-20", true, Faculty.NBS, "Singapore", 100, 10, "Camp Description", "ARVI@ntu.edu.sg")));
        myHashMap.put("Camp 6", new Camp("Camp 6", new CampInformation("Camp 6", "2024-06-10", "2024-06-20", "2024-01-20", false, Faculty.ADM, "Singapore", 100, 10, "Camp Description", "OURIN@ntu.edu.sg")));
       

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("src/main/resources/CampManager.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(myHashMap);
            out.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}