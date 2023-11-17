
package camppkg;
import java.util.*;

public class Camp {
    private String id;
    private boolean visibility;
    private CampInformation campinfo;

    public Camp(String campID, CampInformation campinfo) {
        this.id = campID;
        this.visibility = false;
        this.campinfo = campinfo;
    }

    public String getID() {
        return this.id;
    }

    public CampInformation getCampInfo() {
        return this.campinfo;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void toggleVisibility() {
        visibility = !visibility;
    }

}