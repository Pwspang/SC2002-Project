
package camppkg;
import java.util.*;

/**
 *  Class of Movie. 
 *  Contain
 */
public class CampInformation {
    private String id;
    private Bool visibility;
    private ArrayList<String> stuWithdrawn;

    /**
	 * Constructs a Camp object based on CampInformation.
	 * @param id Unique Camp name
	 * @param CampInformation Information needed to construct Camp
	 */
    public Camp(campID: String, CampInformation campinfo) {
        this.id = campID;
        this.visibility = false;
        this.campinfo = campinfo;
        this.stuWithdrawn = new ArrayList<String>();
    }

    
    public String getID() {
        return this.id;
    }

    public CampInformation getCampInfo() {
        return this.campinfo;
    }

    public Bool getVisibility() {
        return this.visibility;
    }

    public ArrayList<stuWithdrawn> getStuWithdrawn() {
        return this.stuWithdrawn;
    }

    public toggleVisibility() {
        visbility = !visbility;
        return void;
    }

}