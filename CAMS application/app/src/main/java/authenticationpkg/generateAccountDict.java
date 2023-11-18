package authenticationpkg;
/*
import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class generateAccountDict {
    public static void main(String args[]) {
        HashMap<String, AuthUser> myHashMap = new HashMap<>();
        myHashMap.put("YCHERN@e.ntu.edu.sg", AuthStudent("CHERN", "YCHERN@e.ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("KOH1@e.ntu.edu.sg", AuthStudent("KOH", "KOH1@e.ntu.edu.sg", "password", Faculty.ADM));
        myHashMap.put("BR015@e.ntu.edu.sg", AuthStudent("BRANDON", "BR015@e.ntu.edu.sg", "password", Faculty.EEE));
        myHashMap.put("CT113@e.ntu.edu.sg", AuthStudent("CALVIN", "CT113@e.ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("YCN019@e.ntu.edu.sg", AuthStudent("CHAN", "YCN019@e.ntu.edu.sg", "password", Faculty.NBS));
        myHashMap.put("DL007@e.ntu.edu.sg", AuthStudent("DENISE", "DL007@e.ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("DON84@e.ntu.edu.sg", AuthStudent("DONG", "DON84@e.ntu.edu.sg", "password", Faculty.ADM));
        myHashMap.put("ELI34@e.ntu.edu.sg", AuthStudent("ERNEST", "ELI34@e.ntu.edu.sg", "password", Faculty.EEE));
        myHashMap.put("LE51@e.ntu.edu.sg", AuthStudent("LEE", "LE51@e.ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("SL22@e.ntu.edu.sg", AuthStudent("LIU", "SL22@e.ntu.edu.sg", "password", Faculty.NBS));
        myHashMap.put("AKY013@e.ntu.edu.sg", AuthStudent("RAWAL", "AKY013@e.ntu.edu.sg", "password", Faculty.SSS));

        myHashMap.put("HUKUMAR@ntu.edu.sg", AuthStaff("MADHUKUMAR", "HUKUMAR@ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("OURIN@ntu.edu.sg", AuthStaff("ALEXEI", "OURIN@ntu.edu.sg", "password", Faculty.ADM));
        myHashMap.put("UPAM@ntu.edu.sg", AuthStaff("CHATTOPADHYAY", "UPAM@ntu.edu.sg", "password", Faculty.EEE));
        myHashMap.put("ANWIT@ntu.edu.sg", AuthStaff("DATTA", "ANWIT@ntu.edu.sg", "password", Faculty.SCSE));
        myHashMap.put("ARVI@ntu.edu.sg", AuthStaff("ARVIND", "ARVI@ntu.edu.sg", "password", Faculty.NBS));

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("AccountManager.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(myHashMap);
            out.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
*/