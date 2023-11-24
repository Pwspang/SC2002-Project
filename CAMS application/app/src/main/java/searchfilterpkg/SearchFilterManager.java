package searchfilterpkg;

import java.util.*;

/***
 * Used to store the types of search filters.
 * Purpose is to call the filter function of the search filters.
 */
public class SearchFilterManager {
    /**
     * A HashMap with filter name as key and SearchFilter for value.
     * This HashMap will contain types of search filters.
     */
    private static HashMap<String, SearchFilter> searchFilterDict;

    /**
     * An instance of SearchFilterManager.
     */
    private static final SearchFilterManager searchFilterManager = new SearchFilterManager();

    /**
     * Constructor method used to construct SearchFilterManager.
     * Initialises the searchFilterDict.
     */
    private SearchFilterManager() {
        searchFilterDict = new HashMap<String, SearchFilter>();
        searchFilterDict.put("DateSearchFilter", new DateSearchFilter());
        searchFilterDict.put("LocationSearchFilter", new LocationSearchFilter());
    }

    /**
     * Gets the list of the types of filters.
     * 
     * @return List of the types of filters.
     */
    public ArrayList<String> getFilterList() {
        return new ArrayList<>(searchFilterDict.keySet());
    }

    /**
     * Gets the SearchFilter object based on the name of filter.
     * 
     * @param nameOfFilter The name of the filter.
     * @return SearchFilter with the corresponding filter name.
     */
    public SearchFilter getFilter(String nameOfFilter) {
        SearchFilter rfc = searchFilterDict.get(nameOfFilter);
        return rfc;
    }

    /**
     * Gets the searchFilterDict of SearchFilterManager.
     * 
     * @return HashMap object with filter name as key and SearchFilter for
     *         value.
     */
    public HashMap<String, SearchFilter> getSearchFilterDict() {
        return searchFilterDict;
    }

    /**
     * Calls the filter method of the filter to filter the camp list.
     * Gets the list of campIDs after filtering.
     * 
     * @param nameOfFilter The name of the filter to be used.
     * @param filterOption Data used to filter the list (e.g. The name of
     *                     location or the date of camp).
     * @param campList     The list of camps that will be filtered.
     * @return The filtered list of campIDs.
     */
    public ArrayList<String> filter(String nameOfFilter, String filterOption, ArrayList<String> campList) {
        SearchFilter rfc = getFilter(nameOfFilter);
        return rfc.filter(nameOfFilter, filterOption, campList);
    }

    /**
     * Gets an instance of the SearchFilterManager.
     * Ensures that only a single instance of SearchFilterManager is created.
     * 
     * @return SearchFilterManager object
     */
    public static SearchFilterManager getInstance() {
        return searchFilterManager;
    }

}