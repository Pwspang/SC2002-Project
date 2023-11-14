package viewPackage;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 */
public class loginUI {
    /**
     *
     * @return User object
     */
    public static int handleLogin(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();

        terminal.println("Welcome to CAMS");

        String user = textIO.newStringInputReader()
                .read("Username");

        String password = textIO.newStringInputReader()
                .withInputMasking(true)
                .read("Password");


        return 1;
    }

}
