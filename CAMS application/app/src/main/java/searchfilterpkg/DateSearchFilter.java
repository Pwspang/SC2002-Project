package searchfilterpkg;

import java.time.LocalDate;
import java.util.*;

import camppkg.*;

/**
 * DateSearchFilter implements SearchFilter.
 */
public class DateSearchFilter implements SearchFilter {

    /**
     * Gets the list of campIDs based on date.
     * 
     * @param date     The date that will be used to filter the camp list.
     * @param campList The list of campIDs.
     * @return The filtered list of campIDs.
     */
    public ArrayList<String> filter(String date, ArrayList<String> campList) {

        ArrayList<String> filteredCampList = new ArrayList<>();
        LocalDate convertedDate = LocalDate.parse(date);
        CampManager campManager = CampManager.getInstance();

        for (int i = 0; i < campList.size(); i++) {
            Camp c = campManager.getCamp(campList.get(i));
            CampInformation info = c.getCampInfo();
            LocalDate startDate = info.getStartDate();
            if (convertedDate.equals(startDate)) {
                filteredCampList.add(campList.get(i));
            }

        }
        return filteredCampList;

    }

}
