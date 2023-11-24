package pointspkg;

import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/***
 * Used to store the points of all students.
 * Contains functions related to students' points.
 */
public class PointsManager implements Serializable {
    /**
     * A HashMap to store the points of all students.
     * Key: userID, Value: number ogf points.
     */
    private HashMap<String, Integer> pointsDict;
    /**
     * An instance of PointsManager.
     */
    private static final PointsManager pointsManager = new PointsManager();
    /**
     * The file name that PointsManager reads and writes to.
     */
    private static final String filename = "src/main/resources/PointsManager.dat";

    /**
     * Constructor method used to construct PointsManager.
     * Initialises the pointsDict from the PointsManager.dat file.
     */
    private PointsManager() {
        pointsDict = readSerializedObject();

    }

    /**
     * Used to write serialised pointsDict to the PointsManager.dat file.
     */
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

    /**
     * Reads from the PointsManager.dat file to initialise pointsDict.
     * 
     * @return HashMap object with userID as key, number of points as value.
     */
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

    /**
     * Gets an instance of the PointsManager.
     * Ensures that only a single instance of PointsManager is created.
     * 
     * @return PointsManager object
     */
    public static PointsManager getInstance() {
        return pointsManager;
    }

    /**
     * Gets the pointsDict of PointsManager.
     * 
     * @return HashMap object with userID as key, number of points as value.
     */
    public HashMap<String, Integer> getPointsDict() {
        return pointsDict;
    }

    /**
     * Gets the number of points of a student.
     * 
     * @param userID The userID of the student.
     * @return Number of points that the student has.
     */
    public Integer getPoints(String userID) {
        return pointsDict.get(userID);
    }

    /**
     * Adds one point to a student account.
     * 
     * @param userID The userID of the student.
     */
    public void addOnePoint(String userID) {
        Integer current = pointsDict.get(userID);
        pointsDict.put(userID, current + 1);
    }

}
