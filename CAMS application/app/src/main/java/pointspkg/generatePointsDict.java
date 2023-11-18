package pointspkg;

import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class generatePointsDict {
    public static void main(String args[]) {
        HashMap<String, int> myHashMap = new HashMap<>();
        myHashMap.put("YCHERN@e.ntu.edu.sg", 0);
        myHashMap.put("KOH1@e.ntu.edu.sg", 0);
        myHashMap.put("BR015@e.ntu.edu.sg", 0);
        myHashMap.put("CT113@e.ntu.edu.sg", 0);
        myHashMap.put("YCN019@e.ntu.edu.sg", 0);
        myHashMap.put("DL007@e.ntu.edu.sg", 0);
        myHashMap.put("DON84@e.ntu.edu.sg", 0);
        myHashMap.put("ELI34@e.ntu.edu.sg", 0);
        myHashMap.put("LE51@e.ntu.edu.sg", 0);
        myHashMap.put("SL22@e.ntu.edu.sg", 0);
        myHashMap.put("AKY013@e.ntu.edu.sg", 0);

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("PointsManager.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(myHashMap);
            out.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
