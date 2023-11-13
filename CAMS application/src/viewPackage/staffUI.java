package viewPackage;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class staffUI implements iView{
    public void displayOptions(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();

        terminal.println("Options for staff");

        //blocking input
        textIO.newStringInputReader().read("Blocking");
    };

    private void
}
