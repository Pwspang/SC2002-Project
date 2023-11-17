package viewPackage;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class studentUI implements iView{
    private static studentUI ui=null;
    private static String[] options = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};

    public int displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   
        terminal.setBookmark("studentUI");

        // To change header
        terminal.println("Options for student");

        // Print options table
        terminal.println(iView.displayOptionTable(options));

        int choice = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(options.length)
                .read("Option: ");

        terminal.resetToBookmark("studentUI");
        return choice;
    };

    public static iView getInstance(){
        if (studentUI.ui == null){
            studentUI.ui = new studentUI();
        }
        return studentUI.ui;
    }

    public void handleOption(int option){};
}
