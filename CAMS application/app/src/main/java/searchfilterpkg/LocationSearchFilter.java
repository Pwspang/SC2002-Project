package searchfilterpkg;

import java.time.LocalDate;
import java.util.*;

import camppkg.*;

/**
 * LocationSearchFilter implements SearchFilter.
 */
public class LocationSearchFilter implements SearchFilter {

    /**
     * Gets the list of campIDs based on location.
     * 
     * @param date     The name of location that will be used to filter the camp
     *                 list.
     * @param campList The list of campIDs.
     * @return The filtered list of campIDs.
     */
    public ArrayList<String> filter(String location, ArrayList<String> campList) {

        ArrayList<String> filteredCampList = new ArrayList<>();
        CampManager campManager = CampManager.getInstance();

        for (int i = 0; i < campList.size(); i++) {
            Camp c = campManager.getCamp(campList.get(i));
            CampInformation info = c.getCampInfo();
            String loc = info.getLocation();
            if (location.equals(loc)) {
                filteredCampList.add(campList.get(i));
            }

        }
        return filteredCampList;

    }

}
