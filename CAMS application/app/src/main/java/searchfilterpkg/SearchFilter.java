package searchfilterpkg;

import java.util.*;

/**
 * SearchFilter is an interface that represents all report filters shall
 * implement.
 */

interface SearchFilter {
    /**
     * Gets the list of campIDs after filtering.
     * 
     * @param filterOption Data used to filter the list (e.g. The name of
     *                     location or the date of camp).
     * @param campList     The list of camps that will be filtered
     * @return The filtered list of campIDs.
     */
    ArrayList<String> filter(String filterOption, ArrayList<String> campList);

}
