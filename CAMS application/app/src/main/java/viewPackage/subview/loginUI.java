package viewPackage.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 */
public class loginUI{
    /**
     *
     * @return User object
     */
    public static int handleLogin(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();
        terminal.setBookmark("login");

        terminal.println("""
            ######     ###    ##     ##  ######  
            ##    ##   ## ##   ###   ### ##    ## 
            ##        ##   ##  #### #### ##       
            ##       ##     ## ## ### ##  ######  
            ##       ######### ##     ##       ## 
            ##    ## ##     ## ##     ## ##    ## 
             ######  ##     ## ##     ##  ######                                                                               
                """); 

        String user = textIO.newStringInputReader()
                .read("Username");

        String password = textIO.newStringInputReader()
                .read("Password");

        // To call account manager.login() method;

        terminal.resetToBookmark("login");

        return 1;
    }

    public static void resetPassword(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();

        terminal.setBookmark("resetPassword");

        //call reset password
        terminal.println("Reset password screen.");

        String password = textIO.newStringInputReader()
                .read("New Password");
        
        //Call account manager here

        terminal.resetToBookmark("resetPassword");
    }

}
