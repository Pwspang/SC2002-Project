package viewpkg.subview;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import authenticationpkg.*;
import viewpkg.iView;

/**
 * LoginUI to handle UI elements related to login
 */
public class LoginUI{
    /**
     * Handles IO for login
     */
    public static AuthUser handleLogin(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();
        terminal.setBookmark("login");

        terminal.println("""
                +=================================================+
                | .d8888b.        d8888 888b     d888  .d8888b.   |
                | d88P  Y88b      d88888 8888b   d8888 d88P  Y88b |
                | 888    888     d88P888 88888b.d88888 Y88b.      |
                | 888           d88P 888 888Y88888P888  "Y888b.   |
                | 888          d88P  888 888 Y888P 888     "Y88b. |
                | 888    888  d88P   888 888  Y8P  888       "888 |
                | Y88b  d88P d8888888888 888   "   888 Y88b  d88P |
                |  "Y8888P" d88P     888 888       888  "Y8888P"  |
                +=================================================+                                                                                                                   
                        """);

        String username = textIO.newStringInputReader()
                .read("Username");

        String password = textIO.newStringInputReader()
                .read("Password");

        AuthUser user=null;

        try {
                AccountManager accountManager = AccountManager.getInstance();

                user = accountManager.login(username, password);

        } catch (Exception e) {
                terminal.println(e.getMessage()); 
                
                //blocking input here
                textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");
        }

        terminal.resetToBookmark("login");
        
        return user;
    }
    /**
     * @return AuthUser object
     */

    /**
     * Handles IO for reset password
     * @param user AuthUser object
     */
    public static void resetPassword(AuthUser user){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();

        terminal.setBookmark("resetPassword");

        //call reset password
        terminal.println(iView.displayHeader("Reset Password")); 

        String password = textIO.newStringInputReader()
                .read("New Password");
        
        //Call account manager here
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.changePassword(user, password);

        terminal.println("Password sucessfully changed.");

        // Blocking function 
        textIO.newStringInputReader().withMinLength(0).read("\nPress enter to continue...");

        terminal.resetToBookmark("resetPassword");
    }

}
