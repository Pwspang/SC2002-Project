package viewPackage;

import de.vandermeer.asciitable.*;

/**
 *
 */
public interface iView {
    /**
     * 
     */
    public int displayOptions();
    public void handleOption(int option);
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
}
