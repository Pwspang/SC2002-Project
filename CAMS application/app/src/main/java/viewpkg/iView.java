package viewpkg;

import de.vandermeer.asciitable.*;

import authenticationpkg.AuthUser;

/**
 * The main interface which StudentUI, StaffUI, CampComitteeUI will implement this interface
 */
public interface iView {
    /**
     * Display the options for user to select the input and returns the option that the user choose
     */
    public int displayOptions();
    /**
     * @return 
     */

    /**
     * Handles the selection of option
     * @param option Should be within the range of options
     * @param user AuthUser object
     */
    public void handleOption(int option, AuthUser user);

    /**
     * Generate the options table from array of string into string
     * @param options Contains a list of options
     */
    public static String displayOptionTable(String[] options){
            AsciiTable at = new AsciiTable();
            at.addRule();
        for (int i=0; i < options.length; i++){
            at.addRow(i+1, options[i]);
            at.addRule();
        }
        at.getRenderer().setCWC(new CWC_LongestLine());
        at.setPaddingLeftRight(1);
        return at.render();
    }
    /**
    * @return Formatted table in string type
    */

    /**
     * Generate Header for UI
     */
    public static String displayHeader(String name){
        return  "================================================\n" + name + "\n================================================";
    }
}
