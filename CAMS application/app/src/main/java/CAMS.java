// To import all the required packages
import viewPackage.subview.loginUI;
import viewPackage.*;


/**
 *
 */

public class CAMS {
    //To change to user
    private static int user=0;
    private static iView currView;

    public static void main(String[] args){
        start();
    }

    private static void start(){
        int choice;
        startAllManager();
        // Current state to call currView.displayOptions() on loop
        while (true){
            if (user == 0){
                login();
            } else {
                choice = currView.displayOptions();
                switch(choice){
                    case 1:
                        logout();
                        break;
                    case 2:
                        loginUI.resetPassword();
                        break;
                    default:
                        currView.handleOption(choice);
                }
            }
        }
    }

    private static void login(){
        user=loginUI.handleLogin();

        //Use switch case to set user UI
        currView = staffUI.getInstance();   
    }

    public static void logout(){
        user = 0;
    }

    private static void startAllManager(){
        //something.getInstance()
    }

    private static void closeAllManager(){
        //something.close() -> to serialize all the objects
    }

}