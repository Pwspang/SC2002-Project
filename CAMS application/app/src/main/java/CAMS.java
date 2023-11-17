// To import all the required packages
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import viewpkg.*;
import viewpkg.subview.LoginUI;
import authenticationpkg.*;


/**
 * Main application class, entry point for the program 
 * 
 * @author Pang Wei Siong
 * @version 1.0 
 */
public class CAMS {
    /*
     * User object
     */
    private static AuthUser user=null;
    /*
     * Will be either studentUI, StaffUI, CampComitteeUI
     */
    private static iView currView;

    /*
     * Initial entry to the application
     */
    public static void main(String[] args){
        start();
    }

    /*
     * Main loop of the application, handles view states and choices
     */
    private static void start(){
        int choice;
        boolean done = true;
        startAllManager();
        // Current state to call currView.displayOptions() on loop
        while (done){
            if (user == null){
                login();
            } else {
                choice = currView.displayOptions();
                switch(choice){
                    case 1:
                        closeAllManager();
                        done = false;
                        break;
                    case 2:
                        logout();
                        break;
                    case 3:
                        LoginUI.resetPassword(user);
                        break;
                    default: 
                        currView.handleOption(choice, user);
                }
            }
        }
    }

    /*
     * Runs LoginUI.handleLogin(), sets the user object and sets the corresponding view
     */
    private static void login(){
        user = LoginUI.handleLogin();

        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   

        
        //To replace with user.getUI() 
        int choice = textIO.newIntInputReader()
        .withMinVal(1)
        .withMaxVal(3)
        .read("Option: ");

        switch (choice){
            case 1: 
            currView = StaffUI.getInstance();       
            break;
            case 2:
            currView = StudentUI.getInstance();       
            break;
            case 3:
            currView = CampComitteeUI.getInstance();       
            break;
        }

        
    }

    public static void logout(){
        user = null;
    }
    /*
     * Starts all manager object, serializing the objects from dat file
     */
    private static void startAllManager(){
        //something.getInstance()
    }
    /*
     * Stops all manager object, deserializing the objects to dat file
     */
    private static void closeAllManager(){
        //something.close() -> to serialize all the objects
        TextIO textIO = TextIoFactory.getTextIO();
        textIO.dispose();
    }

}