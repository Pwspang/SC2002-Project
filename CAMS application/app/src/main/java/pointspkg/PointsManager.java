package pointspkg;

import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PointsManager implements Serializable {
    private HashMap<String,Integer> pointsDict;
    private static final PointsManager pointsManager = new PointsManager();
    private static final String filename = "PointsManager.dat";

    private PointsManager() {
        pointsDict = readSerializedObject();

    }

    public void writeSerializedObject() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(pointsDict);
            out.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public HashMap<String, Integer> readSerializedObject() {
		HashMap<String, Integer> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (HashMap<String, Integer>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		// System.out.println(" Details Size: " + pDetails.size());
		// System.out.println();
		return pDetails;
	}

    public static PointsManager getInstance() {
        return pointsManager;
    }

    public HashMap<String, Integer> getPointsDict() {
        return pointsDict;
    }

    public int getPoints(String userID) {
        return pointsDict.get(userID);
    }

    public void addOnePoint(String userID) {
        int current = pointsDict.get(userID);
        pointsDict.put(userID, current + 1);
    }

}
