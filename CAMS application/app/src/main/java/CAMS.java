// To import all the required packages
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import viewpkg.*;
import viewpkg.subview.LoginUI;
import authenticationpkg.*;
import camppkg.CampManager;
import feedbackpkg.FeedbackManager;


/**
 * Main application class, entry point for the program 
 * 
 * @author Pang Wei Siong
 * @version 1.0 
 */
public class CAMS {
    /*
     * AuthUser object
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
        startAllManager();
        start();
        closeAllManager();
    }

    /*
     * Main loop of the application, handles view states and choices
     */
    private static void start(){
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal();   

        int choice;
        boolean done = true;
        
        // Current state to call currView.displayOptions() on loop
        while (done){
            // debugging block
            try {
                if (user == null){
                    login();
                } else {
                    terminal.setBookmark("Main View");
                    terminal.printf("Welcome %s from %s\n", user.getName(), user.getFaculty().name());
                    choice = currView.displayOptions();
                    switch(choice){
                        case 1:
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
                    terminal.resetToBookmark("Main View");
                }
            } catch (Exception e){
                terminal.print(e.getMessage());
            }
        }
    }

    /*
     * Runs LoginUI.handleLogin(), sets the user object and sets the corresponding view
     */
    private static void login(){
        user = LoginUI.handleLogin();

        if (user != null){
            currView = user.getUI();
        }
    }
    
    /**
     * Logout function, sets user to null
     */
    public static void logout(){
        user = null;
    }
    /*
     * Starts all manager object, serializing the objects from dat file
     */
    private static void startAllManager(){
        //something.getInstance()
        // Added for testing
        AccountManager.getInstance();
        CampManager.getInstance();
        FeedbackManager.getInstance();
        
    }
    /*
     * Stops all manager object, deserializing the objects to dat file
     */
    private static void closeAllManager(){
        
        //something.close() -> to serialize all the objects
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.writeSerializedObject();

        CampManager campManager = CampManager.getInstance();
        campManager.writeSerializedObject();

        FeedbackManager feedbackManager = FeedbackManager.getInstance();
        feedbackManager.writeSerialisedObj();

        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal terminal = textIO.getTextTerminal(); 
        terminal.dispose();
    }

}